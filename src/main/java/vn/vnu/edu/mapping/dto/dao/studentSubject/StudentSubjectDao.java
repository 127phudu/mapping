package vn.vnu.edu.mapping.dto.dao.studentSubject;


import vn.vnu.edu.mapping.dto.model.StudentSubject;

import java.util.List;

public interface StudentSubjectDao {
    List<StudentSubject> findByStudentIdAndSemesterId(Long studentId, Long semesterId);
}
