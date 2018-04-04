package com.sundy.common.util;

import java.io.*;

/**
 * Created by sundy on 2018/3/30.
 */
public class ExceptionUtils {
    /**
     * 获取异常信息的日志描述
     * @param throwable
     * @return
     */
    public static String getException(Throwable throwable){
        if(throwable==null) return "";

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        pw.flush();
        LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        StringBuffer sb = new StringBuffer();
        try {
            String line = null;
            while ((line=reader.readLine()) != null) {
                sb.append(line+"\n");
            }
        } catch (IOException ex) {
            sb.append(ex.toString());
        }
        return sb.toString();
    }
}
