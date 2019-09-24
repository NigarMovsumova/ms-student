package az.pashabank.ls.msstudent.controller;

import az.pashabank.ls.msstudent.model.dto.CollegeDto;
import az.pashabank.ls.msstudent.model.dto.StudentDto;
import az.pashabank.ls.msstudent.model.StudentRequest;
import az.pashabank.ls.msstudent.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    @ApiOperation("find a student by id")
    public StudentDto findStudentById(@PathVariable Long id){
        return studentService.findStudentById(id);
    }

    @GetMapping("/in/{city}")
    @ApiOperation("find students list by city")
    public List<CollegeDto> findStudentsByCity(@PathVariable String city){
        return studentService.findStudentsByCity(city);
    }

    @PostMapping
    @ApiOperation("create a student")
    public void createStudent(@RequestBody StudentRequest studentRequest){
       studentService.createStudent(studentRequest);
    }

    @PutMapping("/{id}")
    @ApiOperation("update an existing student")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest){
        studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("{id}")
    @ApiOperation("delete a student by id")
    public void deleteStudent(@PathVariable Long id){
       studentService.deleteStudent(id);
    }
}
