package com.geekplus.springsecurity.enums;

public enum Status {
    ENABLE(1L),
    DISENABLE(0L),
    DELETE(99L);
    private Long status;

    Status(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
