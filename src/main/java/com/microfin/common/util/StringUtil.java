package com.microfin.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;
import java.util.UUID;

import org.codehaus.plexus.util.StringUtils;

/**
 * 字符串工具类
 * <p>
 * Title:StringUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Pansoft
 * </p>
 *
 * @author lipeng
 * @version 1.0
 */
public class StringUtil extends StringUtils {

    /** 空字符串 */
    public static final String STR_EMPTY = "";

    /** 短横线标志 */
    public static final String STR_LINE = "-";

    /** null字符串 */
    public static final String STR_NULL = "null";

    /** 编码格式(UTF-8) */
    public static final String CODING_FORMAT_UTF8 = "UTF-8";

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空则返回true，不否则返回false
     */
    public static boolean isEmpty(String s) {
        s = nvlTrim(s);
        return STR_EMPTY.equals(s) || STR_NULL.equals(s);
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     *
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        s = nvlTrim(s);
        return !STR_EMPTY.equals(s) && !STR_NULL.equals(s);
    }

    /**
     * 字符串转换为字符串数组
     *
     * @param str
     *            字符串
     * @param splitRegex
     *            分隔符
     * @return
     */
    public static String[] str2StrArray(String str, String splitRegex) {
        if (isEmpty(str)) {
            return null;
        }
        return str.split(splitRegex);
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     *
     * @param str
     *            字符串
     * @return
     */
    public static String[] str2StrArray(String str) {
        return str2StrArray(str, ",\\s*");
    }

    /**
     * null转为指定的默认字符串
     *
     * @param str
     * @param defStr
     * @return
     */
    public static String nvl(String str, String defStr) {
        if (str == null) {
            str = defStr;
        }
        return str;
    }

    /**
     * null转为空串
     *
     * @param str
     * @return
     */
    public static String nvl(String str) {
        return nvl(str, STR_EMPTY);
    }

    /**
     * null转为空串,非null进行trim处理
     *
     * @param str
     * @return
     */
    public static String nvlTrim(String str) {
        str = nvl(str);
        return str.trim();
    }

    /**
     * 空对象转换处理
     *
     * @param obj
     * @return String
     */
    public static String nullToEmpty(Object obj) {
        // 返回值
        String rtnValue = STR_EMPTY;
        // 非null对象转换为字符串
        if (obj != null) {
            rtnValue = obj.toString();
        }
        // 转换结果返回
        return rtnValue;
    }

    /**
     * 空对象转换处理
     *
     * @param obj
     * @return int
     */
    public static int nullToZero(Object obj) {
        // 返回值
        int rtnValue = 0;
        // 非null对象转换
        if (obj != null) {
            rtnValue = Integer.parseInt(obj.toString().trim());
        }
        // 转换结果返回
        return rtnValue;
    }

    /**
     * 空对象转换处理
     *
     * @param String
     * @return int
     */
    public static int emptyToZero(String str) {
        // 返回值
        int rtnValue = 0;
        // 非null对象转换
        if (!isEmpty(str)) {
            rtnValue = Integer.parseInt(nvlTrim(str));
        }
        // 转换结果返回
        return rtnValue;
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param String
     * @return String
     */
    public static String getRandomString(int length) {
        // 返回字符串
        String randomStr = STR_EMPTY;
        // 指定长度小于1的情况
        if (length <= 0) {
            // 返回空字符串
            return randomStr;
        }
        // 指定允许的字符
        char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f',
                'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm' };
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            // 生成自定长度的随机字符串
            stringBuffer.append(randomChar[Math.abs(random.nextInt()) % randomChar.length]);
        }
        randomStr = stringBuffer.toString();
        return randomStr;
    }

    /**
     * 判断字符串是否全为数字
     *
     * @param String
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        int begin = 0;
        boolean once = true;
        if (str == null || str.trim().equals("")) {
            return false;
        }
        str = str.trim();
        if (str.startsWith("+") || str.startsWith("-")) {
            if (str.length() == 1) {
                // "+" "-"
                return false;
            }
            begin = 1;
        }
        for (int i = begin; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if (str.charAt(i) == '.' && once) {
                    // '.' can only once
                    once = false;
                } else {
                    return false;
                }
            }
        }
        if (str.length() == (begin + 1) && !once) {
            // "." "+." "-."
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为整数
     *
     * @param String
     * @return boolean
     */
    public static boolean isInteger(String str) {
        int begin = 0;
        if (str == null || str.trim().equals("")) {
            return false;
        }
        str = str.trim();
        if (str.startsWith("+") || str.startsWith("-")) {
            if (str.length() == 1) {
                // "+" "-"
                return false;
            }
            begin = 1;
        }
        for (int i = begin; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否全为数字(异常判断)
     *
     * @param String
     * @return boolean
     */
    public static boolean isNumericEx(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * 判断字符串是否为整数(异常判断)
     *
     * @param String
     * @return boolean
     */
    public static boolean isIntegerEx(String str) {
        str = str.trim();
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            if (str.startsWith("+")) {
                return isIntegerEx(str.substring(1));
            }
            return false;
        }
    }

    /**
     * 取得转码之前的字符串
     *
     * @param String
     * @return String
     * @throws UnsupportedEncodingException
     */
    public static String getDecodeStr(String str) throws UnsupportedEncodingException {
        String val = STR_EMPTY;
        if (str != null) {
            val = URLDecoder.decode(str, CODING_FORMAT_UTF8);
        }
        return val;
    }

    /**
     * 取得32位随机字符串
     */
    public static String getUuid() {
        return nullToEmpty(UUID.randomUUID()).replaceAll(STR_LINE, STR_EMPTY);
    }

    /**
     * 计算文件大小
     */
    public static String convertFileSize(String sizeStr) {
        if (notEmpty(sizeStr) && isNumericEx(sizeStr)) {
            long size = Long.parseLong(sizeStr);
            long kb = 1024;
            long mb = kb * 1024;
            long gb = mb * 1024;

            if (size >= gb) {
                return String.format("%.1f GB", (float) size / gb);
            } else if (size >= mb) {
                float f = (float) size / mb;
                return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
            } else if (size >= kb) {
                float f = (float) size / kb;
                return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
            } else {
                return String.format("%d B", size);
            }
        } else {
            return STR_EMPTY;
        }
    }

    public static final String[] zeros = new String[] { "0000", "000", "00", "0" };

    public static String fillZero(int i) {
        String si = String.valueOf(i);
        return zeros[si.length()] + si;
    }
}
