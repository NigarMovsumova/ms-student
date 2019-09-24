package az.pashabank.ls.msstudent.mappers;

import az.pashabank.ls.msstudent.model.dto.StudentDto;
import az.pashabank.ls.msstudent.repository.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    public StudentDto mapEntityToDto(StudentEntity studentEntity) {
        return StudentDto.builder()
                .id(studentEntity.getId())
                .name(studentEntity.getName())
                .surname(studentEntity.getSurname())
                .collegeId(studentEntity.getCollegeId())
                .build();
    }

    public List<StudentDto> mapEntityListToDtoList(List<StudentEntity> studentEntities) {
        return studentEntities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }
}
