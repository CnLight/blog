package com.wl.blog.controller.admin;

import com.wl.blog.pojo.BlogUser;
import com.wl.blog.service.admin.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/user/dealUser")
    public String dealUser(Model model) {
        List<BlogUser> allUsers = userService.findAllUser();
        model.addAttribute("blogUsers", allUsers);
        return "user/dealUser";
    }

    @RequestMapping("/user/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user/dealUser";
    }

    @RequestMapping("user/toUpdateUser/{id}")
    public String toUpdateUser(@PathVariable int id, Model model) {
        BlogUser blogUser = userService.findUserById(id);
        model.addAttribute("blogUser", blogUser);
        return "user/toUpdateUser";
    }

    @RequestMapping("/user/updateUser")
    public String updateUser(BlogUser blogUser) {
        userService.updateUser(blogUser);
        return "redirect:/user/dealUser";
    }

    @RequestMapping("/user/addUser")
    public String addUser(BlogUser blogUser) {
        userService.addUser(blogUser);
        return "redirect:/user/dealUser";
    }

    @RequestMapping("/user/toAddUser")
    public String toAddUser() {
        return "user/toAddUser";
    }

    @RequestMapping("/user/toLogin")
    public String toLogin(HttpSession session) {
        if (session.getAttribute("blogUser") != null) return "redirect:/index";
        return "user/login";

    }

    @RequestMapping("/user/login")
    public String login(HttpSession session, BlogUser blogUser, Model model) {
        //判断session
        if (session.getAttribute("blogUser") == null) {
            //查询数据库有没有当前用户
            Map map = new HashMap<String, String>();
            map.put("user_name", blogUser.getUser_name());
            map.put("user_pwd", blogUser.getUser_pwd());
            BlogUser newBlogUser = userService.findAllUserByIdAndPwd(map);
            if (newBlogUser != null) {
                //添加session
                session.setAttribute("blogUser", newBlogUser);

            } else {
                model.addAttribute("msg", "用户名或密码不正确!");
                return "user/login";
            }
        }
        return "redirect:/index";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session) {
        //清除session
        if (session.getAttribute("blogUser") != null)
            session.removeAttribute("blogUser");
        return "redirect:/user/toLogin";
    }

}
