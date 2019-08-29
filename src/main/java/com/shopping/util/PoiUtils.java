package com.shopping.util;

import com.shopping.constant.SysConstant;
import com.shopping.domain.FileTypeEnum;
import com.shopping.domain.Product;
import com.shopping.domain.ResultVo;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * POI操作的工具类
 * Created by pq on 2017/7/17.
 */
@Component
public class PoiUtils {

    private static final Logger logger = LoggerFactory.getLogger(PoiUtils.class);
    /**
     * 表头所在行(从0开始，1代表第二行)
     */
    private static final Integer HEAD_ROW_NUM = 1;
    /**
     * 正文内容开始的行数
     * 第一行为表头说明信息，第二行为表头，第三行开始为正文内容
     */
    private static final Integer CONTENT_START_ROW_NUM = 2;
    /**
     * 默认的sheet页
     */
    private static final Integer DEFALUT_SHEET_NUM = 0;
    /**
     * 是否启用表头验证功能
     */
    private static Boolean enable_head_validate=true;

    /**
     * 解析excel
     * @param is
     * @param fileTypeEnum
     * @return
     */
    public static ResultVo parseExcel(InputStream is,
                                      FileTypeEnum fileTypeEnum) {
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        ResultVo resultVo = new ResultVo();
        try {
            xssfWorkbook = new XSSFWorkbook(is);
            sheet = xssfWorkbook.getSheetAt(DEFALUT_SHEET_NUM);
            row = sheet.getRow(HEAD_ROW_NUM);
            //上传文件的表头列数
            int colNum = row.getPhysicalNumberOfCells();
            //获取模板表头
            String[] template_head = getTemplateHead(fileTypeEnum);
            if (template_head == null) {
                resultVo.setResult_code(SysConstant.ERROR);
                resultVo.setResult_msg("读取表头模板失败！请联系管理员处理！");
                return resultVo;
            }
            //1、验证上传表头与模板表头是否一致
            if (enable_head_validate) {
                if (! validateHead(row, resultVo, colNum, template_head)) {
                    return resultVo;
                }
            }
            //2、读取excel内容
            resultVo = readExcelContent(sheet, fileTypeEnum);
            return resultVo;
        } catch (Exception e) {
            logger.error("系统管理 -> 数据导入 -> 解析excel出错！原因:{}", e.getMessage());
            e.printStackTrace();
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg("解析excel出错！请联系管理员处理！");
            return resultVo;
        }
    }
    /**
     * 验证表头
     * @param row
     * @param resultVo
     * @param colNum
     * @param template_head
     * @return
     */
    private static boolean validateHead(XSSFRow row, ResultVo resultVo,
                                        int colNum, String[] template_head) {
        //1、验证列数是否一致
        if (colNum != template_head.length) {
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg("表头验证失败！应为:" + template_head.length + "列，"
                    + "实际:" + colNum + "列！请按照模板上传！");
            return false;
        }
        //2、验证字段内容是否一致
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i)).trim();
            if (! template_head[i].equals(title[i])) {
                resultVo.setResult_code(SysConstant.ERROR);
                String err_msg = "上传文件的表头与模板不一致！出错位置:第"
                        + (colNum + 1) + "列，模板字段为:" + template_head[i] +
                        ",上传文件的字段为:" + title[i];
                resultVo.setResult_msg(err_msg);
                logger.error(err_msg);
                return false;
            }
        }
        logger.info("系统管理 -> 数据导入 -> 上传的excel表，表头验证通过！");
        return true;
    }

    /**
     * 获取模板表头
     * @param fileTypeEnum
     * @return
     */
    private static String[] getTemplateHead(FileTypeEnum fileTypeEnum) {
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet sheet = null;
        XSSFRow row = null;
        InputStream is = null;
        try {
            String fileCnName = fileTypeEnum.getName();
            is = PoiUtils.class.getClassLoader().getResourceAsStream("fileTemplate/" + fileCnName);
            xssfWorkbook = new XSSFWorkbook(is);
            sheet = xssfWorkbook.getSheetAt(DEFALUT_SHEET_NUM);
            row = sheet.getRow(HEAD_ROW_NUM);
            int colNum = row.getPhysicalNumberOfCells();
            String[] title = new String[colNum];
            for (int i = 0; i < colNum; i++) {
                title[i] = getCellFormatValue(row.getCell(i)).trim();
            }
            return title;
        } catch (Exception e) {
            logger.error("解析:{} 模板表头失败！原因:{}",
                    fileTypeEnum.getName(), e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 读取Excel数据内容
     * @param sheet
     * @return Map 包含单元格数据内容的Map对象
     */
    private static ResultVo readExcelContent(XSSFSheet sheet,
                                             FileTypeEnum fileTypeEnum) {
        ResultVo resultVo = null;
        if (SysConstant.PRODUCT_TYPE.equals(fileTypeEnum.getType())) {
            resultVo = getProductList(sheet);
        }
        return resultVo;
    }


    /**
     * 读取excel内容，存放到List<Product>
     * @param sheet
     * @return
     */
    private static ResultVo getProductList(XSSFSheet sheet) {
        ResultVo resultVo = new ResultVo();
        List<Product> productList = new ArrayList<Product>();
        Product product = null;
        XSSFRow row = null;
        int current_line = CONTENT_START_ROW_NUM ;
        int current_col = 0 ;
        try {
            long start = System.currentTimeMillis();
            //最大行数
            int rowNum = sheet.getLastRowNum();
            for (int i = CONTENT_START_ROW_NUM; i <= rowNum; i++) {
                current_line = i + 1;//3
                //每一行第二个元素开始
                current_col = 0;
                product = new Product();
                row = sheet.getRow(i);
                product.setName(getCellFormatValue(row.getCell(current_col++)).trim());
                product.setDetail(getCellFormatValue(row.getCell(current_col++)).trim());
                product.setPrice(new BigDecimal(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setVip_price(new BigDecimal(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setLave_quantity(Integer.valueOf(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setSale_quantity(Integer.valueOf(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setClick_rate(Integer.valueOf(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setPromotions_price(new BigDecimal(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setImage_path(getCellFormatValue(row.getCell(current_col++)).trim());
                product.setProduct_type(Integer.valueOf(getCellFormatValue(row.getCell(current_col++)).trim()));
                product.setDate(getCellFormatValue(row.getCell(current_col++)).trim());
                productList.add(product);
            }
            resultVo.setResult_code(SysConstant.SUCCESS);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("productList", productList);
            resultVo.setMap_data(map);
            long end = System.currentTimeMillis();
            logger.info("excel中数据转换成List<Product>成功！耗时：{} 毫秒！总行数:{}",
                    end-start, rowNum - CONTENT_START_ROW_NUM + 1);
            return resultVo;
        } catch (Exception e) {
            String err_msg = "上传失败！错误位置：第" + current_line + "行，" +
                    "第" + current_col + "列，原因:" + e.getMessage();
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg(err_msg);
            logger.error(err_msg);
        }
        return resultVo;
    }


    /**
     * 根据XSSFCell类型设置数据
     * @param cell
     * @return
     */
    private static String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        Date d = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        cellvalue=formater.format(d);
                    }else{
                        cell.setCellType(CellType.STRING);
                        cellvalue = cell.getStringCellValue();
                    }
                    break;
                case STRING:
                    cellvalue = cell.getStringCellValue();
                    break;
                case FORMULA:
                    DecimalFormat df = new DecimalFormat("0.00");
                    cellvalue = df.format(cell.getNumericCellValue());
                    break;
                // 默认的Cell值
                default:
                    if (cell.getCellType()==cell.CELL_TYPE_FORMULA){
                        DecimalFormat df1 = new DecimalFormat("0.00");
                        cellvalue = df1.format(cell.getNumericCellValue());
                    }else{
                        cellvalue = " ";
                    }
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}