package org.example.model;

import java.util.List;

public class StudentModel {
        private Integer studentId;
        private String studentName;
        private Integer studRoll;
        private List<DepartmentModel> departments;

    public List<DepartmentModel> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentModel> departments) {
        this.departments = departments;
    }

    public Integer getStudRoll() {
            return studRoll;
        }

        public void setStudRoll(Integer studRoll) {
            this.studRoll = studRoll;
        }

        public Integer getStudentId() {
            return studentId;
        }

        public void setStudentId(Integer studentId) {
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }
