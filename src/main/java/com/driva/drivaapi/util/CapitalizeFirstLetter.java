package com.driva.drivaapi.util;

public class CapitalizeFirstLetter {

    public static String capitalize(String word) {

        String firstLetStr = word.substring(0, 1).toUpperCase();
        String remLetStr = word.substring(1);
        return firstLetStr + remLetStr;

    }
}
