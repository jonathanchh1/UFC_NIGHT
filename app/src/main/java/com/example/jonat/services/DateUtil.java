package com.example.jonat.services;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jonat on 11/13/2017.
 */

public class DateUtil {

    public static String MakePrettyDate(String getCreatedTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ", Locale.US);
        ParsePosition pos = new ParsePosition(0);
        long then = formatter.parse(getCreatedTime, pos).getTime();
        long now = new Date().getTime();

        long seconds = (now - then) / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        String friendly = null;
        long num = 0;
        if (days > 0) {
            num = days;
            friendly = days + " day";
        } else if (hours > 0) {
            num = hours;
            friendly = hours + " hour";
        } else if (minutes > 0) {
            num = minutes;
            friendly = minutes + " minute";
        } else {
            num = seconds;
            friendly = seconds + " second";
        }
        if (num > 1) {
            friendly += "s";
        }
        String postTimeStamp = friendly + " ago";
        return postTimeStamp;
    }

}
