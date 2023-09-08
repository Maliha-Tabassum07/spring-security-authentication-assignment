package com.maliha.securitypractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class MainController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to spring security";
    }

    @GetMapping("/user")
    public String user(){
        return "This needs form authentication";
    }
    @GetMapping("/role")
    public String role(){ return "Anyone with either admin or user role can access this page";}

    @GetMapping("/role/admin")
    public String admin(){ return "You can delete user. So you need admin role"; }

    @GetMapping("/role/user/login")
    public String login(){ return "You need to login as normal user first";}

    @GetMapping("role/user/edit")
    public String edit(){return "If you login as a user you can edit your info";}

    @GetMapping("role/user/view")
    public String view(){return "All these are under user roles";}

    @GetMapping("authority/read")
    public String read(){return "this will be under READ_ONLY authority";}

    @GetMapping("authority/edit/user")
    public String editUser(){return "Authority to edit user info";}
    @GetMapping("authority/edit/Admin")
    public String editAdmin(){return "Authority to edit admin info";}

}
