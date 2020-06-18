package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.Server;

import java.util.List;

@Repository
public interface ServerRepository extends JpaRepository<Server,Long> {
    Server getById(Long serverId);
    List<Server> findAll();
}
 