package com.shopping.service;

import com.shopping.domain.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface SystemService {

    public List<Map<String,Object>>getReport(String type);
    public int addItem(String type);
    public int delete(String pId,String type);
    public int update(String row,String type);
    public ResultVo excelToTable(MultipartFile file,FileTypeEnum fileTypeEnum);
}
