package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.Subject;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("SELECT s FROM Subject AS s " +
            "JOIN SubjectSemester ss ON ss.subjectId = s.id " +
            "WHERE ss.id = ?1")
    Subject getSubjectBySubjectSemesterId( @Param("subjectSemesterId") Long subjectSemesterId);
}
 