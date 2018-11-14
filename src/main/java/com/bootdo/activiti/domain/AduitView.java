package com.bootdo.activiti.domain;

import java.util.Date;

public class AduitView {
    private Integer id;

    private String aduitPsn;

    private String aduitState;

    private String aduitView;

    private String businessKey;

    private String taskId;

    private Date createTime;

    private String remark;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAduitPsn() {
        return aduitPsn;
    }

    public void setAduitPsn(String aduitPsn) {
        this.aduitPsn = aduitPsn == null ? null : aduitPsn.trim();
    }

    public String getAduitState() {
        return aduitState;
    }

    public void setAduitState(String aduitState) {
        this.aduitState = aduitState == null ? null : aduitState.trim();
    }

    public String getAduitView() {
        return aduitView;
    }

    public void setAduitView(String aduitView) {
        this.aduitView = aduitView == null ? null : aduitView.trim();
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey == null ? null : businessKey.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}