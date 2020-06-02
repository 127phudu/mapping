package vn.vnu.edu.mapping.dto.dao.subjectSemester;

import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;
import vn.vnu.edu.mapping.dto.repository.SubjectSemesterRepository;

import java.util.List;

@Service
public class SubjectSemesterImpl implements SubjectSemesterDao {
    private final SubjectSemesterRepository subjectSemesterRepository;

    public SubjectSemesterImpl(SubjectSemesterRepository subjectSemesterRepository) {
        this.subjectSemesterRepository = subjectSemesterRepository;
    }

    @Override
    public List<SubjectSemester> findBySemesterId(Long semesterId) {
        return subjectSemesterRepository.findBySemesterId(semesterId);
    }

    @Override
    public boolean setServerIpForSubjectSemester(Long subjectSemesterId, String serverIp) {
        SubjectSemester subjectSemester = subjectSemesterRepository.findById(subjectSemesterId).orElse(null);
        if (subjectSemester != null) {
            subjectSemester.setHandleServer(serverIp);
            subjectSemesterRepository.save(subjectSemester);
            return true;
        } else {
            return false;
        }
    };
}
