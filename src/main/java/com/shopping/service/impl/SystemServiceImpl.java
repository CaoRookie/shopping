package com.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.shopping.constant.OrderConstant;
import com.shopping.constant.SysConstant;
import com.shopping.dao.SystemDao;
import com.shopping.domain.*;
import com.shopping.service.SystemService;
import com.shopping.util.PoiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemDao systemDao;

    private static final Logger logger= LoggerFactory.getLogger(SystemServiceImpl.class);

    public List<Map<String, Object>> getReport(String type) {
        return systemDao.getReport(type);
    }

    public int addItem(String type) {
        if (SysConstant.PRODUCT_TYPE.equals(type)){
            Product p=new Product();
            p.setProduct_type(1);//默认商品类型为1
            return systemDao.addItem(p);
        }
        if (SysConstant.PRODUCTTYPE_TYPE.equals(type)){
            ProductType p=new ProductType();
            return systemDao.addItem(p);
        }
        return 0;
    }

    public int delete(String pId, String type) {
        return systemDao.delete(pId,type);
    }

    public int update(String row, String type) {
        if(SysConstant.PRODUCT_TYPE.equals(type)){
            Product p= JSON.parseObject(row,Product.class);
            return systemDao.update(p);
        }else if(SysConstant.PRODUCTTYPE_TYPE.equals(type)){
            ProductType productType=JSON.parseObject(row,ProductType.class);
            return systemDao.update(productType);
        }else if(SysConstant.USER.equals(type)){
            User user=JSON.parseObject(row,User.class);
            User user1=systemDao.getUserById(user.getUser_id());
            if(user1.getUser_access() == 0){
                user1.setUser_access(777);
            }else{
                user1.setUser_access(0);
            }
            return systemDao.update(user1);
        }else if(OrderConstant.ORDER.equals(type)){
            Order order=JSON.parseObject(row,Order.class);
            order.setState(OrderConstant.RECEIVE);
            return systemDao.update(order);
        }
        return 0;
    }

    public ResultVo excelToTable(MultipartFile file, FileTypeEnum fileTypeEnum) {
        InputStream is = null;
        ResultVo resultVo = new ResultVo();
        try {
            //1、获取文件流
            is = file.getInputStream();
        } catch (IOException e) {
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg("获取上传文件出错！请重新导入！");
            logger.error("系统管理 -> 商品批量导入 -> 获取上传文件的输入流出错！错误原因：{}",
                    e.getCause());
            return resultVo;
        }
        //2、解析excel
        resultVo = PoiUtils.parseExcel(is, fileTypeEnum);
        //3、写入table
        if (! SysConstant.ERROR.equals(resultVo.getResult_code())) {
            resultVo = writeToTable(resultVo, fileTypeEnum, file);
        }
        return resultVo;
    }

    /***
     * 将数据写入表
     * @param resultVo
     * @param fileTypeEnum
     * @param file
     * @return
     */
    private ResultVo writeToTable(ResultVo resultVo,FileTypeEnum fileTypeEnum,MultipartFile file){
        ResultVo write_resultVo = new ResultVo();
        try {
            //1、入库
            if (SysConstant.PRODUCT_TYPE.equals(fileTypeEnum.getType())) {
                List<Product> list = (List<Product>) resultVo.getMap_data().get("productList");
                systemDao.writeProductToJdbc(list);
            }
            //4、返回结果
            write_resultVo.setResult_code(SysConstant.SUCCESS);
            write_resultVo.setResult_msg("文件入库成功！");
            return write_resultVo;
        } catch (Exception e) {
            write_resultVo.setResult_code(SysConstant.ERROR);
            write_resultVo.setResult_msg("文件入库失败！原因:" + e.getCause());
            logger.error("文件入库失败！原因:{}", e.getMessage());
            return write_resultVo;
        }
    }
}
