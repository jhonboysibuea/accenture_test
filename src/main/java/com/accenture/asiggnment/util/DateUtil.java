package com.accenture.asiggnment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final Date formatDateDb(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate=sdf.parse(date);

            return newDate;

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
