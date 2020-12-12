package com.alphaboy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yaojun
 * @create 2020-12-12 09:37
 */
@Controller
public class UserController {
    /*测试spring boot*/
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello springboot!");
        return "ok";
    }
    /*add方法*/
    @RequestMapping("/add")
    public String add(){
        System.out.println("add method");
        return "/user/add";
    }
    /*add方法*/
    @RequestMapping("/update")
    public String update(){
        System.out.println("add method");
        return "/user/update";
    }
    /*跳转到登录页面*/
    @RequestMapping("/toLogin")
    public String toLogin(){
        System.out.println("toLogin method");
        return "login";
    }
    /*登录逻辑处理*/
    @RequestMapping("/login")
    public String login(String name, String password, Model model){
        /*编写shiro认证操作*/
        //1.获取subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功 跳转到test.html

            return "redirect:/thymeleaf";
        }catch (UnknownAccountException e){
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败：用户名不存在
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    /*
       测试thymeleaf
     */
    @RequestMapping("/thymeleaf")
    public String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("yaojun","hello world!");
        model.addAttribute("users", Arrays.asList("yaojun","junyao"));

        //返回test.html
        return "test";
    }
}
