package com.sundy.common.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 16:15:02
 * 描述：读取文件的工具类
 */
public class ReadFileUtils {
    private static final Logger LOG = Logger.getLogger(ReadFileUtils.class);
    private long totalFileLength = 0;

    private ReadFileUtils(){}
    private static ReadFileUtils util = new ReadFileUtils();
    public static ReadFileUtils getInstance(){
        return util;
    }

    /**
     * 计算文件的大小
     * @param file
     */
    private void calcTotalFileLength(File file){
        File[] files = file.listFiles();
        if(files != null && files.length>0){
            for (int i = 0; i < files.length; i++) {
                if(files[i].isFile()){
                    totalFileLength+=files[i].length();
                }else if(files[i].isDirectory()){
                    calcTotalFileLength(files[i]);
                }
            }
        }
    }

    /**
     * 获取文件的总大小
     * @param path
     * @return
     */
    public long getTotalFileLength(String path){
        setTotalFileLength(0);
        calcTotalFileLength(new File(path));
        return totalFileLength;
    }
    /**
     * 获取文件的总大小
     * @param file
     * @return
     */
    public long getTotalFileLength(File file){
        setTotalFileLength(0);
        calcTotalFileLength(file);
        return totalFileLength;
    }

    /**
     * 计算下载的流量
     * @param file
     * @return
     */
    public String getTotalFlow(File file){
        String str ="";
        long total = getTotalFileLength(file)/1024;
        if(total>1024){
            if(total/1024>1024){
                str = new DecimalFormat("##0.00").format(total / 1024f / 1024f)+"GB";
            }else {
                str = new DecimalFormat("##0.00").format(total / 1024f) +"MB";
            }
        }else {
            str = total+"KB";
        }
        return str;
    }

    /**
     * 计算下载的流量
     * @param path
     * @return
     */
    public String getTotalFlow(String path){
        return getTotalFlow(new File(path));
    }

    private void setTotalFileLength(long totalFileLength) {
        this.totalFileLength = totalFileLength;
    }

    /**
     * 读取pdf文档,如果文档为图片类型得到的content和text为NUll<br>
     * 需要的包有 pdfbox-app-2.0.1.jar和commons-logging-1.2.jar<br>
     * 下载地址 https://archive.apache.org/dist/pdfbox/2.0.1/
     *
     * @param path
     *            文档的绝对路径
     * @return pdf的entity
     * @throws IOException
     * @throws Exception
     */
    public static PDFEntity readPDF(String path){
        File file = new File(path);
        PDDocument document = null;
        PDFEntity entity = new PDFEntity();
        try {
            document = PDDocument.load(file);
            int total = document.getNumberOfPages();
            entity.setTotalPageSize(total);
            entity.setVersion(document.getVersion());
            String[] content = new String[total];
            PDFTextStripper ps = new PDFTextStripper();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < total; i++) {
                ps.setStartPage(i + 1);
                ps.setEndPage(i + 1);
                content[i] = ps.getText(document);
                sb.append(ps.getText(document));
            }
            entity.setTxet(sb.toString().trim());
            entity.setContent(content);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("读取PDF异常：[path: "+path+"]  "+e);
        }finally{
            try {
                if(document!=null) document.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                LOG.error("读取PDF关闭PDDocument异常："+ex);
            }
        }

        return entity;
    }


    /**
     * 读取word文档，返回文档的内容 <br> https://www.apache.org/dyn/closer.lua/poi/release/bin/poi-bin-3.16-20170419.zip
     * 需要的包有:  commons-collections4-4.1.jar;poi-scratchpad-3.16.jar<br>
     * poi-3.16.jar ;xmlbeans-2.6.0.jar ;poi-ooxml-3.16.jar;poi-ooxml-schemas-3.16.jar<br>
     * @param path
     * @return
     */
    public static String readWord(String path) throws Exception{
        if (path.endsWith(".doc")) {
            return readWordDoc(new File(path));
        } else {
            return readWordDocx(new File(path));
        }
    }

    /**
     * 读取word文档，后缀为doc的文档
     * @param file
     * @return
     */
    public static String readWordDoc(File file) {
        FileInputStream fis = null;
        WordExtractor we = null;
        try {
            fis = new FileInputStream(file);
            we = new WordExtractor(fis);
            return we.getText();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("读取Word文档异常：[path: "+file.getAbsolutePath()+"] "+e);
            return "";
        }finally{
            try {
                if(we!=null) we.close();
                if(fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取word文档，后缀为docx的文档
     * @param file
     * @return
     */
    public static String readWordDocx(File file) {
        FileInputStream fis = null;
        XWPFDocument xd = null;
        XWPFWordExtractor xwpfWordExtractor = null;
        try {
            fis = new FileInputStream(file);
            xd = new XWPFDocument(fis);
            xwpfWordExtractor = new XWPFWordExtractor(xd);
            return xwpfWordExtractor.getText();
        } catch (Exception e) {
            return null;
        }finally{
            try {
                if(xwpfWordExtractor != null) xwpfWordExtractor.close();
                if(xd != null) xd.close();
                if(fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取Excel 文件返回文件的具体内容
     * @param path
     * @return
     * @throws Exception
     */
    public static String readExcle(String path) {
        Workbook workbook = null;
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        try {
            if (path.endsWith(".xlsx")) {
//				if(new File(path).length()>5242880){
//					ParseXlsxExcel excel = new ParseXlsxExcel(path);
//					excel.getAllContent().toString();
//				}
                workbook = new XSSFWorkbook(path);
                return getValue(workbook, sb);
            } else if(path.endsWith(".xls")){
                fis = new FileInputStream(path);
                workbook = new HSSFWorkbook(fis);

                return getValue(workbook, sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("读取Excel异常：[path: "+path+"]"+e);
        }finally{
            try {
                if(workbook != null)
                    workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fis!=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取所有sheet的值
     * @param workbook
     * @param sb
     * @return
     */
    private static String getValue(Workbook workbook, StringBuffer sb) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            for (int j = 0; j <= sheet.getLastRowNum(); j++) {
                sb.append(getCellValue(sheet.getRow(j)));
            }
        }
        return sb.toString();
    }

    /**
     * 获取一行中的每个cell的值
     * @param row
     * @return
     */
    private static String getCellValue(Row row) {
        if (row == null) return " ";
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k <= row.getLastCellNum(); k++) {
            Cell cell = row.getCell(k);
            if(cell!=null) sb.append(cell.toString()+"	");
        }
        return sb.toString()+"	\n";
    }

    /**
     * 建立一个Excel表格
     * @param path
     */
    public static void creatKeyword(String path){
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("涉密词汇");
            Row row = sheet.createRow(0);
            CellStyle style = setTitleStyle(workbook);
            setTitleCellValue(row,style);
            fos = new FileOutputStream(path);
            workbook.write(fos);
        } catch (Exception e) {

        }finally{
            try {
                if(fos!=null) fos.close();
                if(workbook!=null) workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置Excel中的标题
     * @param row
     * @param style
     */
    private static void setTitleCellValue(Row row, CellStyle style) {
        Cell cell = row.createCell(0);
        cell.setCellValue("关键字"); cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("关键字类别"); cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("关键字涉密等级（0、涉密；1、机密；2、绝密）"); cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("描述");cell.setCellStyle(style);
    }

    /**
     * 设置Excel中每个标题中cell的风格
     * @param workbook
     * @return
     */
    @SuppressWarnings("deprecation")
    private static CellStyle setTitleStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        Font font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12); //设置字体大小
        font.setColor(Font.COLOR_RED);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        return style;
    }

    /**
     * 读取ppt文档，返回文档的内容
     * @param path
     * @return
     * @throws Exception
     */
    public static String readPPT(String path){
        if(path.endsWith(".ppt")){
            PowerPointExtractor extractor = null;
            try {
                extractor = new PowerPointExtractor(path);
                return extractor.getText();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("读取ppt异常：[path： "+path+"] "+e);
            }finally{
                try {
                    if(extractor!=null) extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else if(path.endsWith(".pptx")){
            OPCPackage openPackage = null;
            XSLFPowerPointExtractor extractor = null;
            try {
                openPackage = POIXMLDocument.openPackage(path);
                extractor = new XSLFPowerPointExtractor(openPackage);
                return extractor.getText();
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("读取ppt异常：[path： "+path+"] "+e);
            }finally{
                try {
                    if(openPackage!=null)
                        openPackage.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } //关闭流不可以反过来，会导致ppt文档损坏

                try {
                    if(extractor!=null)
                        extractor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 读取.txt文件，返回文件的内容
     * @param path
     * @return
     * @throws IOException
     */
    public static String readTXT(String path) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuffer sb = new StringBuffer();
        String line = null;
        for(;(line=br.readLine())!=null;){
            sb.append(line+"\n");
        }
        if(br!=null) br.close();
        return sb.toString()==null ? "" : sb.toString();
    }

    /**
     * 把文本转成字符串
     * @param path
     * @return
     * @throws Exception
     */
    public static String readFileToString(String path) throws Exception{
        if(path.contains(".doc")){
            return readWord(path);
        }else if(path.contains(".xls")){
            return readExcle(path);
        }else if(path.contains(".pdf")){
            return readPDF(path).toString();
        }else if (path.contains("ppt")) {
            return readPPT(path);
        }else if(path.contains(".txt")){
            return readTXT(path);
        }
        return "";
    }
}
