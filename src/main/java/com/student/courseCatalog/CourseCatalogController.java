package com.student.courseCatalog;

import com.netflix.appinfo.InstanceInfo;

import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CourseCatalogController {

    @Autowired
    EurekaClient eurekaClient;

    @RequestMapping("/")

    @HystrixCommand(fallbackMethod = "displayDefaultHome")

    public String displayDefaultHome(){
        return ("Hello " + "Oops,something went wrong!.Please try after sometime..");
    }
    public String getCourseRegistrationHome(){
        String courseRegistrationMessage = "";
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("CourseRegistration-Service",false);
        String courseRegistrationURl = instanceInfo.getHomePageUrl();
        RestTemplate restTemplate = new RestTemplate();
        courseRegistrationMessage = restTemplate.getForObject(courseRegistrationURl,String.class);
        return ("Hello " + courseRegistrationMessage);
    }


    @RequestMapping(method = RequestMethod.GET,value = "/getAllCourses")
    @HystrixCommand(fallbackMethod = "displayDefaulCourses")
    public String getAllCourses(){
        String courses ="";
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("CourseRegistration-Service",false);
        String courseRegistrationURl = instanceInfo.getHomePageUrl();
        courseRegistrationURl = courseRegistrationURl + "/getCourses";
        RestTemplate restTemplate = new RestTemplate();
        courses = restTemplate.getForObject(courseRegistrationURl,String.class);
        return ("Our Courses are : " + courses);
    }
    public String displayDefaulCourses(){
        return ("Oops! Page Not Found" + "Please try again later...");
    }

    @RequestMapping(method = RequestMethod.GET,value = "/FirstCourseStudents")
    public String getSpecificCourse(){

        Course course = new Course();
        Student student =  new Student();

        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("CourseRegistration-Service",false);
        String courseRegistrationURl = instanceInfo.getHomePageUrl();

        courseRegistrationURl=courseRegistrationURl+"/1";
        RestTemplate restTemplate =new RestTemplate();
        course = restTemplate.getForObject(courseRegistrationURl,Course.class);

        instanceInfo=eurekaClient.getNextServerFromEureka("StudentDetails-Service",false);
        String studentDetailsURL = instanceInfo.getHomePageUrl();
        studentDetailsURL= studentDetailsURL+ "/" + course.getCourseid();
        String studentList = restTemplate.getForObject(studentDetailsURL,String.class);

        return ("our first course is " + course.getCoursename() + " and enrolled users are "+ studentList );
    }


}

