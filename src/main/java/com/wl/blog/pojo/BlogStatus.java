package com.wl.blog.pojo;

public class BlogStatus {
    private int StatusId;


    public int getStatusId() {
        return StatusId;
    }

    @Override
    public String toString() {
        return "BlogStatus{" +
                "StatusId=" + StatusId +
                ", StatusDesc='" + StatusDesc + '\'' +
                '}';
    }

    public void setStatusId(int statusId) {
        StatusId = statusId;
    }

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    private String StatusDesc;
}
