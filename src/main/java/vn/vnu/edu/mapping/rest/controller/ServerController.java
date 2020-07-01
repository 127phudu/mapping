package vn.vnu.edu.mapping.rest.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.common.utilities.PageUtil;
import vn.vnu.edu.mapping.dto.service.ServerService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.PageBase;
import vn.vnu.edu.mapping.rest.model.ServerRequest;
import vn.vnu.edu.mapping.rest.model.server.ServerListResponse;

import java.util.List;

@RestController
@RequestMapping("/server")
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/list")
    public ApiDataResponse getServerList() {
        try {
            return ApiDataResponse.ok(serverService.findAll());
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @GetMapping("/listForAdmin")
    public ApiDataResponse<ServerListResponse> getServerListForAdmin(
        @RequestParam(required = false, value = "Size") Integer size,
        @RequestParam(required = false, value = "Page") Integer page
    ) {
        try {
            PageBase pageBase = PageUtil.validate(page, size);
            return ApiDataResponse.ok(serverService.findAllForAdmin(pageBase));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @GetMapping("/listDetailForAdmin/semester/{semesterId}")
    public ApiDataResponse<ServerListResponse> getServerListDetailForAdminBySemester(
        @RequestParam(required = false, value = "Size") Integer size,
        @RequestParam(required = false, value = "Page") Integer page,
        @PathVariable Long semesterId
    ) {
        try {
            PageBase pageBase = PageUtil.validate(page, size);
            return ApiDataResponse.ok(serverService.findAllDetailForAdmin(pageBase, semesterId));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @PostMapping()
    public ApiDataResponse createServer(@RequestBody ServerRequest request) {
        try {
            return ApiDataResponse.ok(serverService.create(request));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @PutMapping()
    public ApiDataResponse updateServer(@RequestBody ServerRequest request) {
        try {
            return ApiDataResponse.ok(serverService.update(request));
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }

    @DeleteMapping()
    public ApiDataResponse deleteList(@RequestBody List<Long> ids) {
        try {
            serverService.deleteList(ids);
            return ApiDataResponse.ok("success");
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }
}
