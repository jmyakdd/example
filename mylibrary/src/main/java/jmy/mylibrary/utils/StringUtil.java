package jmy.mylibrary.utils;

import java.text.DecimalFormat;

/**
 * Created by jmy on 2017/3/22.
 */

public class StringUtil {
    public static boolean isNull(String str) {
        if (str == null)
            return true;
        if (str.trim().equals(""))
            return true;
        return false;
    }

    public static boolean isPhone(String str) {
        if (isNull(str)) {
            return false;
        }
        if (str.length() != 11) {
            return false;
        }
        return true;
    }

    public static String formatDouble(double d){
        DecimalFormat df = new DecimalFormat("###0.00");
        return df.format(d);
    }

    public static String getMillion(double d){
        return formatDouble(d/10000);
    }
}
