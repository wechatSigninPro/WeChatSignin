package com.nenu.edu.server.util;

import com.nenu.edu.server.vo.ExcelCall;
import com.nenu.edu.server.vo.ExcelCourse;
import com.nenu.edu.server.vo.ExcelStudent;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: Liang Jiayue
 * @Description:Poi工具类
 */
public class PoiUtil {

    /**
     * 生成excel文件
     *
     * @param courses
     * @return
     * @throws IOException
     */
    public static byte[] generateExcel(List<ExcelCourse> courses) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Workbook workbook = new XSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);

        for (ExcelCourse course : courses) {
            Sheet sheet = workbook.createSheet(course.getName() + "（" + course.getCode() + "）");
            Row row0 = sheet.createRow(0);
            Cell c00 = row0.createCell(0);
            c00.setCellValue("序号");
            c00.setCellStyle(style);
            Cell c01 = row0.createCell(1);
            c01.setCellValue("姓名");
            c01.setCellStyle(style);
            Cell c02 = row0.createCell(2);
            c02.setCellValue("学号");
            c02.setCellStyle(style);
            if (course.getStudents() != null
                    && course.getStudents().size() > 0
                    && course.getStudents().get(0).getCalls() != null
                    && course.getStudents().get(0).getCalls().size() > 0) {
                List<ExcelCall> calls = course.getStudents().get(0).getCalls();

                for (int k = 0; k < calls.size(); k++) {
                    ExcelCall call = calls.get(k);
                    Cell c03 = row0.createCell(k + 3);
                    c03.setCellValue("第" + (k + 1) + "次（" + dateFormat.format(call.getCreateTime()) + "）");
                    c03.setCellStyle(style);
                }
            }

            for (int i = 0; i < course.getStudents().size(); i++) {
                ExcelStudent student = course.getStudents().get(i);
                Row row = sheet.createRow(i + 1);
                Cell c0 = row.createCell(0);
                c0.setCellValue(i + 1);
                c0.setCellStyle(style);
                Cell c1 = row.createCell(1);
                c1.setCellValue(student.getName());
                c1.setCellStyle(style);
                Cell c2 = row.createCell(2);
                c2.setCellValue(student.getCampusId());
                c2.setCellStyle(style);

                sheet.autoSizeColumn(0);
                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);

                for (int j = 0; j < student.getCalls().size(); j++) {
                    Cell c3 = row.createCell(j + 3);
                    if (student.getCalls().get(j).getSignId() == null) {
                        c3.setCellValue("未签到");
                    } else {
                        c3.setCellValue("已签到");
                    }
                    c3.setCellStyle(style);
                    sheet.autoSizeColumn(j + 3);
                }

            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] result = outputStream.toByteArray();
        outputStream.close();
        workbook.close();

        return result;
    }
}