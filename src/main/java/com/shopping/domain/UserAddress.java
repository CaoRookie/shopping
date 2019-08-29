package com.shopping.domain;

import java.io.Serializable;

public class UserAddress implements Serializable{

    private static final long serialVersionUID = 2980517548391346747L;

    private Integer user_id;
    private Integer addrId;
    private Integer isDefaultAddr;
    private String receiptUserName;
    private String receiptPhone;
    private String address;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getIsDefaultAddr() {
        return isDefaultAddr;
    }

    public void setIsDefaultAddr(Integer isDefaultAddr) {
        this.isDefaultAddr = isDefaultAddr;
    }

    public String getReceiptUserName() {
        return receiptUserName;
    }

    public void setReceiptUserName(String receiptUserName) {
        this.receiptUserName = receiptUserName;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserAddress:{" +
                "user_id=" +user_id+
                ",addrId=" +addrId+
                ",isDefaultAddr=" +isDefaultAddr+'\''+
                ",receiptUserName=" +receiptUserName+'\''+
                ",receiptPhone=" +receiptPhone+'\''+
                ",address=" +address+'\''+
                "}";
    }
}
