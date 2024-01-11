package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stud_id")
    private Integer studId;

    @Column(name = "stud_name")
    private String studName;

    @Column(name = "stud_roll")
    private Integer studRoll;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Department> departments = new HashSet<>();

    //We created new method which will add all departments to a student
    public void addDepartment(Department department) {

        this.departments.add(department);
        //department.setStudent(this); I omitted this part and set the Student details in the student service class itself.
    }


    public Set<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    public Integer getStudRoll() {
        return studRoll;
    }

    public void setStudRoll(Integer studRoll) {
        this.studRoll = studRoll;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }
}