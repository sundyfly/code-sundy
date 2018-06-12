package com.sundy.common.util;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年06月08日 14:44:55
 * 描述：获取JVM的一些基本信息
 */
public class JVMUtits {

    /**
     * Java的运行环境版本
     */
    public static String getJavaVersion(){
        return System.getProperties().getProperty("java.version");
    }

    /**
     * Java的运行环境供应商
     */
    public static String getJavaVendor(){
        return System.getProperties().getProperty("java.vendor");
    }

    /**
     * Java供应商的URL
     */
    public static String getJavaVendorURL(){
        return System.getProperties().getProperty("java.vendor.url");
    }

    /**
     * Java的安装路径
     */
    public static String getJavaHome(){
        return System.getProperties().getProperty("java.home");
    }

    /**
     * Java的虚拟机规范版本
     */
    public static String getVmSpecificationVersion(){
        return System.getProperties().getProperty("java.vm.specification.version");
    }

    /**
     * Java的虚拟机规范供应商
     */
    public static String getVmSpecificationVendor(){
        return System.getProperties().getProperty("java.vm.specification.vendor");
    }

    /**
     * Java的虚拟机规范名称
     */
    public static String getVmSpecificationName(){
        return System.getProperties().getProperty("java.vm.specification.name");
    }

    /**
     * Java的虚拟机实现版本
     */
    public static String getVmVersion(){
        return System.getProperties().getProperty("java.vm.version");
    }

    /**
     * Java的虚拟机实现供应商
     */
    public static String getVmVendor(){
        return System.getProperties().getProperty("java.vm.vendor");
    }

    /**
     * Java的虚拟机实现名称
     */
    public static String getVmName(){
        return System.getProperties().getProperty("java.vm.name");
    }

    /**
     * Java运行时环境规范版本
     */
    public static String getSpecificationVeriosn(){
        return System.getProperties().getProperty("java.specification.version");
    }

    /**
     * Java运行时环境规范供应商
     */
    public static String getSpecificationVendor(){
        return System.getProperties().getProperty("java.specification.vendor");
    }

    /**
     * Java运行时环境规范名称
     */
    public static String getSpecificationName(){
        return System.getProperties().getProperty("java.specification.name");
    }

    /**
     * Java的类格式版本号
     */
    public static String getClassVersion(){
        return System.getProperties().getProperty("java.class.version");
    }

    /**
     * Java的类路径
     */
    public static String getClassPath(){
        return System.getProperties().getProperty("java.class.path");
    }

    /**
     * 加载库时搜索的路径列表
     */
    public static String getLibraryPath(){
        return System.getProperties().getProperty("java.library.path");
    }

    /**
     * 默认的临时文件路径
     */
    public static String getIoTmpdir(){
        return System.getProperties().getProperty("java.io.tmpdir");
    }

    /**
     * 一个或多个扩展目录的路径
     */
    public static String getExtDirs(){
        return System.getProperties().getProperty("java.ext.dirs");
    }

    /**
     * 操作系统的名称
     */
    public static String getOsName(){
        return System.getProperties().getProperty("os.name");
    }

    /**
     * 操作系统的构架
     */
    public static String getOsArch(){
        return System.getProperties().getProperty("os.arch");
    }

    /**
     * 操作系统的版本
     */
    public static String getOsVersion(){
        return System.getProperties().getProperty("os.version");
    }

    /**
     * 文件分隔符
     */
    public static String getFileSepartor(){
        return System.getProperties().getProperty("file.separator");
    }

    /**
     * 路径分隔符
     */
    public static String getPathSeparator(){
        return System.getProperties().getProperty("path.separator");
    }

    /**
     * 行分隔符
     */
    public static String getLineSeparator(){
        return System.getProperties().getProperty("line.separator");
    }

    /**
     * 用户的账户名称
     */
    public static String getUserName(){
        return System.getProperties().getProperty("user.name");
    }

    /**
     * 用户的主目录
     */
    public static String getUserHome(){
        return System.getProperties().getProperty("user.home");
    }

    /**
     * 用户的当前工作目录
     */
    public static String getUserDir(){
        return System.getProperties().getProperty("user.dir");
    }

    /**
     *
     */
    public static void main(String[] args) {
        System.out.println(JVMUtits.getClassPath());
    }

}
