package com.vision.imagine.utils;

import com.github.thunder413.datetimeutils.DateTimeUnits;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.vision.imagine.pojos.Age;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static String calculateAge2(String dateTime) {

        try {
            //"2020-09-20 09:04:52"
            Date createdTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTime);

            Date currentTime = new Date();

//          int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.MILLISECONDS);
//          int diff = DateTimeUtils.getDateDiff(date,date2, DateTimeUnits.SECONDS);
            int diffInMinutes = DateTimeUtils.getDateDiff(currentTime, createdTime, DateTimeUnits.MINUTES);
            int diffInHours = DateTimeUtils.getDateDiff(currentTime, createdTime, DateTimeUnits.HOURS);
            int diffInDays = DateTimeUtils.getDateDiff(currentTime, createdTime, DateTimeUnits.DAYS);

            Age age = new Age(diffInMinutes, diffInHours, diffInDays, diffInDays / 30, diffInDays / 365);

            String ago = "";
            if (age.getYears() != 0) {
                ago = age.getYears() + " years ago";
            } else if (age.getMonths() != 0) {
                ago = age.getMonths() + " months ago";
            } else if (age.getDays() != 0) {
                ago = age.getDays() + " days ago";
            } else if (age.getHour() != 0) {
                ago = age.getHour() + " hours ago";
            } else if (age.getMins() != 0) {
                ago = age.getMins() + " mins ago";
            }
            return ago;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Age calculateAge(Date birthDate) {
        int years = 0;
        int months = 0;
        int days = 0;

        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        //Get difference between months
        months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }

        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else {
            days = 0;
            if (months == 12) {
                years++;
                months = 0;
            }
        }

        Date currentDate = new Date();
        long diff = currentDate.getTime() - birthDate.getTime();

//        long diffSeconds = diff / 1000 % 60;
        int diffMinutes = (int) (diff / (60 * 1000) % 60);
        int diffHours = (int) (diff / (60 * 60 * 1000) % 24);
//        long diffDays = diff / (24 * 60 * 60 * 1000);

//            public Age(int mins, int hour, int days, int months, int years) {

        //Create new Age object
        return new Age(diffMinutes, diffHours, days, months, years);
//        return new Age(days, months, years);
    }


}
