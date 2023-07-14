package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showAllUsers(@ModelAttribute("user") User user, ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        User authUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", authUser);
        List<User> allUsersList = userService.getAllUsers();
        model.addAttribute("allUsersList", allUsersList);
        return "users";
    }


    @PostMapping("/")
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "/edit";

        userService.updateUser(id, user);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showOneUser(@PathVariable("id") long id1, ModelMap model) {
        model.addAttribute("user", userService.getUser(id1));
        return "user";
    }
}