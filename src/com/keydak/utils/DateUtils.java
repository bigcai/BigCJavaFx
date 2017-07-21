package com.keydak.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * User: caisz
 * Date: 2017/5/5
 * Time: 15:24
 * Description:
 */
public class DateUtils {

    public static Date toDate(LocalDate localDate ) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        java.util.Date date = Date.from(instant);
        return  date;
    }

    public static LocalDate toLocalDate( Date date ) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return  localDate;
    }
}
