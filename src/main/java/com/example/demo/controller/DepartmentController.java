package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Department;
import com.example.demo.form.DepartmentForm;
import com.example.demo.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/departmentList")
	public String testGet(Model model) {
		List<Department> departmentList = departmentService.findAll();

		List<DepartmentForm> departmentFormList = new ArrayList<DepartmentForm>();

		for (Department department : departmentList) {
			DepartmentForm departmentForm = new DepartmentForm();
			BeanUtils.copyProperties(department, departmentForm);
			departmentFormList.add(departmentForm);
		}

		model.addAttribute("departmentFormList", departmentFormList);
		model.addAttribute("totalSales", "aa");
		model.addAttribute("totalNumberOfPeople", "aa");
		model.addAttribute("averageSales", "aa");
		return "department";
	}
}
