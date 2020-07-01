package vn.vnu.edu.mapping.dto.dao.subject;

import vn.vnu.edu.mapping.dto.model.Subject;

import java.util.List;

public interface SubjectDao {
    Subject getSubjectBySubjectSemesterId(Long subjectSemesterId);
}
