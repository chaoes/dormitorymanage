package me.wtclmy.project.service;

import me.wtclmy.project.pojo.Order;
import me.wtclmy.project.utils.MyDate;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/7/8
 **/

@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    private OrderServiceImpl orderService;
    @Override
    public HSSFWorkbook getOrderExcel() {
        List<Order> finisheds = orderService.queryFinishedOrder();
        List<Order> unfinishs = orderService.queryUnfinishOrder();

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("未处理报修单");
        hssfSheet.setDefaultColumnWidth(10);
        hssfSheet.setDefaultRowHeight((short) 4);
        HSSFRow row = hssfSheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("未处理报修单");
        hssfSheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        HSSFRow row2 = hssfSheet.createRow(1);
        row2.createCell(0).setCellValue("报修单号");
        row2.createCell(1).setCellValue("宿舍号");
        row2.createCell(2).setCellValue("报修信息");
        row2.createCell(3).setCellValue("报修时间");
        int i=2;
        for (Order unfinish:unfinishs) {
            HSSFRow hssfRow = hssfSheet.createRow(i);
            hssfRow.createCell(0).setCellValue(unfinish.getOrderId());
            hssfRow.createCell(1).setCellValue(unfinish.getDormitory().getDormitoryId());
            hssfRow.createCell(2).setCellValue(unfinish.getOrderInfo());
            hssfRow.createCell(3).setCellValue(MyDate.dateToString(unfinish.getOrderStart()));
            i++;
        }
        HSSFSheet hssfSheet1 = hssfWorkbook.createSheet("已处理报修单");
        hssfSheet1.setDefaultColumnWidth(10);
        hssfSheet1.setDefaultRowHeight((short) 4);
        HSSFRow row3 = hssfSheet1.createRow(0);
        row3.createCell(0).setCellValue("已处理报修单");
        hssfSheet1.addMergedRegion(new CellRangeAddress(0,0,0,4));
        HSSFRow row4 = hssfSheet1.createRow(1);
        row4.createCell(0).setCellValue("报修单号");
        row4.createCell(1).setCellValue("宿舍号");
        row4.createCell(2).setCellValue("报修信息");
        row4.createCell(3).setCellValue("报修时间");
        row4.createCell(4).setCellValue("处理时间");
        i=2;
        for (Order finished:finisheds
             ) {
            HSSFRow hssfRow = hssfSheet1.createRow(i);
            hssfRow.createCell(0).setCellValue(finished.getOrderId());
            hssfRow.createCell(1).setCellValue(finished.getDormitory().getDormitoryId());
            hssfRow.createCell(2).setCellValue(finished.getOrderInfo());
            hssfRow.createCell(3).setCellValue(MyDate.dateToString(finished.getOrderStart()));
            hssfRow.createCell(4).setCellValue(MyDate.dateToString(finished.getOrderFinish()));
            i++;
        }
        return hssfWorkbook;
    }
}
