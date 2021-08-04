package com.driva.drivaapi.mapper.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InstructorInfo {
   
   private String fistName;
   private String lastName;
   private String email;
   private String phoneNumber;
}
