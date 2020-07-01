package vn.vnu.edu.mapping.rest.model.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import vn.vnu.edu.mapping.rest.model.PageResponse;

import java.util.List;

@Getter
@Setter
public class ServerDetailListResponse {
    @JsonProperty(value = "ServerDetail")
    List<ServerResponse> servers;

    @JsonProperty(value = "Page")
    PageResponse pageResponse;

    public ServerDetailListResponse(List<ServerResponse> servers, PageResponse pageResponse) {
        this.servers = servers;
        this.pageResponse = pageResponse;
    }
}
