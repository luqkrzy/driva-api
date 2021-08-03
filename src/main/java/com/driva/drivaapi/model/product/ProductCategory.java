package com.driva.drivaapi.model.product;

public enum ProductCategory {
   
   KURS(1), LEKCJA(2);
   
   private int index;
   
   ProductCategory(int index) {
	  this.index = index;
   }
   
   public int getIndex() {
	  return index;
   }
}
