package vn.vnu.edu.mapping.dto.dao.subjectSemester;

import vn.vnu.edu.mapping.dto.model.SubjectSemester;

import java.util.List;

public interface SubjectSemesterDao {
    List<SubjectSemester> findBySemesterId(Long semesterId);
    boolean setServerIdForSubjectSemester(Long subjectSemesterId, Long serverId);
}
