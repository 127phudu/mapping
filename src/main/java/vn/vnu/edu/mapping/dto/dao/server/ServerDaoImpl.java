package vn.vnu.edu.mapping.dto.dao.server;

import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.model.Server;
import vn.vnu.edu.mapping.dto.repository.ServerRepository;

import java.util.List;

@Service
public class ServerDaoImpl implements ServerDao {
    private final ServerRepository serverRepository;

    public ServerDaoImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public Server getById(Long serverId) {
        return serverRepository.getById(serverId);
    }

    @Override
    public List<Server> findAll() {
        return serverRepository.findAll();
    };
}
