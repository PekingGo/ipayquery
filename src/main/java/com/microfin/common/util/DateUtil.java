package com.microfin.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 *
 * 取得标准格式日期和时间
 *
 * @author lipeng public 函数列表 static String getAddDay(int ,int) static String
 *         today() static String time()
 * */

public class DateUtil {

    /** 缺省日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /** 缺省日期格式2 */
    public static final String DEFAULT_DATE_FORMAT_T = "yyyy/MM/dd";

    /** 缺省日期格式2 */
    public static final String DEFAULT_DATE_FORMAT_U = "yyyyMMddHHmmssSSS";

    /** 缺省时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /** 缺省月格式 */
    public static final String DEFAULT_MONTH = "MONTH";

    /** 缺省年格式 */
    public static final String DEFAULT_YEAR = "YEAR";

    /** 缺省日格式 */
    public static final String DEFAULT_DATE = "DAY";

    /** 缺省小时格式 */
    public static final String DEFAULT_HOUR = "HOUR";

    /** 缺省分钟格式 */
    public static final String DEFAULT_MINUTE = "MINUTE";

    /** 缺省秒格式 */
    public static final String DEFAULT_SECOND = "SECOND";

    /** 缺省长日期格式 */
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH-mm";

    /** 缺省长日期格式,精确到秒 */
    public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

    /** 缺省长日期格式2,精确到秒 */
    public static final String DEFAULT_DATETIME_FORMAT_SEC_T = "yyyy/MM/dd HH:mm:ss";

    /** 缺省长日期格式2 */
    public static final String DEFAULT_DATETIME_FORMAT_T = "yyyy/MM/dd HH:mm";

    /** 星期数组 */
    public static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

    /** 年 */
    public final static int YEAR = 1;

    /** 月 */
    public final static int MONTH = 2;

    /** 日 */
    public final static int DAY = 3;

    /** 时 */
    public final static int HOUR = 4;

    /** 分 */
    public final static int MINUTES = 5;

    /** 秒 */
    public final static int SECOND = 6;

    /**
     * 取当前日期的字符串表示
     *
     * @return 当前日期的字符串 ,如2010-05-28
     **/
    public static String today() {
        return today(DEFAULT_DATE_FORMAT);
    }

    /**
     * 根据输入的格式得到当前日期的字符串
     *
     * @param strFormat
     *            日期格式
     * @return
     */
    public static String today(String strFormat) {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(strFormat)) {
            val = toString(new Date(), strFormat);
        }
        return val;
    }

    /**
     * 取当前时间的字符串表示,
     *
     * @return 当前时间,如:21:10:12
     **/
    public static String currentTime() {
        return currentTime(DEFAULT_TIME_FORMAT);
    }

    /**
     * 根据输入的格式获取时间的字符串表示
     *
     * @param 输出格式
     *            ,如'hh:mm:ss'
     * @return 当前时间,如:21:10:12
     **/

    public static String currentTime(String strFormat) {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(strFormat)) {
            val = toString(new Date(), strFormat);
        }
        return val;
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期 <br>
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DATE", -5).
     *
     * @param field
     *            ,段,如"year","month","date",对大小写不敏感
     * @param amount
     *            ,增加的数量(减少用负数表示),如5,-1
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws ParseException
     **/

    public static String getAddDay(String field, int amount) throws ParseException {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(field)) {
            val = getAddDay(field, amount, null);
        }
        return val;
    }

    /**
     * 取得相对于当前时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * 欲取得当前日期5天前的日期,可做如下调用:<br>
     * getAddDay("DATE", -5,'yyyy-mm-dd hh:mm').
     *
     * @param field
     *            ,段,如"year","month","date",对大小写不敏感
     * @param amount
     *            ,增加的数量(减少用负数表示),如5,-1
     * @param strFormat
     *            ,输出格式,如"yyyy-mm-dd","yyyy-mm-dd hh:mm"
     * @return 格式化后的字符串 如"2010-05-28"
     * @throws ParseException
     **/
    public static String getAddDay(String field, int amount, String strFormat) throws ParseException {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(field) && StringUtil.notEmpty(strFormat)) {
            val = getAddDay(null, field, amount, strFormat);
        }
        return val;
    }

    /**
     * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
     *
     * @param date
     *            String 要改变的日期
     * @param field
     *            int 日期改变的字段，YEAR,MONTH,DAY
     * @param amount
     *            int 改变量
     * @param strFormat
     *            日期返回格式
     * @return
     * @throws ParseException
     */
    public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
        if (StringUtil.isEmpty(strFormat)) {
            strFormat = DEFAULT_DATETIME_FORMAT_SEC;
        }
        Calendar rightNow = Calendar.getInstance();
        if (StringUtil.notEmpty(date)) {
            rightNow.setTime(parseDate(date, strFormat));
        }
        if (StringUtil.isEmpty(field)) {
            return toString(rightNow.getTime(), strFormat);
        }
        rightNow.add(getInterval(field), amount);
        return toString(rightNow.getTime(), strFormat);
    }

    /**
     * 获取时间间隔类型
     *
     * @param field
     *            时间间隔类型
     * @return 日历的时间间隔
     */
    protected static int getInterval(String field) {
        String tmpField = field.toUpperCase();
        if (DEFAULT_YEAR.equals(tmpField)) {
            return Calendar.YEAR;
        } else if (DEFAULT_MONTH.equals(tmpField)) {
            return Calendar.MONTH;
        } else if (DEFAULT_DATE.equals(tmpField)) {
            return Calendar.DATE;
        } else if (DEFAULT_HOUR.equals(tmpField)) {
            return Calendar.HOUR;
        } else if (DEFAULT_MINUTE.equals(tmpField)) {
            return Calendar.MINUTE;
        } else {
            return Calendar.SECOND;
        }
    }

    /**
     * 获取格式化对象
     *
     * @param strFormat
     *            格式化的格式 如"yyyy-MM-dd"
     * @return 格式化对象
     */
    public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
        if (StringUtil.notEmpty(strFormat)) {
            return new SimpleDateFormat(strFormat);
        } else {
            return new SimpleDateFormat();
        }
    }

    /**
     * 得到当前日期的星期数
     *
     * @return 当前日期的星期的字符串
     * @throws ParseException
     */
    public static String getWeekOfMonth() throws ParseException {
        return getWeekOfMonth(null, null);
    }

    /**
     * 根据日期的到给定日期的在当月中的星期数
     *
     * @param date
     *            给定日期
     * @return
     * @throws ParseException
     */
    public static String getWeekOfMonth(String date, String fromat) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        if (StringUtil.notEmpty(date) && StringUtil.notEmpty(fromat)) {
            rightNow.setTime(parseDate(date, fromat));
        }
        return WEEKS[rightNow.get(Calendar.WEEK_OF_MONTH)];
    }

    /**
     * 将java.util.date型按照指定格式转为字符串
     *
     * @param date
     *            源对象
     * @param format
     *            想得到的格式字符串
     * @return 如：2010-05-28
     */
    public static String toString(Date date, String format) {
        String val = StringUtil.STR_EMPTY;
        if (date != null && StringUtil.notEmpty(format)) {
            val = getSimpleDateFormat(format).format(date);
        }
        return val;
    }

    /**
     * 将java.util.date型按照缺省格式转为字符串
     *
     * @param date
     *            源对象
     * @return 如：2010-05-28
     */
    public static String toString(Date date) {
        String val = StringUtil.STR_EMPTY;
        if (date != null) {
            val = toString(date, DEFAULT_DATE_FORMAT);
        }
        return val;
    }

    /**
     * 强制类型转换 从串到日期
     *
     * @param sDate
     *            源字符串，采用yyyy-MM-dd格式
     * @param sFormat
     *            ps
     * @return 得到的日期对象
     * @throws ParseException
     */
    public static Date parseDate(String strDate, String format) throws ParseException {
        Date val = null;
        if (StringUtil.notEmpty(strDate) && StringUtil.notEmpty(format)) {
            val = getSimpleDateFormat(format).parse(strDate);
        }
        return val;
    }

    /***
     * 根据传入的毫秒数和格式，对日期进行格式化输出
     *
     * @version 2011-7-12
     * @param object
     * @param format
     * @return
     */
    public static String millisecondFormat(Long millisecond, String format) {
        if (millisecond == null || millisecond <= 0) {
            throw new IllegalArgumentException(String.format("传入的时间毫秒数[%s]不合法", StringUtil.STR_EMPTY + millisecond));
        }
        if (StringUtil.isEmpty(format)) {
            format = DEFAULT_DATE_FORMAT;
        }
        return toString(new Date(millisecond), format);
    }

    /**
     * 强制类型转换 从串到时间戳
     *
     * @param sDate
     *            源串
     * @param sFormat
     *            遵循格式
     * @return 取得的时间戳对象
     * @throws ParseException
     */
    public static Timestamp parseTimestamp(String strDate, String format) throws ParseException {
        Timestamp val = null;
        if (StringUtil.notEmpty(strDate) && StringUtil.notEmpty(format)) {
            Date utildate = getSimpleDateFormat(format).parse(strDate);
            val = new Timestamp(utildate.getTime());
        }
        return val;
    }

    /**
     * getCurDate 取当前日期
     *
     * @return java.util.Date型日期
     **/
    public static Date getCurDate() {
        return (new Date());
    }

    /**
     * getCurTimestamp 取当前时间戳
     *
     * @return java.sql.Timestamp
     **/
    public static Timestamp getCurTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * getCurTime 取当前时间的毫秒数
     *
     * @return long型时间的毫秒数
     **/
    public static long getCurTime() {
        return (new Date().getTime());
    }

    /**
     * getCurTimestamp 取遵循格式的当前时间
     *
     * @param sFormat
     *            遵循格式
     * @return java.sql.Timestamp
     **/
    public static Date getCurDate(String format) throws Exception {
        Date val = null;
        if (StringUtil.notEmpty(format)) {
            val = getSimpleDateFormat(format).parse(toString(new Date(), format));
        }
        return val;
    }

    /**
     * Timestamp按照指定格式转为字符串
     *
     * @param timestamp
     *            源对象
     * @param format
     *            ps（如yyyy.mm.dd）
     * @return 如：2010-05-28 或2010-05-281 13:21
     */
    public static String toString(Timestamp timestamp, String format) {
        String val = StringUtil.STR_EMPTY;
        if (timestamp != null && StringUtil.notEmpty(format)) {
            val = toString(new Date(timestamp.getTime()), format);
        }
        return val;
    }

    /**
     * Timestamp按照缺省格式转为字符串
     *
     * @param ts
     *            源对象
     * @return 如：2010-05-28
     */
    public static String toString(Timestamp ts) {
        String val = StringUtil.STR_EMPTY;
        if (ts != null) {
            val = toString(ts, DEFAULT_DATE_FORMAT);
        }
        return val;
    }

    /**
     * Timestamp按照缺省格式转为字符串，可指定是否使用长格式
     *
     * @param timestamp
     *            欲转化之变量Timestamp
     * @param fullFormat
     *            是否使用长格式
     * @return 如：2010-05-28 或2010-05-28 21:21
     */
    public static String toString(Timestamp timestamp, boolean fullFormat) {
        String val = StringUtil.STR_EMPTY;
        if (timestamp != null) {
            if (fullFormat) {
                val = toString(timestamp, DEFAULT_DATETIME_FORMAT_SEC);
            } else {
                val = toString(timestamp, DEFAULT_DATE_FORMAT);
            }
        }
        return val;
    }

    /**
     * 将sqldate型按照指定格式转为字符串
     *
     * @param sqldate
     *            源对象
     * @param sFormat
     *            ps
     * @return 如：2010-05-28 或2010-05-28 00:00
     */
    public static String toString(java.sql.Date sqldate, String sFormat) {
        String val = StringUtil.STR_EMPTY;
        if (sqldate != null && StringUtil.notEmpty(sFormat)) {
            val = toString(new Date(sqldate.getTime()), sFormat);
        }
        return val;
    }

    /**
     * 将sqldate型按照缺省格式转为字符串
     *
     * @param sqldate
     *            源对象
     * @return 如：2010-05-28
     */
    public static String toString(java.sql.Date sqldate) {
        String val = StringUtil.STR_EMPTY;
        if (sqldate != null) {
            val = toString(sqldate, DEFAULT_DATE_FORMAT);
        }
        return val;
    }

    /**
     * 计算日期时间之间的差值， date1得时间必须大于date2的时间
     *
     * @version 2011-7-12
     * @param date1
     * @param date2
     * @return {@link java.util.Map} Map的键分别为, day(天),
     *         hour(小时),minute(分钟)和second(秒)。
     */
    public static Map<String, Long> timeDifference(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException("date1 and date2 can't null");
        }
        long mim1 = date1.getTime();
        long mim2 = date2.getTime();
        if (mim1 < mim2) {
            throw new IllegalArgumentException(String.format("date1[%s] not be less than date2[%s].", mim1 + StringUtil.STR_EMPTY, mim2
                    + StringUtil.STR_EMPTY));
        }
        long m = (mim1 - mim2 + 1) / 1000l;
        long mday = 24 * 3600;
        final Map<String, Long> map = new HashMap<String, Long>();
        map.put("day", m / mday);
        m = m % mday;
        map.put("hour", (m) / 3600);
        map.put("minute", (m % 3600) / 60);
        map.put("second", (m % 3600 % 60));
        return map;
    }

    /**
     * date1 <= date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Map<String, Integer> compareTo(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            return null;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(date1);
        c2.setTime(date2);

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("year", c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        map.put("month", c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR));
        map.put("day", c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH));
        map.put("hour", c2.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY));
        map.put("minute", c2.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE));
        map.put("second", c2.get(Calendar.SECOND) - c1.get(Calendar.SECOND));
        return map;
    }

    /**
     * util.date 转换为 sql.date
     *
     * @param date
     * @return
     */
    public static java.sql.Date toSqlDate(Date date) {
        java.sql.Date sqlDate = null;
        if (date != null) {
            sqlDate = new java.sql.Date(date.getTime());
        }
        return sqlDate;
    }

    /**
     * sql.date 转换为 util.date
     *
     * @param date
     * @return
     */
    public static Date toUtilDate(java.sql.Date date) {
        java.util.Date utilDate = null;
        if (date != null) {
            utilDate = new java.util.Date(date.getTime());
        }
        return utilDate;
    }

    /**
     * 1. 设置时间格式 2. 传入字符串类型的日期，返回Date类型的日期（时间格式转换） 3.
     * 传入Date类型的日期，返回字符串类型的日期（时间格式转换） 4. 当前日期下的偏移日期（年、月、日、小时、分钟）（可以跨年度改变）
     *
     * @param args
     */

    /**
     * Date转换成String类型
     */
    // 按date的格式
    public static String dateToString(Date date) {
        String val = StringUtil.STR_EMPTY;
        if (date != null) {
            // DateFormat df = DateFormat.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC);
            val = sdf.format(date);
        }
        return val;
    }

    // 按指定格式
    public static String dateToString(Date date, String pattern) {
        if (date != null && StringUtil.notEmpty(pattern)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } else {
            return StringUtil.STR_EMPTY;
        }
    }

    // 按指定格式取当前时间
    public static String dateToString(String pattern) {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(pattern)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Calendar ca = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
            val = sdf.format(ca.getTime());
        }
        return val;
    }

    /**
     * String转换成Date类型
     */
    // 返回当前时间
    public static Date stringToDate() {

        Calendar ca = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        return ca.getTime();
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date) throws ParseException {
        if (StringUtil.notEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT_SEC);
            return sdf.parse(date);
        } else {
            return null;
        }
    }

    // 将指定格式的字符串日期转换成日期类型对象
    public static Date stringToDate(String dateStrs, String datePattern) throws ParseException {
        Date val = null;
        if (StringUtil.notEmpty(dateStrs) && StringUtil.notEmpty(datePattern)) {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            val = sdf.parse(dateStrs);
        }
        return val;
    }

    /**
     * 日期的偏移计算问题(时间累加)
     */
    public static Date dateMoving(Date date, int move, int type) {

        Calendar ca = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
        ca.setTime(date);

        if (type == YEAR) {

            int year = ca.get(Calendar.YEAR);
            year = year + move;
            ca.set(Calendar.YEAR, year);

        } else if (type == MONTH) {

            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH);
            month = month + move;

            if (month > 0) {
                int year1 = month / 12;
                year = year + year1;
                month = month % 12;
            } else {
                int year1 = month / 12 - 1;
                year = year + year1;
                month = 12 + month % 12;
            }

            ca.set(Calendar.YEAR, year);
            ca.set(Calendar.MONTH, month);

        } else if (type == DAY) {

            long current = ca.getTimeInMillis();
            long movelong = move;
            movelong = 1000 * 60 * 60 * 24 * movelong;
            current = current + movelong;

            ca.setTimeInMillis(current);

        } else if (type == HOUR) {

            long current = ca.getTimeInMillis();
            long movelong = move;
            movelong = 1000 * 60 * 60 * movelong;
            current = current + movelong;

            ca.setTimeInMillis(current);

        } else if (type == MINUTES) {

            long current = ca.getTimeInMillis();
            long movelong = move;
            movelong = 1000 * 60 * movelong;
            current = current + movelong;

            ca.setTimeInMillis(current);

        } else if (type == SECOND) {

            long current = ca.getTimeInMillis();
            long movelong = move;
            movelong = 1000 * movelong;
            current = current + movelong;

            ca.setTimeInMillis(current);

        }

        return ca.getTime();
    }

    /**
     * 日期转换：java.sql.Date --> java.util.Date
     */
    public static Date transToUtilDate(java.sql.Date date) {
        Date val = null;
        if (date != null) {
            long times = date.getTime();
            Calendar ca = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINA);
            ca.setTimeInMillis(times);
            val = ca.getTime();
        }
        return val;
    }

    /**
     * 日期转换：java.util.Date --> java.sql.Date
     */
    public static java.sql.Date transToSqlDate(Date date) {
        java.sql.Date val = null;
        if (date != null) {
            long time = date.getTime();
            val = new java.sql.Date(time);
        }
        return val;
    }

    public static Set<String> dayBetween(Date date1, Date date2) {

        Set<String> set = new HashSet<String>();
        double between = (date2.getTime() - date1.getTime()) / 1000; // 除以1000是为了转换成秒
        double day = between / (24 * 3600);
        for (int i = 1; i <= day; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(date1);
            cd.add(Calendar.DATE, i); // 增加一天
            set.add(dateToString(cd.getTime(), "yyyyMMdd"));
        }

        return set;
    }

    /**
     * 把原来格式的时间字符串转换成指定格式的字符串
     */
    public static String toSqlString(String initStr, String initPattern, String transPattern) {
        String val = StringUtil.STR_EMPTY;
        if (StringUtil.notEmpty(initStr)) {
            try {
                // 转换为指定格式
                val = toString(parseDate(initStr, initPattern), transPattern);
            } catch (ParseException e) {
                // 如果原字符串不是合法的时间格式，返回空字符
                val = StringUtil.STR_EMPTY;
            }
        }
        // 返回转换结果
        return val;
    }

    /**
     * 判断一个字符串是否是合法日期
     *
     * @param date
     *            日期字符串
     * @param formate
     *            日期格式 如：yyyyMMdd
     * @return
     */
    public static boolean isDate(String date, String formate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
            dateFormat.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
