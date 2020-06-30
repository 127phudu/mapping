package vn.vnu.edu.mapping.dto.model.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectSemesterCountMember {
    private Long subjectSemesterId;
    private Long numberOfStudent;

    public SubjectSemesterCountMember(Long subjectSemesterId, Long numberOfStudent) {
        this.subjectSemesterId = subjectSemesterId;
        this.numberOfStudent = numberOfStudent;
    }
}
