package com.wl.blog.dao;

import com.wl.blog.pojo.Picture;

import java.util.List;

public interface PictureMapper {
    public List<Picture> findAllPictures();

    public int addPicture(Picture picture);

    public int findPictureByPath(String path);

    public Picture findPictureById(int PictureRowId);
}
