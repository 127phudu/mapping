package vn.vnu.edu.mapping.dto.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.rest.model.ServerRequest;

import java.util.List;

@Service
public class ServerService {
    private final ServerDao serverDao;

    public ServerService(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    @Cacheable(value = "listServerForAll")
    public List<Server> findAll () {
        return serverDao.findAll();
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
}
