package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	/**
	* 部署リスト取得
	*
	* @return 部署リスト
	*/
	@Transactional
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
}
