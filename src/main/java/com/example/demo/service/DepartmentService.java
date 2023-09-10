package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	/**
	* 部署リスト取得
	*
	* @return 部署リスト
	*/
	@Transactional
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}
	
	/**
	* 部署ごとの合計売上取得
	*
	* @param departmentId
	* @return 部署リスト
	*/
	@Transactional
	public Integer totalSales(int departmentId) {
		return  userRepository.totalSales(departmentId);
	}
	
	/**
	* 部署ごとの合計人数取得
	*
	* @param departmentId
	* @return 
	*/
	@Transactional
	public Integer countUsersByDepId(int departmentId) {
		return  userRepository.countUsersByDepId(departmentId);
	}
	
}
