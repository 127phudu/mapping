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
public class SubjectSemesterService {
    private final SubjectSemesterDao subjectSemesterDao;
    private final SemesterDao semesterDao;
    private final ServerDao serverDao;
    private final MapperFacade mapperFacade;

    public SubjectSemesterService(MapperFacade mapperFacade, SubjectSemesterDao subjectSemesterDao, SemesterDao semesterDao, ServerDao serverDao) {
        this.subjectSemesterDao = subjectSemesterDao;
        this.semesterDao = semesterDao;
        this.serverDao = serverDao;
        this.mapperFacade = mapperFacade;
    }

    public boolean setServerIdForSubjectSemester(Long subjectSemesterId, Long serverId) {
        return subjectSemesterDao.setServerIdForSubjectSemester(subjectSemesterId, serverId);
    }
}
