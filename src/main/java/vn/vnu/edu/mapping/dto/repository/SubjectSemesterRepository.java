package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;

import java.util.List;

@Repository
public interface SubjectSemesterRepository extends JpaRepository<SubjectSemester,Long> {
    List<SubjectSemester> findBySemesterId(Long semesterId);
    SubjectSemester getById (Long subjectSemesterId);
}
