package com.bmfm.mdg.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jikun.zhu
 * @date 2020/7/1 4:11 下午
 */
public class DateUtil {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String format(Date date) {
        return format.format(date);
    }

}
