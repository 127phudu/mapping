package vn.vnu.edu.mapping.dto.dao.studentSubject;


import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;

import java.util.List;

public interface StudentSubjectDao {
    List<StudentSubject> findByStudentIdAndSemesterId(Long studentId, Long semesterId);
    List<SubjectSemesterCountMember> getSubjectSemesterCountMemberBySemesterId(Long semesterId);
}
