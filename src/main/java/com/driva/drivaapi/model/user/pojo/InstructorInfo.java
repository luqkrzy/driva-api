package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.user.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorInfo {
   
   private Long id;
   private String fistName;
   private String lastName;
   private String email;
   private String phoneNumber;
   
   public InstructorInfo(Instructor instructor) {
      this.id = instructor.getId();
      this.fistName = instructor.getFirstName();
      this.lastName = instructor.getLastName();
      this.email = instructor.getEmail();
      this.phoneNumber = Integer.toString(instructor.getPhoneNumber());
   }
}
