package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Service.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {
	private final UserServiceImpl userServiceImp;

	@Autowired
	public UserController(UserServiceImpl userServiceImp) {
		this.userServiceImp = userServiceImp;
	}

	@GetMapping()
	public String showThisUser(ModelMap model, Principal principal) {
		model.addAttribute("user", userServiceImp.findByUsername(principal.getName()));
		return "user";
	}

}