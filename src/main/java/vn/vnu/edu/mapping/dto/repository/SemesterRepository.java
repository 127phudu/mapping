package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.Semester;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester,Long> {
    Semester findByStatusIn(List<Integer> listStatus);
}
