package az.pashabank.ls.msstudent.service;

import az.pashabank.ls.msstudent.model.StudentRequest;
import az.pashabank.ls.msstudent.model.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto getStudentById(Long id);

    void createStudent(StudentRequest studentRequest);

    void deleteStudent(Long id);

    void updateStudent(Long id, StudentRequest studentRequest);

    List<StudentDto> getStudentsByCity(String city);
}
