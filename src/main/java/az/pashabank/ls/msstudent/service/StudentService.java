package az.pashabank.ls.msstudent.service;

import az.pashabank.ls.msstudent.client.CollegeClient;
import az.pashabank.ls.msstudent.exception.NotFoundException;
import az.pashabank.ls.msstudent.exception.NotValidRequestException;
import az.pashabank.ls.msstudent.mappers.StudentMapper;
import az.pashabank.ls.msstudent.model.dto.CollegeDto;
import az.pashabank.ls.msstudent.model.dto.StudentDto;
import az.pashabank.ls.msstudent.repository.entity.StudentEntity;
import az.pashabank.ls.msstudent.model.StudentRequest;
import az.pashabank.ls.msstudent.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CollegeClient collegeClient;

    public StudentService(StudentRepository studentRepository,
                          StudentMapper studentMapper,
                          CollegeClient collegeClient) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.collegeClient = collegeClient;
    }

    private static boolean isRequestValid(StudentRequest studentRequest){
        if (studentRequest.getName() == null ||
                studentRequest.getCollegeId() == null ||
                studentRequest.getSurname() == null ||
                studentRequest.getSurname().equals("")||
                studentRequest.getName().equals("")){
            return false;
        }

        return true;
    }

    public StudentDto findStudentById(Long id) {
        StudentEntity studentEntity = studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id: " + id + " is not found"));
        return studentMapper.mapEntityToDto(studentEntity);
    }

    public void createStudent(StudentRequest studentRequest) {
        if (studentRequest.getName() == null ||
                studentRequest.getCollegeId() == null ||
                studentRequest.getSurname() == null ||
                studentRequest.getSurname().equals("")||
        studentRequest.getName().equals("")){
            throw new NotValidRequestException("Student Request is not valid");
        }

            StudentEntity studentEntity = StudentEntity
                    .builder()
                    .name(studentRequest.getName())
                    .surname(studentRequest.getSurname())
                    .collegeId(studentRequest.getCollegeId())
                    .build();

            studentRepository.save(studentEntity);
    }

    public void deleteStudent(Long id) {
       studentRepository
               .findById(id)
               .orElseThrow(()-> new NotFoundException("Student with id : "+ id+"  not found"));

       studentRepository.deleteById(id);
    }

    public void updateStudent(Long id, StudentRequest studentRequest) {
        StudentEntity studentEntity= studentRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException("Student with id: "+ id+" not found"));

        if (StudentService.isRequestValid(studentRequest)){
            studentRepository.save(studentEntity);
        } else {
            //TODO LOGGING
        }
    }

    public List<CollegeDto> findStudentsByCity(String city) {
        return collegeClient.getCollegesByCity(city);
    }
}
