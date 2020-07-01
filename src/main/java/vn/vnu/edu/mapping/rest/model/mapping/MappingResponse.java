package vn.vnu.edu.mapping.rest.model.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappingResponse {
    @JsonProperty(value = "SubjectSemesterId")
    private Long subjectSemesterId;

    @JsonProperty(value = "SubjectName")
    private String subjectName;

    @JsonProperty(value = "ServerId")
    private String serverId;

    @JsonProperty(value = "NumberOfStudent")
    Long numberOfStudent;
}
