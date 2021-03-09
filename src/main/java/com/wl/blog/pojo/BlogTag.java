package com.wl.blog.pojo;

public class BlogTag {
    private int TagId;
    private String TagDesc;

    public int getTagId() {
        return TagId;
    }

    @Override
    public String toString() {
        return "BlogTag{" +
                "TagId=" + TagId +
                ", TagDesc='" + TagDesc + '\'' +
                ", TagCategoryId=" + TagCategoryId +
                '}';
    }

    public void setTagId(int tagId) {
        TagId = tagId;
    }

    public String getTagDesc() {
        return TagDesc;
    }

    public void setTagDesc(String tagDesc) {
        TagDesc = tagDesc;
    }

    public int getTagCategoryId() {
        return TagCategoryId;
    }

    public void setTagCategoryId(int tagCategoryId) {
        TagCategoryId = tagCategoryId;
    }

    private int TagCategoryId;
}
