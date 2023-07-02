package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserEditForm implements Serializable{
	@NotBlank(message = "社員番号が入力されていません")
    private String userId; // 社員番号

    @NotBlank(message = "氏名が入力されていません")
    private String userName; // 氏名

    private String mailAddress; // メールアドレス

    private String phoneNumber; // 電話番号

}
