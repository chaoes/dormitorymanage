package me.wtclmy.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/7/8
 **/


public class MyDate {
    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
