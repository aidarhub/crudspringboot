package com.example.crudspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.crudspringboot.model.User;
import com.example.crudspringboot.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/")
    public String printUsers(Model model) {
        List<User> list = userService.listUsers();
        model.addAttribute("usersList", list);
        return "users";
    }

    @GetMapping("/admin")
    public String addUser() {
        return "redirect:/admin/";
    }

    @GetMapping("/")
    public String getMain() {
        return "redirect:/user";
    }
}
