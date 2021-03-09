package com.wl.blog.pojo;

public class ArticleCategory {
    private int CategoryId;
    private String CategoryCode;
    private String CategoryName;

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "CategoryId=" + CategoryId +
                ", CategoryCode='" + CategoryCode + '\'' +
                ", CategoryName='" + CategoryName + '\'' +
                '}';
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryCode() {
        return CategoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        CategoryCode = categoryCode;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
