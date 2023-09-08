package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.entity.User2;
import com.example.demo.form.UserEditForm;
import com.example.demo.form.UserForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.repository.UserRepository2;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository2 userRepository2;

	@GetMapping("/userList")
	public String userList(UserForm userForm, Model model) {

		List<User> userList = userService.getUserList();

		List<UserForm> userFormList = getUserFormByUser(userList);

		model.addAttribute("userFormList", userFormList);
		return "userList";
	}

	// ユーザー情報登録画面表示
	@GetMapping("/userRegister")
	public String userRegister(UserRegisterForm userRegisterForm, Model model) {
		return "userRegister";
	}

	// ユーザー情報登録画面表示
	@PostMapping("/userRegister/insert")
	public String userRegister(@ModelAttribute @Validated UserRegisterForm userRegisterForm, BindingResult result,
			Model model) {
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

	// ユーザー情報物理削除
	@PostMapping(value = "/userDeletePhysical")
	public String userDeletePhysical(@RequestParam(name = "userId") String userId, BindingResult bindingResult) {
		userService.userDeletePhysical(userId);
		return "redirect:/userList";
	}

	@GetMapping("/users")
	public String listUsers(Model model, @RequestParam(name = "active", defaultValue = "2") Integer active,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<User2> users1;
		//List<User2> users = userRepository2.findByActive(active);
		if (active == 2) {
			users1 = userRepository2.findAll(pageable);
		} else {
			users1 = userRepository2.findByActive(active, pageable);
		}

		model.addAttribute("users", users1);
		model.addAttribute("active", active);
		model.addAttribute("size", size);
		return "userListTest";
	}

	@GetMapping("/userListTest")
	public String testGet(@RequestParam("test") String test, Model model) {
		model.addAttribute("test", "aa");
		return "testGet";
	}

	@PostMapping("/userListTest")
	public String testPost(@RequestBody String test, Model model) {
		model.addAttribute("test", test);
		return "test";
	}

	// 課ごとのユーザー情報取得(getの場合)
	@GetMapping("/departmentMembers/{departmentId}")
	public String listDepartmentMembers(@PathVariable int departmentId, @ModelAttribute UserForm userForm,
			Model model) {

		List<User> departmentMembers = userService.findUsersByDepartmentId(departmentId);

		List<UserForm> userFormList = getUserFormByUser(departmentMembers);

		model.addAttribute("userFormList", userFormList);
		return "departmentUsers";
	}

	// ユーザー情報論理削除
	@PostMapping(value = "/userDeleteLogic")
	public String userDeleteLogic(@ModelAttribute UserForm userForm, BindingResult bindingResult) {
		userService.userDeleteLogic(userForm.getUserId());
		return "redirect:/userList" + userForm.getDepartment().getDepId();
	}

	// ユーザー情報をentityからformモデルに置き換え
	private List<UserForm> getUserFormByUser(List<User> userList) {

		List<UserForm> userFormList = new ArrayList<UserForm>();

		for (User user : userList) {
			UserForm userForm = new UserForm();
			BeanUtils.copyProperties(user, userForm);
			userFormList.add(userForm);
		}
		return userFormList;
	}

}
