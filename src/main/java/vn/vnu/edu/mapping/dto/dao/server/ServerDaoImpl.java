package vn.vnu.edu.mapping.dto.dao.server;

import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.common.utilities.PageUtil;
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

    @Override
    public Server store(Server server) {
        return serverRepository.save(server);
    }

    @Override
    public void delete(Server server) {
        serverRepository.delete(server);
    }

    @Override
    public List<Server> findByIdIn(List<Long> ids) {
        return serverRepository.findByIdIn(ids);
    }

    @Override
    public void deleteList(List<Server> serverList) {
        serverRepository.deleteAll(serverList);
    }
}
