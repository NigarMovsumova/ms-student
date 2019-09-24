package az.pashabank.ls.msstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String name;
    private String surname;
    private Long collegeId;
}
