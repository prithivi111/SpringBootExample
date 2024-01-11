package org.example.service;

import org.example.entity.Department;
import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;
import org.example.model.DepartmentModel;
import org.example.model.StudentModel;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudentData(StudentModel studentModel) {
        //populate entity object
        Student student = new Student();
        student.setStudId(studentModel.getStudentId());
        student.setStudName(studentModel.getStudentName());
        student.setStudRoll(studentModel.getStudRoll());

        List<DepartmentModel> departmentModels = studentModel.getDepartments();
        for(DepartmentModel departmentModel:departmentModels){
            Department department = new Department();
            department.setDeptName(departmentModel.getDeptName());
            student.addDepartment(department);
            department.setStudent(student); //This was not written here by Abhisek sir
        }

        try {
            //save to DB
            this.studentRepository.save(student);
        } catch (Exception e) {
            System.err.println("Exception identified .. " + e.getMessage());
            return "Failed";
        }
        return "Success";
    }

    public List<StudentModel> fetchAllStudData() {
        List<Student> studentList = null;
        List<StudentModel> studentModels = new ArrayList<>();
      try{
          //fetch all rows of student table from DB
         studentList= (List<Student>) this.studentRepository.findAll();
      }catch(Exception e){
          System.out.println("Exception identified.."+ e.getMessage());
          return null;
      }
       if(!studentList.isEmpty()){
           for(Student student:studentList){
               StudentModel studentModel = new StudentModel();
               studentModel.setStudentId(student.getStudId());
               studentModel.setStudentName(student.getStudName());
               studentModel.setStudRoll(student.getStudRoll());
               studentModels.add(studentModel);
           }
       }
       return studentModels;
    }

    public StudentModel fetchStudentData(Integer studId) {
           Student student= this.studentRepository.findByStudId(studId);
            StudentModel studentModel = new StudentModel();
        if(student!=null){
            studentModel.setStudentId((student.getStudId()));
            studentModel.setStudentName((student.getStudName()));
            studentModel.setStudRoll(student.getStudRoll());
        }
        return studentModel;
    }

    public StudentModel fetchStudentInfo(Integer studId, String studName) {
        StudentModel studentModel = new StudentModel();
        Student student = null;
        try {
           // Optional<Student> studentOpsObj  = this.studentRepository.findByStudIdAndStudName(studId, studName);
            Optional<Student> studentOpsObj  = this.studentRepository.findByIdAndName(studId, studName);
            if (studentOpsObj.isPresent()) {
                student = studentOpsObj.get();
            }
        } catch (Exception e) {
            System.err.println("Exception identified .. " + e.getMessage());
            return new StudentModel();
        }
        if (student != null) {
            studentModel.setStudentId(student.getStudId());
            studentModel.setStudentName(student.getStudName());
            studentModel.setStudRoll(student.getStudRoll());
        }
        return studentModel;
    }

    public String updateStudentName(Integer stuId, StudentModel studentModel){
       Student student = null;
        try{
            Optional<Student> studentOptional= this.studentRepository.findById(stuId);
            if(studentOptional.isPresent()){
                student = studentOptional.get();
            }

        }catch(Exception e){
            System.out.println("Exception identified...");
            e.printStackTrace();
            return "Invalid Student ID";
            }
        if(student!=null && student.getStudId()!=null){
            student.setStudName(studentModel.getStudentName());
            student.setStudRoll(studentModel.getStudRoll());
            this.studentRepository.save(student);
            return "Update Successfully";
        } else{
            throw new StudentNotFoundException( "601", "The Given StudentID Is Not Correct"); //Yo 601, maile tetikai deko!
        }
    }

    public String deleteStudentData(Integer studId){
        try{
            this.studentRepository.deleteById(studId);
            return "Data deleted successfully";
        }catch(Exception e){
            System.out.println("Exception identified...");
            e.printStackTrace();
            return "Invalid Student ID";
        }
    }
}

