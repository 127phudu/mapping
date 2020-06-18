package vn.vnu.edu.mapping.dto.service;


import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.server.ServerDao;
import vn.vnu.edu.mapping.dto.model.Server;

import java.util.List;

@Service
public class ServerService {
    private final ServerDao serverDao;

    public ServerService(ServerDao serverDao) {
        this.serverDao = serverDao;
    }

    public List<Server> findAll () {
        return serverDao.findAll();
    };


}
