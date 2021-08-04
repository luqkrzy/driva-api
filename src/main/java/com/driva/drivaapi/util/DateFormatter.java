package com.driva.drivaapi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
   
   public static String dateToString(Date date) {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      return formatter.format(date);
   }
}
