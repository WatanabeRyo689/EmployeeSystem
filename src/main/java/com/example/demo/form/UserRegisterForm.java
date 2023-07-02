package com.example.demo.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserRegisterForm implements Serializable{
	@NotBlank(message = "社員番号が入力されていません")
    private String userId; // 社員番号
	
	@NotBlank(message = "パスワードが入力されていません")
    private String password; // 社員番号

    @NotBlank(message = "氏名が入力されていません")
    private String userName; // 氏名

    private String mailAddress; // メールアドレス

    private String phoneNumber; // 電話番号

    @NotNull(message = "入社日が入力されていません")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinCompanyDate; // 入社日

}
