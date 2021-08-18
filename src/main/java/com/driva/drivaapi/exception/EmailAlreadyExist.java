package com.driva.drivaapi.exception;

public class EmailAlreadyExist extends RuntimeException {
   public EmailAlreadyExist(String message) {
      super(message);
   }
}
