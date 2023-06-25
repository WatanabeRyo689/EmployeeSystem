package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	 @Autowired
	    public UserController(UserService userService) {
	        this.userService = userService;
	}
	
	@GetMapping("/userList")
	public String write1(UserForm userForm,Model model) {
		
		List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
		
		model.addAttribute("moji", "hello world!");
		return "userList";
	}
	
	// ユーザー情報登録画面表示
	@GetMapping("/userRegister")
    public String showUserRegister( UserForm userForm, Model model) {
		model.addAttribute("moji", "hello world!");
		return "userRegister";
    }
	
	// ユーザー情報登録画面表示
	@PostMapping("/userRegister/insert")
    public String test(@ModelAttribute  @Validated UserForm userForm,  BindingResult result,Model model) {
		if (result.hasErrors()) {
	        return "userRegister";
	    }
		//userService.getUser(userForm.getUserId());
		userService.createUser(userForm);
		
		model.addAttribute("moji", "hello world!");
		return "userList";
	}
}
