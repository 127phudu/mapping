package vn.vnu.edu.mapping.rest.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.ServerService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;
import vn.vnu.edu.mapping.rest.model.ServerRequest;

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

    @DeleteMapping("/{id}")
    public ApiDataResponse deleteServer(@PathVariable Long id) {
        try {
            serverService.delete(id);
            return ApiDataResponse.ok("success");
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }
}
