package com.shopping.domain;

import com.shopping.constant.SysConstant;

/**
 * @Name: FileTypeEnum
 * @Description: 文件类型枚举
 * @Author cy
 * @Date 2018/5/710:06
 */
public enum  FileTypeEnum {

    /**
     * 商品
     */
    EXCEL_FILE_PRODUCT(SysConstant.PRODUCT_TYPE,SysConstant.PRODUCT_FILE_NAME),
    /**
     * 商品类型
     */
    EXCEL_FILE_PRODUCTTYPE(SysConstant.PRODUCTTYPE_TYPE,SysConstant.PRODUCTTYPE_FILE_NAME),;

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    FileTypeEnum(String type, String name){
        this.type=type;
        this.name=name;
    }
}
