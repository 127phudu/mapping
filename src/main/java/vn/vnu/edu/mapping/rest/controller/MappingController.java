package vn.vnu.edu.mapping.rest.controller;

import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.common.utilities.PageUtil;
import vn.vnu.edu.mapping.dto.service.MappingService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.PageBase;
import vn.vnu.edu.mapping.rest.model.SetMappingRequest;
import vn.vnu.edu.mapping.rest.model.mapping.UpdateMappingRequest;

import java.util.List;

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

    @GetMapping("/listForAdmin/semester/{semesterId}")
    public ApiDataResponse getMappingForStudent(
        @RequestParam(required = false, value = "Size") Integer size,
        @RequestParam(required = false, value = "Page") Integer page,
        @PathVariable Long semesterId
    ) {
        try {
            PageBase pageBase = PageUtil.validate(page, size);
            return ApiDataResponse.ok(mappingService.getMappingForAdmin(pageBase ,semesterId));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @DeleteMapping("/emptyCache")
    public ApiDataResponse evictAllCache() {
        mappingService.evictCacheMapping();
        mappingService.evictCacheListServer();
        mappingService.evictCacheSemester();
        mappingService.evictCacheStudentSubjects();
        return ApiDataResponse.ok(true);
    }

    @PutMapping("/autoSet/semester/{semesterId}")
    public ApiDataResponse autoSetMapping(@PathVariable Long semesterId) {
        try {
            if (mappingService.autoSetMapping(semesterId)) {
                return ApiDataResponse.ok("success");
            } else {
                return ApiDataResponse.ok("there is no server");
            }
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @PutMapping("/updateList")
    public ApiDataResponse updateListMapping(@RequestBody List<UpdateMappingRequest> requests) {
        try {
            mappingService.updateListMapping(requests);
            return ApiDataResponse.ok("success");
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }
}
