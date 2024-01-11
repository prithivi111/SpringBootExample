package org.example.controller;

import org.example.model.StudentModel;
import org.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic")
public class BasicController {
    @Autowired
    private BasicService basicService;

  /*public BasicController(BasicService basicService) {
        this.basicService = basicService;
    }*/

    //@RequestMapping(method = RequestMethod.GET, value = "/first")
    @GetMapping(value = "/first") //http://localhost:8080/springBasic/basic/first
    public StudentModel first() {
        StudentModel student = this.basicService.fetchStudentData();
        return student;
    }

    @GetMapping(value = "/test") // http://localhost:8082/springBasic/basic/test
    public int test() {
        System.out.println("inside Test");
        int receivedData = this.basicService.test();
        return receivedData;
    }

}