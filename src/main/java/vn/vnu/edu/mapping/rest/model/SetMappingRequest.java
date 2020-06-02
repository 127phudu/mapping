package vn.vnu.edu.mapping.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetMappingRequest {
    @JsonProperty(value = "serverIp")
    private String serverIp;

}