package com.student.studentDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET,value = "/getStudents")
    public List<Student> getCourses(){
        return studentRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/newStudent")
    public void addNewStudent(@RequestBody Student student){
        studentRepository.save(student);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateStudent")
    public void updateStudent(@RequestBody Student student){
        studentRepository.save(student);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void deleteStudent(@PathVariable BigInteger id){
        studentRepository.deleteById(id);
    }
    @RequestMapping("{id}")
    public List<Student> getStudentForACourse(@PathVariable("id")BigInteger id){
        return studentRepository.findBycourseid(id);
    }

}
