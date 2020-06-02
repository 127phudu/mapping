package vn.vnu.edu.mapping.rest.controller;

import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.MappingService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.SetMappingRequest;

@RestController
@RequestMapping("/mapping")
public class MappingController {

    private final MappingService mappingService;

    public MappingController(MappingService mappingService) {
        this.mappingService = mappingService;
    }

    @GetMapping()
    public ApiDataResponse getMappingForStudent() {
        try {
            return ApiDataResponse.ok(mappingService.getMappingForStudent());
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @PatchMapping("/{subjectSemesterId}")
    public ApiDataResponse setServerIpForSubjectSemester(@PathVariable Long subjectSemesterId, @RequestBody SetMappingRequest setMappingRequest) {
        try {
            return ApiDataResponse.ok(mappingService.setServerIpForSubjectSemester(subjectSemesterId, setMappingRequest.getServerIp()));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @DeleteMapping("/emptyCache")
    public ApiDataResponse evictAllCache() {
        mappingService.evictCacheMapping();
        mappingService.evictCacheSemester();
        mappingService.evictCacheStudentSubjects();
        return ApiDataResponse.ok(true);
    }
}
