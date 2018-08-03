package phoebe.eqx.smoi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {

    public static String getNow() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static long getSecondNow() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis() / 1000 + 2208988800L; //current time + sec from 1900-1970.
    }
}
