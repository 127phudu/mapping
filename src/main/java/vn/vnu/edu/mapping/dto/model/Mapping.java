package vn.vnu.edu.mapping.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Mapping implements Serializable {
    private Long subjectId;
    private String handleServer;
}
