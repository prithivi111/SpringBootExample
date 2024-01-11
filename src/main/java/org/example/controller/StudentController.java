package org.example.controller;

import org.example.model.StudentModel;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    //Please make sure that controller is only dealing with Model, but not Entity
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudentData(@RequestBody StudentModel studentModel) {
        String receivedResponse = this.studentService.saveStudentData(studentModel);
        if (receivedResponse.equalsIgnoreCase("Success")) {
            return new ResponseEntity("Successfully Saved Data..", HttpStatus.CREATED);
            //return ResponseEntity.status(HttpStatus.CREATED).body(receivedResponse);
        } else {
            return new ResponseEntity("Not Saved Data..", HttpStatus.BAD_REQUEST);
            //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetchAllStudData")
    public ResponseEntity<List<StudentModel>> fetchAllStudData() {
        List<StudentModel> studentModels=this.studentService.fetchAllStudData();
        if(!studentModels.isEmpty()){
            return ResponseEntity.of(Optional.of(studentModels));
            //return new ResponseEntity<>(studentModels, HttpStatus.OK);

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
           // return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetchStudData")
    public StudentModel fetchStudData(@RequestParam("stdId") int studId) {
        System.out.println("Received Student Id : " + studId);
        StudentModel studentModel = this.studentService.fetchStudentData(studId);
        return studentModel;
    }

    @GetMapping("/fetchStudInfo/{id}/{name}")
    public StudentModel fetchStudInfo(@PathVariable("id") int id, @PathVariable String name) {
        System.out.println("Received Student Id : "+id+ " :::  Student name ::" +name);
        StudentModel studentModel = this.studentService.fetchStudentInfo(id, name);
        return studentModel;
    }

    //update a book
    @PutMapping("/updateStudentName")
    public ResponseEntity<String> updateStudentName(@RequestParam ("studI") Integer studId, @RequestBody StudentModel studentModel){
        String receiveData= this.studentService.updateStudentName(studId, studentModel);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudData/{studId}")
    public ResponseEntity<String> deleteStudData(@PathVariable("studId") Integer studId) {
        return new ResponseEntity<>(this.studentService.deleteStudentData(studId), HttpStatus.OK);
    }
}
