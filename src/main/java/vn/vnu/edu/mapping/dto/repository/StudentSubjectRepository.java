package vn.vnu.edu.mapping.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;

import java.util.List;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {
    List<StudentSubject> findByStudentIdAndSemesterId(Long studentId, Long subjectSemesterId);

    @Query("SELECT new vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember(s.subjectSemesterId, COUNT(s.studentId)) " +
            "FROM StudentSubject s " +
            "WHERE s.semesterId = ?1 " +
            "GROUP BY s.subjectSemesterId " +
            "ORDER BY COUNT(s.studentId) ASC")
    List<SubjectSemesterCountMember> getSubjectSemesterCountMemberBySemesterId(
        @Param("semesterId") Long semesterId
    );
}
