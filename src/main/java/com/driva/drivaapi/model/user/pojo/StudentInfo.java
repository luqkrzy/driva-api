package com.driva.drivaapi.model.user.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentInfo {
   
   private String fistName;
   private String lastName;
   private String email;
   private String phoneNumber;
}
