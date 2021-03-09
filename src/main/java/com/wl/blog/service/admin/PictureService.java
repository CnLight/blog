package com.wl.blog.service.admin;

import com.wl.blog.dao.ArticleMapper;
import com.wl.blog.dao.PictureMapper;
import com.wl.blog.pojo.BlogArticle;
import com.wl.blog.pojo.Picture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PictureService {
    @Resource
    PictureMapper pictureMapper;

    //查询所有的文章
    public List<Picture> findAllPictures() {
        return pictureMapper.findAllPictures();
    }


    //创建一篇文章
    public int addPicture(Picture picture) {
        pictureMapper.addPicture(picture);
        return picture.getPictureRowId();
    }

    public int findPictureByPath(String path) {
        return pictureMapper.findPictureByPath(path);
    }
    //查找文章的路径

    public String findPicturePathById(int id) {
        return pictureMapper.findPictureById(id).getPicturePath();
    }
}


