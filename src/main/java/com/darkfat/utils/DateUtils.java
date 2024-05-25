package com.darkfat.utils;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String format(Date date) {
        try {
            if (date == null) {
                return null;
            }
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(DATE_FORMAT);
            String formattedDate = outputDateFormat.format(date);           
            return outputDateFormat.parse(formattedDate).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

