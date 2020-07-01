package vn.vnu.edu.mapping.rest.model.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import vn.vnu.edu.mapping.rest.model.PageResponse;
import vn.vnu.edu.mapping.rest.model.server.ServerResponse;

import java.util.List;

@Getter
@Setter
public class MappingListResponse {
    @JsonProperty(value = "Mapping")
    List<MappingResponse> mappings;

    @JsonProperty(value = "Page")
    PageResponse pageResponse;

    public MappingListResponse(List<MappingResponse> mappings, PageResponse pageResponse) {
        this.mappings = mappings;
        this.pageResponse = pageResponse;
    }
}
