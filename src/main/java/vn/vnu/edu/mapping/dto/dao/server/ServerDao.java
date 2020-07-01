package vn.vnu.edu.mapping.dto.dao.server;

import vn.vnu.edu.mapping.dto.model.Server;

import java.util.List;

public interface ServerDao {
    Server getById(Long serverId);
    List<Server> findAll();
    List<Server> findByIdIn(List<Long> ids);
    Server store(Server server);
    void delete(Server server);
    void deleteList(List<Server> serverList);

}
