package com.shopping.action;

import com.alibaba.fastjson.JSON;
import com.shopping.constant.SysConstant;
import com.shopping.domain.*;
import com.shopping.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class SystemAction {

    @Autowired
    private SystemService systemService;

    private Map<String,Object> resultJson;

    public Map<String, Object> getResultJson() {
        return resultJson;
    }

    public void setResultJson(Map<String, Object> resultJson) {
        this.resultJson = resultJson;
    }
    private String result;
    public String getResult() {
        return result;
    }

    private File excelFile;

    public File getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    private static final Logger logger= LoggerFactory.getLogger(SystemAction.class);

    /**
     * 数据导入-缓冲区大小
     */
    private static final int BUFFER_SIZE = 4096;

    public String home(){
        User user=(User)ServletActionContext.getRequest().getSession().getAttribute(SysConstant.SESSION_USER_INFO);
        if (user ==null)
            throw new NullPointerException("用户信息丢失！请重新登录！");
        logger.info("--------------------->this is SystemAction");
        return "system";
    }
    //获取
    public String getReport(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String type=request.getParameter("type");
        if (StringUtils.isBlank(type))
            throw new IllegalArgumentException("非法参数：所传参数为空！");
        Map<String,Object>map=new HashMap<String, Object>();
        List<Map<String,Object>>list=systemService.getReport(type);
        map.put("data",list);
        map.put("total",list.size());
        this.resultJson=map;
        return "ajaxJson";
    }
    //添加
    public String addItem(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String type=request.getParameter("type");
        int row=systemService.addItem(type);
        this.result="false";
        if (row > 0){
            this.result="true";
        }
        return "resultbBoolean";
    }
    //修改
    public String update(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String type=request.getParameter("type");
        String row=request.getParameter("row");
        int count= systemService.update(row,type);
        this.result="false";
        if(count == 1)
            this.result="true";
        return "resultbBoolean";
    }
    //删除
    public String delete(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String id=request.getParameter("id");
        String type=request.getParameter("type");
        this.result="false";
        if(StringUtils.isBlank(id) || StringUtils.isBlank(type))
            throw new IllegalArgumentException("非法参数：参数错误，所传参数为空！");
        int count=systemService.delete(id,type);
        if (count != 0)
            this.result="true";
        return "resultbBoolean";
    }

    /**
     * 导出模板
     */
    public void excelTemplateDownload(){
        HttpServletResponse response= ServletActionContext.getResponse();
        HttpServletRequest request= ServletActionContext.getRequest();
        String fileCnName;
        ServletOutputStream out = null;
        FileInputStream inputStream = null;
        try {
            String type = request.getParameter("type");
            if (FileTypeEnum.EXCEL_FILE_PRODUCT.getType().equals(type)) {
                fileCnName = FileTypeEnum.EXCEL_FILE_PRODUCT.getName();
            }else {
                throw new IllegalArgumentException("参数异常！请勿非法提交参数！");
            }
            URL url = SystemAction.class.getClassLoader().
                    getResource("fileTemplate/" + fileCnName);
            File file = new File(url.getFile());
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName="+ URLEncoder.encode(fileCnName, SysConstant.UTF8_ENCODING));

            inputStream = new FileInputStream(file);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((b = inputStream.read(buffer))!= -1){
                //4.写到输出流(out)中
                out.write(buffer, 0, b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (Exception e) {
            logger.error("系统管理 -> 数据导入 -> 模板下载出错！原因:{}", e.getMessage());
            e.printStackTrace();
        } finally {
            closeStream(out, inputStream);
        }
    }
    private void closeStream(ServletOutputStream out, FileInputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (out != null) {
                out.close();
                out.flush();
            }
        } catch (Exception e) {
            logger.error("系统管理 -> 数据导入 -> 关闭流时出错！原因:" + e.getMessage());
        }
    }

    /**
     * 上传文件
     */
    public String fileUpLoad() throws IOException{
        MultipartFile file=new MockMultipartFile(SysConstant.PRODUCT_FILE_NAME,
                SysConstant.PRODUCT_FILE_NAME, "text/plain",new FileInputStream(excelFile));
        logger.info("上传文件为：{}",file.getName());
        ResultVo resultVo=new ResultVo();
        //格式验证
        String[] file_str = file.getOriginalFilename().split("\\.");
        if (file_str.length != 2 || ! ("xlsx".equals(file_str[1])
                || !"xls".equals(file_str[1])) ) {
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg("文件格式错误！请核对文件名及文件格式！");
            return "";
        }
        String fileCnName = file_str[0];
        try{
            //暂定，上传文件名必须跟模板名一致
            if (SysConstant.PRODUCT_FILE_NAME.substring(
                    0, SysConstant.PRODUCT_FILE_NAME.lastIndexOf("."))
                    .equals(fileCnName)) {
                //1.====================
                resultVo = systemService.excelToTable(file, FileTypeEnum.EXCEL_FILE_PRODUCT);
            } else {
                resultVo.setResult_code("000");
                resultVo.setResult_msg("文件名错误！请勿修改模板文件名！");
            }
        } catch (Exception e) {
            resultVo.setResult_code(SysConstant.ERROR);
            resultVo.setResult_msg("文件入库出错！原因:" +
                    e.getMessage() + ",请联系管理员处理！");
            logger.error("文件入库出错！原因:{}", e.getMessage());
            e.printStackTrace();
        }
        this.result= JSON.toJSONString(resultVo);
        return "resultbBoolean";
    }
}
