package com.example.userapi.controller;

import com.example.userapi.service.EmailService;
import com.example.userapi.service.UserServiceImpl;
import com.example.userapi.utility.Utility;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Api( description = "Email controller  | Api Annotation")
@Controller
public class EmailController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    EmailService emailService;

    Utility utility = new Utility();
    List<String> errors = new ArrayList<>();


    @GetMapping("/emails")
    public String viewHomePage(Model model) {
        model.addAttribute("emails", emailService.getEmailList());

        return "email";
    }

}
