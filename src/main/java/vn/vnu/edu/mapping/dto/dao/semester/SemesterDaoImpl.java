package vn.vnu.edu.mapping.dto.dao.semester;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.config.Constant;
import vn.vnu.edu.mapping.dto.model.Semester;
import vn.vnu.edu.mapping.dto.repository.SemesterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemesterDaoImpl implements SemesterDao{
    private final SemesterRepository semesterRepository;

    public SemesterDaoImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Semester findByStatusIn(List<Integer> listStatus) {
        return  semesterRepository.findByStatusIn(listStatus);
    }

    @Override
    @Cacheable(value = "currentSemester")
    public Long getCurrentSemesterId() {
        List<Integer> listStatus = new ArrayList<Integer>();
        listStatus.add(Constant.REGISTERING);
        Semester semester = semesterRepository.findByStatusIn(listStatus);

        if(semester != null) {
            return semester.getId();
        } else {
            return 0L;
        }
    }
}
