package vn.vnu.edu.mapping.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerRequest {
    @JsonProperty(value = "serverId")
    private Long serverId;

    @JsonProperty(value = "address")
    private String address;
}