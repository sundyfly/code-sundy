package com.sundy.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年06月12日 14:01:32
 * 描述：
 */
public class IpUtils {

    public static String getIp(HttpServletRequest request) {
        try {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip != null) {
                if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                    int index = ip.indexOf(",");
                    if (index != -1) {
                        return ip.substring(0, index);
                    } else {
                        return ip;
                    }
                }
            }
            ip = request.getHeader("X-Real-IP");
            if (ip != null) {
                if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            ip = request.getHeader("Proxy-Client-IP");
            if (ip != null) {
                if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            ip = request.getHeader("WL-Proxy-Client-IP");
            if (ip != null) {
                if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }
            ip = request.getRemoteAddr();
            return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取mac地址
     * @param ip
     * @return
     */
    public static String getMac(String ip) {
        String cmd = "arp -a " + ip;
        String reg = "[a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9]"
                + "[:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9]";
        Runtime runtime = Runtime.getRuntime();
        InputStreamReader ins = null;
        try {
            ins = new InputStreamReader(runtime.exec(cmd).getInputStream());
            BufferedReader br = new BufferedReader(ins);
            String line = null;
            StringBuilder b = new StringBuilder();
            while ((line = br.readLine()) != null) {
                b.append(line).append("\n");
            }
            // 现在创建 matcher 对象
            Matcher m = Pattern.compile(reg).matcher(b.toString());
            if (m.find()) {
                return m.group(0).replace("-", ":");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取mac地址
     * @param ip
     * @return
     */
    public static String getMacs(String ip) {
        String cmd = "arp -a " + ip;
        String reg = "[a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9]"
                + "[:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9][:|-][a-zA-Z0-9][a-zA-Z0-9]";
        Runtime runtime = Runtime.getRuntime();
        InputStreamReader ins = null;
        try {
            ins = new InputStreamReader(runtime.exec(cmd).getInputStream());
            BufferedReader br = new BufferedReader(ins);
            String line = null;
            StringBuffer b = new StringBuffer();
            while ((line = br.readLine()) != null) {
                b.append(line).append("\n");
            }
            // 现在创建 matcher 对象
            Matcher m = Pattern.compile(reg).matcher(b.toString());
            if (m.find()) {
                return m.group(0).replace(":", "-");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取本机mac
     */
    public static String getLocalMac(InetAddress ia) throws SocketException {
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        System.out.println("mac数组长度："+mac.length);
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            System.out.println("每8位:"+str);
            if(str.length()==1) {
                sb.append("0").append(str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
}
