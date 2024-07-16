package com.uk.management.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    private CommonUtils(){
        throw new IllegalStateException("Utility class cannot be instantiated");
    }
    public static Date getDate(String date){
        Date date1= null;
        try {
             date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        }
        catch (ParseException ex){
            ex.printStackTrace();
        }

        return date1;

    }


}
