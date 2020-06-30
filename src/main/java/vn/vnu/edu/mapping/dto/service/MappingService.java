package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.semester.SemesterDao;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.dao.studentSubject.StudentSubjectDao;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.Mapping;

import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    private final SubjectSemesterDao subjectSemesterDao;
    private final SemesterDao semesterDao;
    private final ServerDao serverDao;
    private final StudentSubjectDao studentSubjectDao;
    private final MapperFacade mapperFacade;


    public MappingService(MapperFacade mapperFacade, SubjectSemesterDao subjectSemesterDao, SemesterDao semesterDao, ServerDao serverDao, StudentSubjectDao studentSubjectDao) {
        this.subjectSemesterDao = subjectSemesterDao;
        this.semesterDao = semesterDao;
        this.serverDao = serverDao;
        this.studentSubjectDao = studentSubjectDao;

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

    public boolean autoSetMapping(Long semesterId) {
        List<SubjectSemesterCountMember> subjectSemesterCountMembers = studentSubjectDao.getSubjectSemesterCountMemberBySemesterId(semesterId);
        List<Server> serverList = serverDao.findAll();
        int countServer = serverList.size();
        if (countServer > 0) {
            int i = 0;
            for (SubjectSemesterCountMember countMember : subjectSemesterCountMembers) {
                SubjectSemester subjectSemester = subjectSemesterDao.getById(countMember.getSubjectSemesterId());
                subjectSemester.setServerId(serverList.get(i).getId());
                subjectSemesterDao.save(subjectSemester);
                i = (i + 1) % countServer;
            }
            return true;
        } else {
            return false;
        }
    }
}
