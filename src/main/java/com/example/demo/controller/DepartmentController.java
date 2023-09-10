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
			
			// 合計金額取得
			Integer totalSales = departmentService.totalSales(department.getDepId());
			departmentForm.setTotalSales(totalSales);
			
			// 合計人数
			Integer countUsersByDepartmentId = departmentService.countUsersByDepId(department.getDepId());
			departmentForm.setCountUsersByDepId(countUsersByDepartmentId);
			
			// 一人当たりの売上平均値
			Integer averageSales = totalSales/countUsersByDepartmentId;
			departmentForm.setAverageSales(averageSales);
			
			departmentFormList.add(departmentForm);
		}

		model.addAttribute("departmentFormList", departmentFormList);
		return "department";
	}
}
