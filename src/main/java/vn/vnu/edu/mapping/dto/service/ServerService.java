package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.dao.studentSubject.StudentSubjectDao;
import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;
import vn.vnu.edu.mapping.rest.model.PageBase;
import vn.vnu.edu.mapping.rest.model.PageResponse;
import vn.vnu.edu.mapping.rest.model.ServerRequest;
import vn.vnu.edu.mapping.rest.model.server.ServerDetailListResponse;
import vn.vnu.edu.mapping.rest.model.server.ServerListResponse;
import vn.vnu.edu.mapping.rest.model.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServerService {
    private final ServerDao serverDao;
    private final StudentSubjectDao studentSubjectDao;
    private final MapperFacade mapperFacade;

    public ServerService(ServerDao serverDao, MapperFacade mapperFacade, StudentSubjectDao studentSubjectDao) {
        this.serverDao = serverDao;
        this.studentSubjectDao = studentSubjectDao;
        this.mapperFacade = mapperFacade;
    }

    @Cacheable(value = "listServerForAll")
    public List<Server> findAll () {
        return serverDao.findAll();
    };

    public ServerListResponse findAllForAdmin (PageBase pageBase) {
        List<Server> serverList = serverDao.findAll();
        return getListServerPaging(serverList, pageBase);
    };

    public ServerDetailListResponse findAllDetailForAdmin (PageBase pageBase, Long semesterId) {
        List<Server> serverList = serverDao.findAll();
        return getListServerDetailPaging(serverList, pageBase, semesterId);
    };

    public Server create(ServerRequest serverRequest) {
        Server server = new Server();
        server.setAddress(serverRequest.getAddress());

        return serverDao.store(server);
    }

    public Server update(ServerRequest serverRequest) {
        Server server = new Server();
        server.setId(serverRequest.getServerId());
        server.setAddress(serverRequest.getAddress());

        return serverDao.store(server);
    }

    public void delete(Long id) {
        Server server = serverDao.getById(id);
        if (server != null) {
            serverDao.delete(server);
        }
    }

    public void deleteList(List<Long> ids) {
        List<Server> serverList = serverDao.findByIdIn(ids);
        serverDao.deleteList(serverList);
    }

    private ServerListResponse getListServerPaging(List<Server> servers, PageBase pageBase) {
        List<Server> serverList = new ArrayList<>();
        Integer page = pageBase.getPage();
        Integer size = pageBase.getSize();
        int total = servers.size();
        int maxSize = Math.min(total, size * page);
        for (int i = size * (page - 1); i < maxSize; i++) {
            serverList.add(servers.get(i));
        }
        PageResponse pageResponse = new PageResponse(page, size, total);
        return new ServerListResponse(mapperFacade.mapAsList(serverList, ServerResponse.class), pageResponse);
    }

    private ServerDetailListResponse getListServerDetailPaging(List<Server> servers, PageBase pageBase, Long semesterId) {
        List<Server> serverList = new ArrayList<>();
        Integer page = pageBase.getPage();
        Integer size = pageBase.getSize();
        int total = servers.size();
        int maxSize = Math.min(total, size * page);
        for (int i = size * (page - 1); i < maxSize; i++) {
            serverList.add(servers.get(i));
        }

        List<ServerResponse> serverResponseList = mapperFacade.mapAsList(serverList, ServerResponse.class);
        List<SubjectSemesterCountMember> subjectSemesterCountMembers = studentSubjectDao.getSubjectSemesterCountMemberBySemesterId(semesterId);

        for(int i = 0; i < serverResponseList.size(); i++) {
            for (int j = 0; j < subjectSemesterCountMembers.size(); j++){
                if (serverResponseList.get(i).getId().equals(subjectSemesterCountMembers.get(j).getServerId())){
                    Long current = serverResponseList.get(i).getNumberOfStudent();
                    if (current == null) {
                        current = 0L;
                    }
                    serverResponseList.get(i).setNumberOfStudent(current  +
                        subjectSemesterCountMembers.get(j).getNumberOfStudent()
                    );
                }
            }
        }

        PageResponse pageResponse = new PageResponse(page, size, total);
        ServerDetailListResponse serverDetailListResponse = new ServerDetailListResponse(serverResponseList, pageResponse);

        return serverDetailListResponse;
    }
}
