package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.user.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInfo {
   
   private Long id;
   private String fistName;
   private String lastName;
   private String email;
   private String phoneNumber;
   
   public StudentInfo(Student student) {
      this.id = student.getId();
      this.fistName = student.getLastName();
      this.lastName = student.getLastName();
      this.email = student.getEmail();
      this.phoneNumber = student.getLastName();
   }
}
