package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.semester.SemesterDao;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.Mapping;

import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    private final SubjectSemesterDao subjectSemesterDao;
    private final SemesterDao semesterDao;
    private final ServerDao serverDao;
    private final MapperFacade mapperFacade;


    public MappingService(MapperFacade mapperFacade, SubjectSemesterDao subjectSemesterDao, SemesterDao semesterDao, ServerDao serverDao) {
        this.subjectSemesterDao = subjectSemesterDao;
        this.semesterDao = semesterDao;
        this.serverDao = serverDao;

        this.mapperFacade = mapperFacade;
    }

    @Cacheable(value = "mapForAll")
    public List<Mapping> getMappingForStudent() {
        Long semesterId = semesterDao.getCurrentSemesterId();
        if (semesterId > 0) {
            List<SubjectSemester> subjectSemesters = subjectSemesterDao.findBySemesterId(semesterId);
            List<Mapping> mappingList = new ArrayList<>();
            subjectSemesters.forEach(subjectSemester -> {
                Mapping mapping = new Mapping();
                Server server = serverDao.getById(subjectSemester.getServerId());
                mapping.setSubjectSemesterId(subjectSemester.getId());
                mapping.setHandleServer(server.getAddress());
                mappingList.add(mapping);
            });
            return mappingList;
        } else {
            return null;
        }
    }
    @CacheEvict(value = "mapForAll")
    public void evictCacheMapping() {}

    @CacheEvict(value = "listServerForAll")
    public void evictCacheListServer() {}

    @CacheEvict(value = "currentSemester")
    public void evictCacheSemester () {}

    @CacheEvict(value = "listSubjectIdOfStudent", allEntries = true)
    public void evictCacheStudentSubjects () {}

    public boolean setServerIdForSubjectSemester(Long subjectSemesterId, Long serverId) {
        return subjectSemesterDao.setServerIdForSubjectSemester(subjectSemesterId, serverId);
    }

}
