package com.example.demo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "mail_address")
    private String mailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "is_active")
    private int activeFlg;

    @Column(name = "registration_date_time")
    private Timestamp registrationDateTime;

    @Column(name = "update_date_time")
    private Timestamp updateDateTime;

    private String territory;

    private BigDecimal sales;

    @Column(name = "customer_count")
    private int customerCount;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
