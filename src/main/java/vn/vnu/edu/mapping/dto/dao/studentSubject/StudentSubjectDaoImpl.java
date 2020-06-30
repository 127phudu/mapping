package vn.vnu.edu.mapping.dto.dao.studentSubject;

import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.custom.SubjectSemesterCountMember;
import vn.vnu.edu.mapping.dto.repository.StudentSubjectRepository;

import java.util.List;

@Service
public class StudentSubjectDaoImpl implements StudentSubjectDao {
    private final StudentSubjectRepository studentSubjectRepository;

    public StudentSubjectDaoImpl(StudentSubjectRepository studentSubjectRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
    }

    @Override
    public List<StudentSubject> findByStudentIdAndSemesterId(Long studentId, Long semesterId) {
        return studentSubjectRepository.findByStudentIdAndSemesterId(studentId, semesterId);
    }

    @Override
    public List<SubjectSemesterCountMember> getSubjectSemesterCountMemberBySemesterId(Long semesterId) {
        return studentSubjectRepository.getSubjectSemesterCountMemberBySemesterId(semesterId);
    }

}
