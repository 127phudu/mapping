package vn.vnu.edu.mapping.rest.controller;

import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.StudentSubjectService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.SetMappingRequest;

@RestController
@RequestMapping("/subject")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("/getIds/{studentId}")
    public ApiDataResponse getListSubjectIdForStudent(@PathVariable Long studentId) {
        try {
            return ApiDataResponse.ok(studentSubjectService.getListSubjectIdForStudent(studentId));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

}
