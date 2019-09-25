package az.pashabank.ls.msstudent.service.impl;

import az.pashabank.ls.msstudent.client.CollegeClient;
import az.pashabank.ls.msstudent.exception.NotFoundException;
import az.pashabank.ls.msstudent.exception.NotValidRequestException;
import az.pashabank.ls.msstudent.mappers.StudentMapper;
import az.pashabank.ls.msstudent.model.StudentRequest;
import az.pashabank.ls.msstudent.model.dto.CollegeDto;
import az.pashabank.ls.msstudent.model.dto.StudentDto;
import az.pashabank.ls.msstudent.repository.StudentRepository;
import az.pashabank.ls.msstudent.repository.entity.StudentEntity;
import az.pashabank.ls.msstudent.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CollegeClient collegeClient;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper,
                              CollegeClient collegeClient) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.collegeClient = collegeClient;
    }

    public StudentDto getStudentById(Long id) {
        logger.info("ActionLog.getStudentById.start : id {}", id);

        StudentEntity studentEntity = studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id: " + id + " is not found"));
        logger.info("ActionLog.getStudentById.success : id {}", id);

        return studentMapper.mapEntityToDto(studentEntity);
    }

    public void createStudent(StudentRequest studentRequest) {
        logger.info("ActionLog.createStudent.start");

        //TODO good to use validators instead of this
        if (studentRequest.getName() == null ||
                studentRequest.getCollegeId() == null ||
                studentRequest.getSurname() == null ||
                studentRequest.getSurname().equals("") ||
                studentRequest.getName().equals("")) {
            throw new NotValidRequestException("Student Request is not valid");
        }

        logger.info("ActionLog.collegeClient.getCollegeById.start : collegeId {}", studentRequest.getCollegeId());

        try {
            collegeClient.getCollegeById(studentRequest.getCollegeId());
        }
        catch (Exception e){
            logger.warn("ActionLog.collegeClient.getCollegeById : collegeId {}", studentRequest.getCollegeId());
        }

        StudentEntity studentEntity = StudentEntity
                .builder()
                .name(studentRequest.getName())
                .surname(studentRequest.getSurname())
                .collegeId(studentRequest.getCollegeId())
                .build();

        studentRepository.save(studentEntity);
        logger.info("ActionLog.createStudent.success");
    }

    public void deleteStudent(Long id) {
        logger.info("ActionLog.deleteStudent.start: id {}", id);

        studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id : " + id + "  not found"));

        studentRepository.deleteById(id);
        logger.info("ActionLog.deleteStudent.success");
    }

    public void updateStudent(Long id, StudentRequest studentRequest) {
        logger.info("ActionLog.updateStudent.start: id{}", id);

        StudentEntity studentEntity = studentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id: " + id + " not found"));

        if (studentRequest.getCollegeId() != null) {
            studentEntity.setCollegeId(studentRequest.getCollegeId());
        }

        if (studentRequest.getName() != null || !studentRequest.getName().equals("")) {
            studentEntity.setName(studentRequest.getName());
        }

        if (studentRequest.getSurname() != null || !studentRequest.getSurname().equals("")) {
            studentEntity.setSurname(studentRequest.getSurname());
        }

        studentRepository.save(studentEntity);
        logger.info("ActionLog.updateStudent.success: id{}", id);
    }

    public List<StudentDto> getStudentsByCity(String city) {
        logger.info("ActionLog.getStudentsByCity.start: city{}", city);

        List<Long> collegeIds = collegeClient
                .getCollegesByCity(city)
                .stream()
                .map(CollegeDto::getId).collect(Collectors.toList());

        List<StudentEntity> studentEntities = studentRepository.findAllByCollegeIdIn(collegeIds);
        logger.info("ActionLog.getStudentsByCity.success: city{}", city);

        return studentMapper.mapEntityListToDtoList(studentEntities);
    }
}
