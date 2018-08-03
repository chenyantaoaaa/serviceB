package com.springcloud.Others.ComplexExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by chenyantao
 * 2018/7/5.
 */
public class ExcelOperateDemo {
    public static void read(String inPath,String outPath,Map<String,String> map,List<Map<String,String>> forList) {
        File file = new File(inPath);
        boolean isExcel2003 = inPath.toLowerCase().endsWith(".xls");
        if (!file.exists())
            System.out.println("文件不存在");
        try {
            FileInputStream is = new FileInputStream(file);
            Workbook wb = isExcel2003 ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            int rowLength = sheet.getLastRowNum()+1;
            //countMap用于记录每行拥有初始默认值的项
            Map<Integer,Integer> countMap = new HashMap<>();
            for (int i = 0; i < rowLength; i++) {
                Row curRow = sheet.getRow(i);
                if(curRow == null){
                    continue;
                }
                int colLength = curRow.getLastCellNum();
                int nonCount = 0;
                for (int j = 0; j < colLength; j++) {
                    Cell curCell = curRow.getCell(j);
                    if (curCell == null) {
                       continue;
                    }else{
                       curCell.setCellType(CellType.STRING);
                    }
                    String curCellValue = curCell.getStringCellValue();
                    //cell内值为空 则跳到下一次循环
                    if(StringUtils.isEmpty(curCellValue)){
                        continue;
                    }

                    //找到$FOR标记代表需要循环赋值
                    if(curCellValue.length()>=4 && curCellValue.substring(0,4).equals("$FOR")){
                        //根据for标记循环赋值
                        assignForSign(curCellValue,curCell,forList,curRow);
                        continue;
                    }
                    if(map.containsKey(curCellValue)){
                        //替换cell值
                        curCell.setCellValue(map.get(curCell.getStringCellValue()));
                    }else if(!map.containsKey(curCellValue) && curCellValue.substring(0,1).equals("$")){
                        //map中没有的占位符用0替换
                        curCell.setCellValue("0");
                    }else {
                        //其他位置认为是初始默认值
                        nonCount++;
                    }
                    System.out.print(curCell.getStringCellValue() + "\t");
                }
                countMap.put(i,nonCount);
                System.out.println();
            }
            for (int i = 0; i < rowLength; i++) {
                Row cutRow = sheet.getRow(i);
                if(cutRow == null){
                    continue;
                }
                int rowCount = 0;
                int colLength = cutRow.getLastCellNum();
                for (int j = 0; j < colLength; j++) {
                    Cell cutRowCell = cutRow.getCell(j);
                    if (cutRowCell == null) {
                        continue;
                    }
                    String curCellValue = cutRowCell.getStringCellValue();
                    //cell内值为空 则跳到下一次循环
                    if(StringUtils.isEmpty(curCellValue)){
                        continue;
                    }
                    if(!curCellValue.equals("0")){
                        rowCount++;
                    }
                }
                int nonCount = countMap.get(i);
                //如果某行没有任何值被替换 则删除此行
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

    /**
     * 根据for标记循环赋值
     * @param curCellValue
     * @param curCell 当前cell
     * @param forList 循环list
     * @param curRow 当前行
     */
    private static void assignForSign(String curCellValue,Cell curCell,List<Map<String,String>> forList,Row curRow) {
        String key = curCellValue.substring(5,curCellValue.length());
        int curColumn = curCell.getColumnIndex();
        for (int k = 0; k < forList.size(); k++) {
            Map<String,String> forMap = forList.get(k);
            //默认找到for标记后就按照list长度循环添加cell
            if(k == 0){
                curCell.setCellValue(forMap.get(key));
            }else {
                Cell forCell = curRow.createCell(curColumn+k);
                forCell.setCellValue(forMap.get(key));
            }
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

        List<Map<String,String>> forList = new ArrayList<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        Map<String,String> map4 = new HashMap<>();
        map2.put("date","2018-03-02");
        map2.put("name","chen");
        map2.put("age","40");

        map3.put("date","2018-03-03");
        map3.put("name","zhao");
        map3.put("age","20");

        map4.put("date","2018-03-04");
        map4.put("name","wang");
        map4.put("age","22");
        forList.add(map2);
        forList.add(map3);
        forList.add(map4);
        read(inPath,outPath,map,forList);
    }
}
