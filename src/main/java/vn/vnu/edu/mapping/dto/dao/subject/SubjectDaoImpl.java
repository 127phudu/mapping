package vn.vnu.edu.mapping.dto.dao.subject;

import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.model.Subject;
import vn.vnu.edu.mapping.dto.repository.SubjectRepository;

import java.util.List;

@Service
public class SubjectDaoImpl implements SubjectDao {
    private final SubjectRepository subjectRepository;

    public SubjectDaoImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject getSubjectBySubjectSemesterId(Long subjectSemesterId) {
        return subjectRepository.getSubjectBySubjectSemesterId(subjectSemesterId);
    }
}
