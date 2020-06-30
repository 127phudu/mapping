package vn.vnu.edu.mapping.rest.controller;

import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.StudentSubjectService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;

@RestController
@RequestMapping("/subjectSemester")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("/getIds/student/{studentId}")
    public ApiDataResponse getListSubjectSemesterIdForStudent(@PathVariable Long studentId) {
        try {
            return ApiDataResponse.ok(studentSubjectService.getListSubjectSemesterIdForStudent(studentId));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

}
