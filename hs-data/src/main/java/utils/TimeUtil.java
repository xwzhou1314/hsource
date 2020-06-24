package utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
    public final static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyyMM");

    public final static SimpleDateFormat OSS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss_SSS");

    public final static SimpleDateFormat ES_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public final static SimpleDateFormat SS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static SimpleDateFormat CS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public final static SimpleDateFormat CL_FORMAT = new SimpleDateFormat("yy-MM-dd HH:mm");

    public final static SimpleDateFormat DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public final static SimpleDateFormat UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public final static SimpleDateFormat UTC_FORMAT_SUB = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public final static SimpleDateFormat US_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    public final static SimpleDateFormat FORMATNUM = new SimpleDateFormat("yyyyMMddHHmmss");

    public final static ZoneId zone = ZoneId.systemDefault();

    public static String formatNum(Date date) {
        return FORMATNUM.format(date);
    }

    public static String format(Date date) {
        return FORMAT.format(date);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String ossFormat(Date date) {
        return OSS_FORMAT.format(date);
    }

    public static String ssFormat(Date date) {
        return SS_FORMAT.format(date);
    }

    public static String utcFormat(Date date) {
        UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        return UTC_FORMAT.format(date);
    }
    public static String utcFormatSub(Date date) {
     //   UTC_FORMAT_SUB.setTimeZone(TimeZone.getTimeZone("UTC"));
        return UTC_FORMAT_SUB.format(date);
    }
    public static String ddFormat(Date date) {
        if (date == null) {
            return "";
        }
        return DD_FORMAT.format(date);
    }

    public static String csFormat(Date date) {
        if (date == null) {
            return "";
        }
        return CS_FORMAT.format(date);
    }

    public static String clFormatToStr(Date date) {
        if (date == null) {
            return "";
        }
        return CL_FORMAT.format(date);
    }

    public static Date clFormatToDate(String dateString) {
        Date date = null;
        try {
            return CL_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date dateToString(String dateString) {
        Date date = null;
        try {
            date = US_FORMAT.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间指定的格式
     *
     * @param format 格式字符串
     * @return
     */
    public static String getFormatString(String format) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(date);
        return str;
    }

    public static Date parse(String source) {
        try {
            return SS_FORMAT.parse(source);
        } catch (ParseException e) {
        }
        try {
            return DD_FORMAT.parse(source);
        } catch (ParseException e1) {
        }
        try {
            return UTC_FORMAT.parse(source);
        } catch (ParseException e) {
        }
        try {
            return CS_FORMAT.parse(source);
        } catch (ParseException e) {
        }

        return null;
    }

    /**
     * 获取时间戳
     *
     * @param date
     * @return
     */
    public static String querytimestamp(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        return date.getTime() + "";
    }

    public static LocalDate UDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }

    /**
     * 获得当天0点时间
     *
     * @return
     */
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
