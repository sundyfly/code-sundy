package com.sundy.common.util;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author sundy
 * @since 1.8
 * 日期: 2018年05月18日 16:10:36
 * 描述：解析大于5M的后缀为xlsx的Excel文件
 */
public class ParseXlsxExcel extends DefaultHandler{
    private SharedStringsTable sst;
    private String lastContents;
    private boolean nextIsString;
    private int sheetIndex = -1;
    private List<String> rowlist ;
    private int curRow = 0;
    private int curCol = 0;
    private ArrayList<String> list ;
    private String filepath;
    // excel记录行操作方法，以sheet索引，行索引和行元素列表为参数，对sheet的一行元素进行操作，元素为String类型
    public void optRows(int sheetIndex, int curRow, List<String> rowlist) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rowlist.size(); i++) {
            String row = rowlist.get(i);
            if (row != null) sb.append(row + "	");
        }
        list.add(sb.toString());
    }

    public ParseXlsxExcel(String path) {
        this.filepath = path;
        rowlist = new ArrayList<String>();
        list = new ArrayList<String>();
    }

    // 只遍历一个sheet，其中sheetId为要遍历的sheet索引，从1开始，1-3
    private void processOneSheet(int sheetId) throws Exception{
        rowlist.clear();
        OPCPackage pkg = null;
        InputStream sheet = null;
        pkg = OPCPackage.open(filepath, PackageAccess.READ);
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();
        XMLReader parser = fetchSheetParser(sst);
        // 根据 rId# 或 rSheet# 查找sheet
        sheet = r.getSheet("rId" + sheetId);
        sheetIndex++;
        InputSource sheetSource = new InputSource(sheet);
        parser.parse(sheetSource);
        if (sheet != null) sheet.close();
        if (pkg != null) pkg.close();

    }

    /** 遍历 excel 文件 */
    public XMLReader fetchSheetParser(SharedStringsTable sst)throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        this.sst = sst;
        parser.setContentHandler(this);
        return parser;
    }

    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        // c => 单元格
        if (name.equals("c")) {
            // 如果下一个元素是 SST 的索引，则将nextIsString标记为true
            String cellType = attributes.getValue("t");
            if (cellType != null && cellType.equals("s")) {
                nextIsString = true;
            } else {
                nextIsString = false;
            }
        }
        // 置空
        lastContents = "";
    }

    public void endElement(String uri, String localName, String name)
            throws SAXException {
        // 根据SST的索引值的到单元格的真正要存储的字符串
        // 这时characters()方法可能会被调用多次
        if (nextIsString) {
            try {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
                        .toString();
            } catch (Exception e) {

            }
        }

        // v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
        // 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
        if (name.equals("v")) {
            String value = lastContents.trim();
            value = value.equals("") ? " " : value;
            rowlist.add(curCol, value);
            curCol++;
        } else {
            // 如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
            if (name.equals("row")) {
                try {
                    optRows(sheetIndex, curRow, rowlist);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rowlist.clear();
                curRow++;
                curCol = 0;
            }
        }

    }

    public void characters(char[] ch, int start, int length)throws SAXException {
        // 得到单元格内容的值
        lastContents += new String(ch, start, length);
    }

    /**获取所有的content*/
    private ArrayList<String> processAllSheet()throws Exception{
        int x = 0;
        int temp = 0;
        ArrayList<String> all = new ArrayList<String>();
        while(x==temp){
            try {
                temp++;
                ArrayList<String> content = this.getSheetContent(temp);
                all.addAll(content);
                x = temp;
                System.err.println("=========="+temp+"==============");
            } catch (Exception e) {
                System.err.println("x= "+temp);
                e.printStackTrace();
                break;
            }
        }
        return list;
    }

    /**获取所有的content*/
    public ArrayList<String> getAllContent() throws Exception {
        return processAllSheet();
    }

    /**
     * 获取某个sheet的内容
     * @param sheetId
     * @return
     * @throws Exception
     */
    public ArrayList<String> getSheetContent(int sheetId) throws Exception{
        this.processOneSheet(sheetId);
        return list;
    }
}
