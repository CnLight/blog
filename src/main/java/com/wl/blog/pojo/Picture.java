package com.wl.blog.pojo;

public class Picture {
    private int PictureRowId;
    private String PicturePath;

    @Override
    public String toString() {
        return "Picture{" +
                "PictureRowId=" + PictureRowId +
                ", PicturePath='" + PicturePath + '\'' +
                '}';
    }

    public int getPictureRowId() {
        return PictureRowId;
    }

    public void setPictureRowId(int pictureRowId) {
        PictureRowId = pictureRowId;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }
}
