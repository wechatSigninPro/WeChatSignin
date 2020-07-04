package com.nenu.edu.server.util;

/**
 * @Author: Liang Jiayue
 * @Description:字符串工具类
 */
public class StringUtil {
    /**
     * 替换括号内字符串
     * 如name=world，则Hello, {name}!替换成Hello, world!
     *
     * @param pattern
     * @param args
     * @return
     */
    public static String formatString(String pattern, String... args) {
        Object[] argObjects = args;
        String regex = "\\{\\w*\\}";
        String replacement = "%s";
        return String.format(pattern.replaceAll(regex, replacement), argObjects);
    }
}