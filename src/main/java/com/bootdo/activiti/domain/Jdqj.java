package com.bootdo.activiti.domain;

import java.util.Date;

public class Jdqj {
    private Integer id;

    private String reason;

    private Date crdt;

    private String crpsn;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCrdt() {
        return crdt;
    }

    public void setCrdt(Date crdt) {
        this.crdt = crdt;
    }

    public String getCrpsn() {
        return crpsn;
    }

    public void setCrpsn(String crpsn) {
        this.crpsn = crpsn == null ? null : crpsn.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}

