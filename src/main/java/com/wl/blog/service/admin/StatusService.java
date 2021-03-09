package com.wl.blog.service.admin;

import com.wl.blog.dao.StatusMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StatusService {
    @Resource
    StatusMapper statusMapper;

    //根据id查询文章的状态
    public String findStatusById(int StatusId) {
        return statusMapper.findStatusById(StatusId);
    }
}
