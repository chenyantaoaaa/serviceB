package com.springcloud.Others.ComplexExcel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyantao
 * 2018/7/5.
 */
public class ExcelOperateDemo {
    public static void read(String value, Map<String,String> map) {
//        String path = "C:\\Users\\chen\\Desktop\\test.xlsx";
        String inPath = "src/main/resources/excelTmplate/test.xlsx";
        String outPath = "src/main/resources/excelResult/test.xlsx";
        File file = new File(inPath);
        if (!file.exists())
            System.out.println("文件不存在");
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            int rowLength = xssfSheet.getLastRowNum()+1;
            XSSFRow xssfRow = xssfSheet.getRow(0);
            int colLength = xssfRow.getLastCellNum();
            XSSFCell xssfCell = xssfRow.getCell(0);
            CellStyle cellStyle = xssfCell.getCellStyle();
            Map<Integer,Integer> countMap = new HashMap<>();
            for (int i = 0; i < rowLength; i++) {
                XSSFRow xssfRow1 = xssfSheet.getRow(i);
                int nonCount = 0;
                for (int j = 0; j < colLength; j++) {
                    XSSFCell xssfCell1 = xssfRow1.getCell(j);
                    if (xssfCell1 != null) {
                        xssfCell1.setCellType(CellType.STRING);
                    }
                    if(map.containsKey(xssfCell1.getStringCellValue())){
                        xssfCell1.setCellValue(map.get(xssfCell1.getStringCellValue()));
                        xssfCell1.setCellStyle(cellStyle);
                    }else if(!map.containsKey(xssfCell1.getStringCellValue()) && xssfCell1.getStringCellValue().substring(0,1).equals("$")){
                        xssfCell1.setCellValue("0");
                        xssfCell1.setCellStyle(cellStyle);
                    }else {
                        nonCount++;
                    }
                    System.out.print(xssfCell1.getStringCellValue() + "\t");
                }
                countMap.put(i,nonCount);
                System.out.println();
            }
            for (int i = 0; i < rowLength; i++) {
                XSSFRow xssfRow2 = xssfSheet.getRow(i);
                int rowCount = 0;
                for (int j = 0; j < colLength; j++) {
                    XSSFCell xssfCell2 = xssfRow2.getCell(j);
                    if(!xssfCell2.getStringCellValue().equals("0")){
                        rowCount++;
                    }
                }
                int nonCount = countMap.get(i);
                if(rowCount == nonCount){
                    xssfSheet.removeRow(xssfRow2);
                }
            }
            FileOutputStream fileOut = new FileOutputStream(outPath);
            xssfWorkbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("$A1","replace_A1");
        map.put("$B2","replace_B2");
        map.put("$C3","replace_C3");
        map.put("$D4","replace_D4");
        map.put("$E5","replace_E5");
        read(null,map);
    }
}
