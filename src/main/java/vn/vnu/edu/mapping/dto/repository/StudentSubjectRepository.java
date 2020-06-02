package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.StudentSubject;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
    List<StudentSubject> findByStudentIdAndSemesterId(Long studentId, Long subjectSemesterId);
}
