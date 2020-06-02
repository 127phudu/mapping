package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.semester.SemesterDao;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.Mapping;
import vn.vnu.edu.mapping.dto.model.Semester;

import vn.vnu.edu.mapping.config.Constant;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {

    private final SubjectSemesterDao subjectSemesterDao;
    private final SemesterDao semesterDao;
    private final MapperFacade mapperFacade;


    public MappingService(MapperFacade mapperFacade, SubjectSemesterDao subjectSemesterDao, SemesterDao semesterDao) {
        this.subjectSemesterDao = subjectSemesterDao;
        this.semesterDao = semesterDao;

        this.mapperFacade = mapperFacade;
    }

    @Cacheable(value = "mapForAll")
    public List<Mapping> getMappingForStudent() {
        Long semesterId = semesterDao.getCurrentSemesterId();
        if (semesterId > 0) {
            List<SubjectSemester> subjectSemesters = subjectSemesterDao.findBySemesterId(semesterId);
            return mapperFacade.mapAsList(subjectSemesters, Mapping.class);
        } else {
            return null;
        }
    }
    @CacheEvict(value = "mapForAll")
    public void evictCacheMapping() {}

    @CacheEvict(value = "currentSemester")
    public void evictCacheSemester () {}

    @CacheEvict(value = "listSubjectIdOfStudent", allEntries = true)
    public void evictCacheStudentSubjects () {}

    public boolean setServerIpForSubjectSemester(Long subjectSemesterId, String serverIP) {
        return subjectSemesterDao.setServerIpForSubjectSemester(subjectSemesterId, serverIP);
    }

}
