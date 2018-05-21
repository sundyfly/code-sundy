package com.sundy.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Properties;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 15:47:33
 * 描述：获取LInux的一些数据参数
 */
public class LinuxUtils {
    /**
     * 获取CPU的数量
     * @return
     */
    public static int getCPUNumber(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取CPU使用率
     * @return 如： 32.54%
     */
    public static String getCPUUsage() throws IOException {
        float cpuUsage = 0;
        Process pro1,pro2;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/stat";
            //第一次采集CPU时间
            pro1 = r.exec(command);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String line = null;
            long idleCpuTime1 = 0, totalCpuTime1 = 0;   //分别为系统启动后空闲的CPU时间和总的CPU时间
            Read read = new Read(in1, idleCpuTime1, totalCpuTime1).invoke();
            idleCpuTime1 = read.getIdleCpuTime1();
            totalCpuTime1 = read.getTotalCpuTime1();
            in1.close();
            pro1.destroy();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //第二次采集CPU时间
            pro2 = r.exec(command);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            long idleCpuTime2 = 0, totalCpuTime2 = 0;   //分别为系统启动后空闲的CPU时间和总的CPU时间
            Read read2 = new Read(in1, idleCpuTime1, totalCpuTime1).invoke();
            idleCpuTime2 = read2.getIdleCpuTime1();
            totalCpuTime2 = read2.getTotalCpuTime1();

            if(idleCpuTime1 != 0 && totalCpuTime1 !=0 && idleCpuTime2 != 0 && totalCpuTime2 !=0){
                cpuUsage = 1 - (float)(idleCpuTime2 - idleCpuTime1)/(float)(totalCpuTime2 - totalCpuTime1);
            }
            in2.close();
            pro2.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DecimalFormat("##0.00").format(cpuUsage*100)+"%";
    }

    /**
     * 获取可用的内存
     * @return 如： 42.54%
     * @throws IOException
     */
    public static String availableMemory() throws IOException{
        Runtime runtime = Runtime.getRuntime();
        String command = " free";
        Process process = runtime.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        long tatal = 0;
        long available = 0;
        long free = 0;
        String str = null;
        while((line=br.readLine())!=null){
            if(line.startsWith("Mem:")){
                String[] split = line.split("\\s+");
                tatal = Long.valueOf(split[1]);
                available = Long.valueOf(split[3])+Long.valueOf(split[5])+Long.valueOf(split[6]);
            }
        }
        if(tatal!=0){
            str = new DecimalFormat("##0.00").format((free+available)/(tatal*1.0f)*100);
        }
        br.close();
        process.destroy();
        return str==null?"0.00%":str+"%";
    }

    /**
     * 已经用的内存
     * @return 如： 42.54%
     * @throws IOException
     */
    public static String usedMemory() throws IOException{
        Runtime runtime = Runtime.getRuntime();
        String command = " free";
        Process process = runtime.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        long tatal = 0;
        long used = 0;
        String str = null;
        while((line=br.readLine())!=null){
            if(line.startsWith("Mem:")){
                String[] split = line.split("\\s+");
                tatal = Long.valueOf(split[1]);
                used = Long.valueOf(split[2])-Long.valueOf(split[5])-Long.valueOf(split[6]);
            }
        }
        if(tatal!=0){
            str = new DecimalFormat("##0.00").format(used/(tatal*1.0f)*100);
        }
        br.close();
        process.destroy();
        return str==null?"0.00%":str+"%";
    }

    /**
     * 磁盘使用率
     * @return
     * @throws Exception
     */
    public static String getDiskUsage() throws Exception{
        Long[] info = getDiskInfo();
        if(info==null || info.length<=0) return "0.00%";
        long total = info[0]+info[1];
        return new DecimalFormat("#0.00").format(info[0]/(total*1.0f));
    }

    /**
     * 已用磁盘空间情况
     * @return 下标0为已用，1为可用。
     */
    public static Long[] getDiskInfo() throws Exception{
        BufferedReader br = null ;
        Process process = null;
        Long[] str = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            String command = " df -a";
            process = runtime.exec(command);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = br.readLine())!=null){
                String[] split = line.split("\\s+");
                if(line.startsWith("/dev")&&split.length>3){
                    str = new Long[2];
                    str[0] = Long.parseLong(line.split("\\s+")[2]);
                    str[1] =  Long.parseLong(line.split("\\s+")[3]);
                    break;
                }else if(line.startsWith("	")||line.startsWith(" ")){
                    str = new Long[2];
                    str[0] =  Long.parseLong(line.split("\\s+")[1]);
                    str[1] =  Long.parseLong(line.split("\\s+")[2]);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(process!=null) process.destroy();
            if(br!=null) br.close();
        }
        return str;
    }


    /**
     * 获取系统的信息
     * @return下标0表示 系统名称，下标1表示系统版本
     */
    public static String[] getSystemInfo(){
        Properties props = System.getProperties();
        String[] str = {props.getProperty("os.name"),props.getProperty("os.version")};
        return str;
    }

    private static class Read {
        private BufferedReader in1;
        private long idleCpuTime1;
        private long totalCpuTime1;

        public Read(BufferedReader in1, long idleCpuTime1, long totalCpuTime1) {
            this.in1 = in1;
            this.idleCpuTime1 = idleCpuTime1;
            this.totalCpuTime1 = totalCpuTime1;
        }

        public long getIdleCpuTime1() {
            return idleCpuTime1;
        }

        public long getTotalCpuTime1() {
            return totalCpuTime1;
        }

        public Read invoke() throws IOException {
            String line;
            while((line=in1.readLine()) != null){
                if(line.startsWith("cpu")){
                    line = line.trim();
                    String[] temp = line.split("\\s+");
                    idleCpuTime1 = Long.parseLong(temp[4]);
                    for(String s : temp){
                        if(!s.equals("cpu")){
                            totalCpuTime1 += Long.parseLong(s);
                        }
                    }
                    break;
                }
            }
            return this;
        }
    }
}
