package az.pashabank.ls.msstudent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollegeDto {
    private Long id;
    private String name;
    private String city;
}
