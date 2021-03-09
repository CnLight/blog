package com.wl.blog.pojo;

import java.util.Date;

public class BlogArticle {
    private int ArticleId;
    private String ArticleCode;
    private String ArticleName;
    private int ArticleCategoryId;
    private int ArticlePictureId;
    private String ArticleContent;
    private String ArticlePictureUrl;
    private String ArticleSummary;
    private String ArticleTag;

    private Date ArticleUpdateTime;
    private Date ArticleCreateTime;
    private int ArticleStatusId;
    private String StatusDesc;

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "BlogArticle{" +
                "ArticleId=" + ArticleId +
                ", ArticleCode='" + ArticleCode + '\'' +
                ", ArticleName='" + ArticleName + '\'' +
                ", ArticleCategoryId=" + ArticleCategoryId +
                ", ArticlePictureId=" + ArticlePictureId +
                ", ArticleContent='" + ArticleContent + '\'' +
                ", ArticlePictureUrl='" + ArticlePictureUrl + '\'' +
                ", ArticleSummary='" + ArticleSummary + '\'' +
                ", ArticleTag='" + ArticleTag + '\'' +
                ", ArticleUpdateTime=" + ArticleUpdateTime +
                ", ArticleCreateTime=" + ArticleCreateTime +
                ", ArticleStatusId=" + ArticleStatusId +
                ", StatusDesc='" + StatusDesc + '\'' +
                '}';
    }


    public Date getArticleUpdateTime() {
        return ArticleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        ArticleUpdateTime = articleUpdateTime;
    }

    public Date getArticleCreateTime() {
        return ArticleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        ArticleCreateTime = articleCreateTime;
    }

    public int getArticleStatusId() {
        return ArticleStatusId;
    }

    public void setArticleStatusId(int articleStatusId) {
        ArticleStatusId = articleStatusId;
    }

    public String getArticleTag() {
        return ArticleTag;
    }

    public void setArticleTag(String articleTag) {
        ArticleTag = articleTag;
    }

    public String getArticleSummary() {
        return ArticleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        ArticleSummary = articleSummary;
    }


    public String getArticlePictureUrl() {
        return ArticlePictureUrl;
    }

    public void setArticlePictureUrl(String articlePictureUrl) {
        ArticlePictureUrl = articlePictureUrl;
    }

    public String getArticleContent() {
        return ArticleContent;
    }

    public void setArticleContent(String articleContent) {
        ArticleContent = articleContent;
    }

    public int getArticleId() {
        return ArticleId;
    }

    public void setArticleId(int articleId) {
        ArticleId = articleId;
    }

    public String getArticleCode() {
        return ArticleCode;
    }

    public void setArticleCode(String articleCode) {
        ArticleCode = articleCode;
    }

    public String getArticleName() {
        return ArticleName;
    }

    public void setArticleName(String articleName) {
        ArticleName = articleName;
    }

    public int getArticleCategoryId() {
        return ArticleCategoryId;
    }

    public void setArticleCategoryId(int articleCategoryId) {
        ArticleCategoryId = articleCategoryId;
    }

    public int getArticlePictureId() {
        return ArticlePictureId;
    }

    public void setArticlePictureId(int articlePictureId) {
        ArticlePictureId = articlePictureId;
    }

}
