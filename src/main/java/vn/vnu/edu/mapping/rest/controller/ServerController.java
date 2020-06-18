package vn.vnu.edu.mapping.rest.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import vn.vnu.edu.mapping.dto.service.ServerService;
import vn.vnu.edu.mapping.rest.model.ApiDataResponse;

@RestController
@RequestMapping("/server")
public class ServerController {

    private final ServerService serverService;

    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @Cacheable(value = "listServerForAll")
    @GetMapping("/list")
    public ApiDataResponse getServerList() {
        try {
            return ApiDataResponse.ok(serverService.findAll());
        } catch (Exception e) {
            return ApiDataResponse.error();
        }
    }
}
