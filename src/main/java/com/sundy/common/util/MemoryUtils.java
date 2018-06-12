package com.sundy.common.util;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年06月08日 16:04:13
 * 描述：获取内存的一些信息
 */
public class MemoryUtils {
    /**
     * JVM可以使用的总内存
     */
    public static long getTotalMemory(){
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * JVM可以使用的剩余内存
     */
    public static long getFreeMemory(){
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * JVM可以使用的处理器个数
     */
    public static int getAvailableProcessors(){
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 内存总量
     */
    public static long getMenTotal() throws SigarException {
        return new Sigar().getMem().getTotal()/1024L;
    }

    /**
     * 当前内存剩余量
     */
    public static long getMenFree() throws SigarException {
        return new Sigar().getMem().getFree()/1024L;
    }

    /**
     * 当前内存使用量
     */
    public static long getMenUsed() throws SigarException {
        return new Sigar().getMem().getUsed()/1024L;
    }

    /**
     * 交换区总量
     */
    public static long getSwapTotal() throws SigarException {
        return new Sigar().getSwap().getTotal()/1024L;
    }

    /**
     * 当前交换区剩余量
     */
    public static long getSwapFree() throws SigarException {
        return new Sigar().getSwap().getFree()/1024L;
    }

    /**
     * 当前交换区使用量
     */
    public static long getSwapUsed() throws SigarException {
        return new Sigar().getSwap().getUsed()/1024L;
    }

}
