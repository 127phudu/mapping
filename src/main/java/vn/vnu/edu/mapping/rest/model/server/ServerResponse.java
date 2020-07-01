package vn.vnu.edu.mapping.rest.model.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerResponse {
    @JsonProperty(value = "Id")
    private Long id;

    @JsonProperty(value = "Address")
    private String address;

    @JsonProperty(value = "NumberOfStudent")
    Long numberOfStudent;
}
