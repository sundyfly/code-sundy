package com.sundy.common.util;

import java.io.*;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年03月30 11:27:06
 * 描述：异常工具类
 */
public class ExceptionUtils {
    /**
     * 获取异常信息的日志描述
     *
     * @param throwable
     * @return
     */
    public static String getException(Throwable throwable) {
        if (throwable == null) return "";

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ex) {
            sb.append(ex.toString());
        }
        return sb.toString();
    }
}
