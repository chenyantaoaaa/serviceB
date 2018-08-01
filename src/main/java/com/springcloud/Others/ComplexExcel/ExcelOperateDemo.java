package com.springcloud.Others.ComplexExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by chenyantao
 * 2018/7/5.
 */
public class ExcelOperateDemo {
    public static void read(String inPath,String outPath,Map<String,String> map) {
        File file = new File(inPath);
        boolean isExcel2003 = inPath.toLowerCase().endsWith(".xls");
        if (!file.exists())
            System.out.println("文件不存在");
        try {
            FileInputStream is = new FileInputStream(file);
            Workbook wb = isExcel2003 ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            int rowLength = sheet.getLastRowNum()+1;
            Row row = sheet.getRow(0);
            int colLength = row.getLastCellNum();
            Cell cell = row.getCell(0);
            CellStyle cellStyle = cell.getCellStyle();
            Map<Integer,Integer> countMap = new HashMap<>();
            for (int i = 0; i < rowLength; i++) {
                Row curRow = sheet.getRow(i);
                int nonCount = 0;
                for (int j = 0; j < colLength; j++) {
                    Cell curCell = curRow.getCell(j);
                    if (curCell != null) {
                        curCell.setCellType(CellType.STRING);
                    }
                    if(map.containsKey(curCell.getStringCellValue())){
                        curCell.setCellValue(map.get(curCell.getStringCellValue()));
                        curCell.setCellStyle(cellStyle);
                    }else if(!map.containsKey(curCell.getStringCellValue()) && curCell.getStringCellValue().substring(0,1).equals("$")){
                        curCell.setCellValue("0");
                        curCell.setCellStyle(cellStyle);
                    }else {
                        nonCount++;
                    }
                    System.out.print(curCell.getStringCellValue() + "\t");
                }
                countMap.put(i,nonCount);
                System.out.println();
            }
            for (int i = 0; i < rowLength; i++) {
                Row cutRow = sheet.getRow(i);
                int rowCount = 0;
                for (int j = 0; j < colLength; j++) {
                    Cell cutRowCell = cutRow.getCell(j);
                    if(!cutRowCell.getStringCellValue().equals("0")){
                        rowCount++;
                    }
                }
                int nonCount = countMap.get(i);
                if(rowCount == nonCount){
                    sheet.removeRow(cutRow);
                }
            }
            FileOutputStream fileOut = new FileOutputStream(outPath);
            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String inPath = "src/main/resources/excelTmplate/test.xlsx";
        String outPath = "src/main/resources/excelResult/test.xlsx";
        Map<String,String> map = new HashMap<>();
        map.put("$A1","replace_A1");
        map.put("$B2","replace_B2");
        map.put("$C3","replace_C3");
        map.put("$D4","replace_D4");
        map.put("$E5","replace_E5");
        read(inPath,outPath,map);
    }
}
