package com.sundy.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年06月12日 9:49:59
 * 描述：
 */
public class FileUtils {

    private static final int MAX_CACHE_SIZE = 1024*4;

    /**
     * 给对应的文件追加data数据
     * @param filename 文件全路径
     * @param data  追加的内容
     * @param append 是否已追加的形式,false直接清空文件
     * @param newLine 换行再追加
     * @throws IOException
     */
    public static synchronized void write(String filename,String data,boolean append,boolean newLine) throws IOException{
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filename, "rw");
            if(append){
                raf.seek(raf.length());
            }else {
                raf.setLength(data.length());
                raf.seek(0);
            }

            if(newLine && raf.length() != 0 && append){
                raf.write("\n".getBytes());
            }
            raf.write(data.getBytes());
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }

    /**
     * 给对应的文件前追加内容
     * @param file 文件
     * @param data 追加的数据
     * @param newLine 是否在追加内容后换行
     * @throws IOException
     */
    public static synchronized void appendFirst(File file,String data,boolean newLine) throws IOException{
        String filename = file.getAbsolutePath();
        int index = filename.lastIndexOf(File.separatorChar);
        String dest = filename.substring(0, index) + File.separatorChar + UUID.randomUUID().toString().replaceAll("-","");
        FileOutputStream fos = null;
        RandomAccessFile raf = null;
        try{
            fos = new FileOutputStream(dest);
            raf = new RandomAccessFile(filename,"r");
            fos.write(data.getBytes(),0,data.getBytes().length);

            if (newLine){ //追加到文件的前面，并且进行换行
                fos.write("\n".getBytes());
            }

            if(raf.length()>0){
                byte[] cache = new byte[(int) raf.length()];
                raf.read(cache);
                fos.write(cache,0,cache.length);
                fos.flush();
            }
            raf.close();
            fos.close();
            File destFile = new File(dest);
            file.delete();
            destFile.renameTo(file);
        }finally {
            if(raf != null){
                raf.close();
            }
            if(fos != null){
                fos.close();
            }
        }
    }

}
