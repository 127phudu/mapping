package vn.vnu.edu.mapping.rest.controller;

import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.ServerService;
import vn.vnu.edu.mapping.dto.service.SubjectSemesterService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.SetMappingRequest;

@RestController
@RequestMapping("/subjectSemester")
public class SubjectSemesterController {

    private final SubjectSemesterService subjectSemesterService;

    public SubjectSemesterController(SubjectSemesterService subjectSemesterService) {
        this.subjectSemesterService = subjectSemesterService;
    }

    @PatchMapping("/{subjectSemesterId}/setServer")
    public ApiDataResponse setServerIdForSubjectSemester(@PathVariable Long subjectSemesterId, @RequestBody SetMappingRequest setMappingRequest) {
        try {
            return ApiDataResponse.ok(subjectSemesterService.setServerIdForSubjectSemester(subjectSemesterId, setMappingRequest.getServerId()));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }
}
