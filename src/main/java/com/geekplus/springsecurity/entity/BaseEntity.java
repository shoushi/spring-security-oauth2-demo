package com.geekplus.springsecurity.entity;

import java.io.Serializable;
import java.sql.Date;

public class BaseEntity implements Serializable {
    public Date inputTime;
    public Date lastModifyTime;

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
