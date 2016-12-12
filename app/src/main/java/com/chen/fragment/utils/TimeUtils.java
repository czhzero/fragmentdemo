package com.chen.fragment.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenzhaohua on 16/11/15.
 */
public class TimeUtils {

    /**
     * UTC time to format
     *
     * @param utcTime
     * @param format  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTo(String utcTime, String format) {
        if (utcTime == null) {
            return "";
        }

        try {

            String timeZone;
            if (!utcTime.endsWith("Z")) {
                //末尾不包含Z,则不需要减去8小时
                utcTime += "Z";
                timeZone = "+0800";
            } else {
                timeZone = "+0000";
            }

            Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).parse(utcTime.replaceAll("Z$", timeZone));
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return utcTime;
    }

    /**
     * long time to format
     * @param time
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTo(long time, String format) {
        try {
            Date date = new Date(time);
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {

        }

        return "";
    }


    /**
     * date time to format
     * @param date
     * @param format  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTo(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {

        }

        return "";
    }


    /**
     * 将utc时间转换为当前时区的毫秒值
     *
     * @param utcTime
     * @return
     */
    public static long convertToMills(String utcTime) {
        if (TextUtils.isEmpty(utcTime)) {
            return 0;
        }

        try {

            String timeZone;
            if (!utcTime.endsWith("Z")) {
                //末尾不包含Z,则不需要减去8小时
                utcTime += "Z";
                timeZone = "+0800";
            } else {
                timeZone = "+0000";
            }

            Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).parse(utcTime.replaceAll("Z$", timeZone));
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 将年月日转换为当前时区的毫秒值
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long convertToMills(int year, int month, int day) {
        String utcTime = convertToUtcTime(year, month, day);
        return convertToMills(utcTime);
    }




    /**
     * utc时间转换为Date时间
     * @param utcTime
     * @return
     */
    public static Date convertToDate(String utcTime) {
        try {

            String timeZone;
            if (!utcTime.endsWith("Z")) {
                //末尾不包含Z,则不需要减去8小时
                utcTime += "Z";
                timeZone = "+0800";
            } else {
                timeZone = "+0000";
            }
            Date date = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")).parse(utcTime.replaceAll("Z$", timeZone));
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * Date时间转utc时间
     *
     * @param date
     * @return
     */
    public static String convertToUtcTime(Date date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * 年月日转utc时间
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String convertToUtcTime(int year, int month, int day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            return convertToUtcTime(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    /**
     * long时间转utc时间
     * @param time
     * @return
     */
    public static String convertToUtcTime(long time) {
        try {
            Date date = new Date(time);
            return convertToUtcTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * utc时间转为当日00:00:00的utc时间
     * @param utcTime
     * @return
     */
    public static String convertToMinUtcTime(String utcTime) {
        Date date = convertToDate(utcTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        Date zeroDate = calendar.getTime();
        return convertToUtcTime(zeroDate);
    }



    /**
     * utc时间转为当日00:00:00的utc时间
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String convertToMinUtcTime(int year, int month, int day) {
        String utcTime = convertToUtcTime(year, month, day);
        return convertToMinUtcTime(utcTime);
    }


    /**
     * utc时间转为当日23:59:59点的utc时间
     *
     * @param utcTime
     * @return
     */
    public static String convertToMaxUtcTime(String utcTime) {
        Date date = convertToDate(utcTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        Date zeroDate = calendar.getTime();
        return convertToUtcTime(zeroDate);
    }





    /**
     * utc时间转为当日23:59:59点的utc时间
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String convertToMaxUtcTime(int year, int month, int day) {
        String utcTime = convertToUtcTime(year, month, day);
        return convertToMaxUtcTime(utcTime);
    }


}
