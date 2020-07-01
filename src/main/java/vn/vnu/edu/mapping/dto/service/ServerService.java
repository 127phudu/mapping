package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.rest.model.PageBase;
import vn.vnu.edu.mapping.rest.model.PageResponse;
import vn.vnu.edu.mapping.rest.model.ServerRequest;
import vn.vnu.edu.mapping.rest.model.server.ServerListResponse;
import vn.vnu.edu.mapping.rest.model.server.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServerService {
    private final ServerDao serverDao;
    private final MapperFacade mapperFacade;

    public ServerService(ServerDao serverDao, MapperFacade mapperFacade) {
        this.serverDao = serverDao;
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
}
