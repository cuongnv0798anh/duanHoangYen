package com.example.duan_dulich.common.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUlti {

    private DateUlti() {

    }
    private static final String DATE_GENERAL = "dd/MM/yyyy";

    public static String toStringDate(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(DATE_GENERAL);
        return dateFormat.format(date);
    }
    public static String ddMMyyyyToString(String date) throws ParseException {
        if(date==null){
            return null;
        }
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        DateFormat targetFormat = new SimpleDateFormat(DATE_GENERAL);
        Date dateTime = sourceFormat.parse(date);
        return targetFormat.format(dateTime);
    }

    public static Date stringToDate(String date) throws ParseException {
        if(date==null || date.equals("")){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(DATE_GENERAL);
        return dateFormat.parse(date);
    }
}
