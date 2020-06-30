package vn.vnu.edu.mapping.dto.service;


import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vn.vnu.edu.mapping.config.Constant;
import vn.vnu.edu.mapping.dto.dao.semester.SemesterDao;
import vn.vnu.edu.mapping.dto.dao.studentSubject.StudentSubjectDao;
import vn.vnu.edu.mapping.dto.dao.subjectSemester.SubjectSemesterDao;
import vn.vnu.edu.mapping.dto.model.Mapping;
import vn.vnu.edu.mapping.dto.model.Semester;
import vn.vnu.edu.mapping.dto.model.StudentSubject;
import vn.vnu.edu.mapping.dto.model.SubjectSemester;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSubjectService {

    private final StudentSubjectDao studentSubjectDao;
    private final SemesterDao semesterDao;
    private final MapperFacade mapperFacade;


    public StudentSubjectService(MapperFacade mapperFacade, StudentSubjectDao studentSubjectDao, SemesterDao semesterDao) {
        this.studentSubjectDao = studentSubjectDao;
        this.semesterDao = semesterDao;

        this.mapperFacade = mapperFacade;
    }

    @Cacheable(value = "listSubjectSemesterIdOfStudent", key = "#studentId")
    public List<Long> getListSubjectSemesterIdForStudent(Long studentId) {
        Long semesterId = semesterDao.getCurrentSemesterId();
        List<Long> listSubjectSemesterId = new ArrayList<Long>();
        if (semesterId > 0) {
            List<StudentSubject> studentSubjects = studentSubjectDao.findByStudentIdAndSemesterId(studentId, semesterId);
            for (StudentSubject studentSubject : studentSubjects) {
                listSubjectSemesterId.add(studentSubject.getSubjectSemesterId());
            }

        }
        return listSubjectSemesterId;
    }
}
