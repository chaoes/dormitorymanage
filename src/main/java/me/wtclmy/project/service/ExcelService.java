package me.wtclmy.project.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @auther:chaoe
 * @date:2020/7/8
 **/


public interface ExcelService {
    public HSSFWorkbook getOrderExcel();
}
