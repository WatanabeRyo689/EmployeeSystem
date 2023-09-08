package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepartmentForm implements Serializable {

	private int depId;

	private String depName;
}
