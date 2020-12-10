package utils;

import java.util.Calendar;

public class Compare {
    public static int differenceBetweenDate(Calendar cLast, Calendar cStart){
        return Math.round((float) (cLast.getTimeInMillis() - cStart.getTimeInMillis()) / (24 * 60 * 60 * 1000));
    }
}
