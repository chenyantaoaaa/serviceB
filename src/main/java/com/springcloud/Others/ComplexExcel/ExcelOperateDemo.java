package com.springcloud.others.ComplexExcel;

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
import java.io.IOException;

/**
 * Created by chenyantao
 * 2018/7/5.
 */
public class ExcelOperateDemo {
    public static void read() {
        File file = new File("C:\\Users\\chen\\Desktop\\test.xlsx");
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
            for (int i = 0; i < rowLength; i++) {
                XSSFRow xssfRow1 = xssfSheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    XSSFCell xssfCell1 = xssfRow1.getCell(j);
                    if (xssfCell1 != null) {
                        xssfCell1.setCellType(CellType.STRING);
                    }
                    System.out.print(xssfCell1.getStringCellValue() + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read();
    }
}
