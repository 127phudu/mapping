package vn.vnu.edu.mapping.dto.model.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectSemesterCountMember {
    private Long subjectSemesterId;
    private Long numberOfStudent;
    private Long serverId;

    public SubjectSemesterCountMember(Long subjectSemesterId, Long numberOfStudent, Long serverId) {
        this.subjectSemesterId = subjectSemesterId;
        this.numberOfStudent = numberOfStudent;
        this.serverId = serverId;
    }
}
