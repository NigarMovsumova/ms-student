package az.pashabank.ls.msstudent.controller;

import az.pashabank.ls.msstudent.model.StudentRequest;
import az.pashabank.ls.msstudent.model.dto.StudentDto;
import az.pashabank.ls.msstudent.service.StudentService;
import az.pashabank.ls.msstudent.service.impl.StudentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@Api("Student Controller")
public class StudentController {

    private final StudentService studentService;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    @ApiOperation("find a student by id")
    public StudentDto getStudentById(@PathVariable Long id) {
        logger.debug("Get student of id {} start", id);
        return studentService.getStudentById(id);
    }

    @PostMapping("/in")
    @ApiOperation("find students list by city")
    public List<StudentDto> getStudentsByCity(@RequestBody String city) {
        logger.debug("Get students of city {} start", city);
        return studentService.getStudentsByCity(city);
    }

    @PostMapping
    @ApiOperation("create a student")
    public void createStudent(@RequestBody StudentRequest studentRequest) {
        logger.debug("Create student start");
        studentService.createStudent(studentRequest);
    }

    @PutMapping("/{id}")
    @ApiOperation("update an existing student")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        logger.debug("Update student of id {} start", id);
        studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete a student by id")
    public void deleteStudent(@PathVariable Long id) {
        logger.debug("Delete student of id {} start", id);
        studentService.deleteStudent(id);
        logger.debug("Delete student of id {} end", id);
    }
}
