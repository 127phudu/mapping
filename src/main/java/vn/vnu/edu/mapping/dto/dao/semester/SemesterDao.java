package vn.vnu.edu.mapping.dto.dao.semester;


import vn.vnu.edu.mapping.dto.model.Semester;

import java.util.List;

public interface SemesterDao {
    Semester findByStatusIn(List<Integer> listStatus);
    Long getCurrentSemesterId();
}
