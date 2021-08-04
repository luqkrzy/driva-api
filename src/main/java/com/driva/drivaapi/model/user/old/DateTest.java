package com.driva.drivaapi.model.user.old;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class DateTest {
   
   @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
   Date date;
   
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   LocalDate dateTime;
   
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   LocalDateTime localDateTime;
   
   @Override
   public String toString() {
      return "DateTest{" +
             "date=" + date +
             ", dateTime=" + dateTime +
             ", localDateTime=" + localDateTime +
             '}';
   }
}
