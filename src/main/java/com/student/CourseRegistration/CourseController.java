package com.student.CourseRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String getCourseAppHome(){
        return ("Welcome to Course Catalog Home");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getCourses")
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/newCourse")
    public void addNewCourse(@RequestBody Course course){
        courseRepository.save(course);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateCourse")
    public void updateCourse(@RequestBody Course course){
        courseRepository.save(course);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "{id}")
    public void deleteCourse(@PathVariable BigInteger id){
        courseRepository.deleteById(id);
    }
    @RequestMapping("{id}")
    public Course getSpecificCourse(@PathVariable("id") BigInteger id){
        return courseRepository.getOne(id);
    }


}
