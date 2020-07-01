package vn.vnu.edu.mapping.rest.model.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMappingRequest {
    @JsonProperty(value = "SubjectSemesterId")
    private Long subjectSemesterId;

    @JsonProperty(value = "ServerId")
    private Long serverId;
}