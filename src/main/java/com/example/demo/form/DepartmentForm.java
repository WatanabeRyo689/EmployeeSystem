package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepartmentForm implements Serializable {

	private int depId;

	private String depName;
	
	private Integer totalSales;
	
	private Integer countUsersByDepId;
	
	private Integer averageSales;
}
