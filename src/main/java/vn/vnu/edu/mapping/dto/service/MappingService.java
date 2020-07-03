package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.semester.SemesterDao;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.dao.studentSubject.StudentSubjectDao;
import vn.vnu.edu.mapping.dto.dao.subject.SubjectDao;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.Mapping;

import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;
import vn.vnu.edu.mapping.rest.model.PageBase;
import vn.vnu.edu.mapping.rest.model.PageResponse;
import vn.vnu.edu.mapping.rest.model.mapping.MappingListResponse;
import vn.vnu.edu.mapping.rest.model.mapping.MappingResponse;
import vn.vnu.edu.mapping.rest.model.mapping.UpdateMappingRequest;
import vn.vnu.edu.mapping.rest.model.server.ServerResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MappingService {

    private final SubjectSemesterDao subjectSemesterDao;
    private final SemesterDao semesterDao;
    private final ServerDao serverDao;
    private final SubjectDao subjectDao;
    private final StudentSubjectDao studentSubjectDao;
    private final MapperFacade mapperFacade;


    public MappingService(MapperFacade mapperFacade, SubjectSemesterDao subjectSemesterDao, SemesterDao semesterDao, ServerDao serverDao, StudentSubjectDao studentSubjectDao, SubjectDao subjectDao) {
        this.subjectSemesterDao = subjectSemesterDao;
        this.semesterDao = semesterDao;
        this.serverDao = serverDao;
        this.subjectDao = subjectDao;
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
            int key;
            int step = 1;
            for (SubjectSemesterCountMember countMember : subjectSemesterCountMembers) {
                if (i == -1) {
                    i = 0;
                    step = - step;
                } else if (i == countServer) {
                    i = countServer - 1;
                    step = - step;
                }
                key = i;
                SubjectSemester subjectSemester = subjectSemesterDao.getById(countMember.getSubjectSemesterId());
                subjectSemester.setServerId(serverList.get(key).getId());
                subjectSemesterDao.save(subjectSemester);
                i += step;
            }
            return true;
        } else {
            return false;
        }
    }

    public MappingListResponse getMappingForAdmin(PageBase pageBase, Long semesterId) {
        List<SubjectSemesterCountMember> subjectSemesterCountMembers = studentSubjectDao.getSubjectSemesterCountMemberBySemesterId(semesterId);
        List<MappingResponse> mappingList = mapperFacade.mapAsList(subjectSemesterCountMembers, MappingResponse.class);
        for (MappingResponse mapping : mappingList) {
            mapping.setSubjectName(
                subjectDao.getSubjectBySubjectSemesterId(mapping.getSubjectSemesterId()
            ).getSubjectName());
        }
        return getMappingForAdminPaging(mappingList, pageBase);
    }

    private MappingListResponse getMappingForAdminPaging(List<MappingResponse> mappingList, PageBase pageBase) {
        List<MappingResponse> mappings = new ArrayList<>();
        Integer size = pageBase.getSize();
        Integer page = pageBase.getPage();
        int total = mappingList.size();
        int maxSize = Math.min(total, size * page);
        for (int i = size * (page - 1); i < maxSize; i++) {
            mappings.add(mappingList.get(i));
        }
        PageResponse pageResponse = new PageResponse(page, size, total);
        return new MappingListResponse(mappings, pageResponse);
    }

    public void updateListMapping(List<UpdateMappingRequest> requests) {
        Map<Long, UpdateMappingRequest> mapData = new HashMap<>();
        List<Long> listIds = new ArrayList<>();
        for (UpdateMappingRequest request : requests) {
            mapData.put(request.getSubjectSemesterId(), request);
            listIds.add(request.getSubjectSemesterId());
        }
        List<SubjectSemester> subjectSemesters = subjectSemesterDao.getSubjectSemesterInList(listIds);

        for (int i = 0; i < subjectSemesters.size(); i++) {
            UpdateMappingRequest updateMapping = mapData.get(subjectSemesters.get(i).getId());
            subjectSemesters.get(i).setServerId(updateMapping.getServerId());
        }
        subjectSemesterDao.storeAll(subjectSemesters);
    }
}
