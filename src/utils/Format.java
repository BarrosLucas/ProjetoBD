package utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Format {
    public static String dateToString(Date date) {
        SimpleDateFormat fr = new SimpleDateFormat("dd/MM/yyyy");
        return fr.format(date);
    }

    public static Time stringToHour(String hour) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = simpleDateFormat.parse(hour);
        Time time = new Time(date.getTime());

        return time;
    }

    public static Calendar stringToDate(String date) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = simpleDateFormat.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return  calendar;
    }

    public static java.sql.Date dateUtilToDateSQL(Date dataUtil)
            throws ParseException {
        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

        return dataSql;
    }
}
