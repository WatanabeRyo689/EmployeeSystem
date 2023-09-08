package com.example.demo.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.example.demo.entity.Department;

import lombok.Data;

@Data
public class UserForm implements Serializable {
	private String userId;

	private String password;

	private String userName;

	private String mailAddress;

	private String phoneNumber;

	private Date joinDate;

	private int activeFlg;

	private Timestamp registrationDateTime;

	private Timestamp updateDateTime;

	private String territory;

	private BigDecimal sales;

	private int customerCount;

	private Department department;

}
