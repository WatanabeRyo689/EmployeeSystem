package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.form.UserEditForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/userList")
	public String userList(UserEditForm userEditForm, Model model) {

		List<User> userList = userService.getUserList();
		model.addAttribute("userEditForm", userList);
		return "userList";
	}

	// ユーザー情報登録画面表示
	@GetMapping("/userRegister")
	public String userRegister(UserRegisterForm userRegisterForm, Model model) {
		return "userRegister";
	}

	// ユーザー情報登録画面表示
	@PostMapping("/userRegister/insert")
	public String userRegister(@ModelAttribute @Validated UserRegisterForm userRegisterForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "userRegister";
		}
		//userService.getUser(userForm.getUserId());
		userService.createUser(userRegisterForm);
		return "redirect:/userList";
	}

	@PostMapping(value = "/userEdit")
	public String userEdit(@ModelAttribute UserEditForm userEditForm, BindingResult bindingResult, Model model) {
		User editUser = userService.getUser(userEditForm.getUserId());
		BeanUtils.copyProperties(editUser, userEditForm);
		return "userEdit";
	}

	// ユーザー情報更新処理
	@PostMapping(value = "/userEdit/update")
	public String userDetailEdit(@Validated @ModelAttribute UserEditForm userEditForm, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
            return "userEdit";
        } else {
            userService.editUser(userEditForm);
            return "redirect:/userList";
        }
	}
	// ユーザー情報削除
    @PostMapping(value = "/userDelete")
    public String deleteUser(@ModelAttribute UserEditForm userEditForm, BindingResult bindingResult) {
        userService.deleteUser(userEditForm.getUserId());
        return "redirect:/userList";
    }
    
    @GetMapping("/userListTest")
	public String testGet(@RequestParam("test") String test, Model model) {
		model.addAttribute("test", "aa");
		return "test";
	}
    
    @PostMapping("/userListTest")
	public String testPost(@RequestParam("test") String test, Model model) {
		model.addAttribute("test", test);
		return "test";
	}
    
}
