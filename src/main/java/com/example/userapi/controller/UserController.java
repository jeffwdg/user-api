package com.example.userapi.controller;

import com.example.userapi.model.Email;
import com.example.userapi.model.User;
import com.example.userapi.service.EmailService;
import com.example.userapi.service.UserServiceImpl;
import com.example.userapi.utility.Utility;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Api( description = "User controller  | Api Annotation")
@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    EmailService emailService;

    Utility utility = new Utility();
    List<String> errors = new ArrayList<>();


    @ApiOperation(value = "List all users APIs")
    @GetMapping("/")
    public String viewHomePage(Model model) {
        System.out.println(userService.getUserList());
        model.addAttribute("users", userService.getUserList());
        return "index";
    }

    @ApiOperation(value = "Fetch users by id")
    @GetMapping(value = "/getUsers/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public User getUser(  @PathVariable("userId") Integer userId, Model model){
           System.out.println("prpertyId="+userId);
            if(userId != null){
                return userService.getUserById(userId);
            }
           return new User();
    }

    @ApiOperation(value = "Show the template for adding users")
    @GetMapping(value = "/addUser")
    @ResponseStatus(value = HttpStatus.OK)
    public String addUser(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "newuser";
    }

    @ApiOperation(value = "Save the user model")
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, Integer id, Model model) {
        boolean validEmail = false;
        String validName = null;
        errors = new ArrayList<>();
        if(user != null) {
            validEmail = utility.validateEmail(user.getEmail());
            if(!validEmail){
                errors.add("Invalid email address.");
            }
        }else{
            errors.add("Missing details. Please add.");
        }

        if(user != null) {
            validName = StringUtils.isBlank(user.firstName) || StringUtils.isBlank(user.lastName) ? "Missing first or last name" : "";
            errors.add(validName);
        }

        if(id == null && StringUtils.isEmpty(validName)){
            System.out.println("Adding user ");
            userService.addUser(user);
            Email email = new Email();
            email.setTo(user.getEmail());
            email.setFrom("test@gmail.com");
            email.setSubject("Registration Successful");
            email.setContent("Hi " + user.getFirstName() + " " + user.getLastName() + " \n Thank you for registering");

            emailService.send( email.getTo(), email.getSubject(), email.getContent());
            emailService.addEmail(email);
            return "redirect:/";
        }

        if(id != null && validEmail){
            System.out.println("Editing user "+ id);
            userService.editUser(id, user);
            return "redirect:/";
        }
        model.addAttribute("errors", errors);

        return "/newuser";
    }

    @ApiOperation(value = "Show the form for updating users")
    @GetMapping("/showFormForUpdate/{id}")
    public String updateForm( @PathVariable(value = "id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "updateuser";
    }

    @ApiOperation(value = "Edit the user by Id")
    @PostMapping("/editUser/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public User editUser( @PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println("user="+userId);
        if(user != null){
            return userService.editUser(userId, user);
        }
        return null;
    }

    @ApiOperation(value = "Delete user by id")
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(  Integer id) {
        if(id != null && Objects.nonNull(userService.getUserById(id))){
            userService.deleteUserById(id);
        }
        return "redirect:/";

    }

}
