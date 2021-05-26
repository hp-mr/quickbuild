package com.mr.quickbuild.utils;

import android.annotation.SuppressLint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class StringUtils {

    /**
     * 大陆手机号码十一位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isMobileNum(String mobiles) {
        String regExp = "13\\d{9}|14[579]\\d{8}|15[0123456789]\\d{8}|17[01235678]\\d{8" +
                "}|18\\d{9}";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 设置密码的长度
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdStrong(String pwd) {
        if (pwd == null || pwd.length() < 6) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatPwd(String str) {
        if (11 == str.length()) {
            return str.substring(0, 3) + "****" + str.substring(7);
        }
        return str;
    }

    public static String formatKileMiter(double miters) {
        if (miters > 1) {
            return miters + "km";
        } else {
            try {
                return Integer.valueOf(miters * 1000 + "") + "m";
            } catch (NumberFormatException e) {
                return "0.0m";
            }

        }
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }
    public static Date longToDate(long currentTime)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, "yyyy-MM-dd HH:mm:ss"); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, "yyyy-MM-dd HH:mm:ss"); // 把String类型转换为Date类型
        return date;
    }
    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime)
            throws ParseException {
        Date date = stringToDate(strTime, "yyyy-MM-dd HH:mm:ss"); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static Date stringToData(String strTime)
            throws ParseException {
        Date date = stringToDate(strTime, "yyyy-MM-dd HH:mm:ss"); // String类型转成date类型
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static SpannableStringBuilder changeTextForeColor(String colorText, String wholeText, int color) {
        if (!TextUtils.isEmpty(colorText) && !TextUtils.isEmpty(wholeText)) {
            int start = wholeText.indexOf(colorText);
            int end = start + colorText.length();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(wholeText);
            CharacterStyle characterStyle = new ForegroundColorSpan(MainUtils.getContext().getResources().getColor(color));
            spannableStringBuilder.setSpan(characterStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableStringBuilder;
        }
        return null;
    }

    public static SpannableStringBuilder changeTextForeColor(List<String> list, String wholeText, int color) {
        if (list.size() > 0) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(wholeText);
            for (String str : list) {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(wholeText)) {
                    int start = wholeText.indexOf(str);
                    int end = start + str.length();
                    CharacterStyle characterStyle = new ForegroundColorSpan(MainUtils.getContext().getResources().getColor(color));
                    spannableStringBuilder.setSpan(characterStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            return spannableStringBuilder;
        }
        return null;
    }

    public static List<String> splitString(String str, String split_str) {
        List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return list;
        }
        Collections.addAll(list, str.split(split_str));
        return list;
    }

    public static List<String> splitString(String str) {
        List<String> list = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return list;
        }
        Collections.addAll(list, str.split(","));
        return list;
    }

}
