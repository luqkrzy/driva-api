package com.driva.drivaapi.model.user.old;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DateTest {
   
   //   @DateTimeFormat(pattern = "dd-MM-yyyy")
   //   Date date;
   //   @NotBlank()
   //   String date;
   
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   LocalDate localDate;
   
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   LocalDateTime localDateTime;
   
   @Override
   public String toString() {
      return "DateTest{" +
             //             "date=" + date +
             ", dateTime=" + localDate +
             //             ", localDateTime=" + localDateTime +
             '}';
   }
}
