package com.wl.blog.controller.admin;

import com.wl.blog.service.admin.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminController {
    @Resource
    UserService userService;

    @RequestMapping("/admin")
    public String dealIndex(Model model) throws ParseException {
        int userNum = userService.findNumFromUser();
        model.addAttribute("userNum", userNum);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDate = simpleDateFormat.format(System.currentTimeMillis());
        model.addAttribute("currentDate", currentDate);
        return "admin";
    }

}
