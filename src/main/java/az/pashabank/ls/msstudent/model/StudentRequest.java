package az.pashabank.ls.msstudent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private String surname;
    private Long collegeId;
}
