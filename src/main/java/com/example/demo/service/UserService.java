package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;



@Service
public class UserService {

	public final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	    * 編集ユーザー取得
	    *
	    * @param userId
	    * @return 編集ユーザー
	    */
	public User getUser(String userId) {
		return userRepository.getById(userId);
	}

	/**
	 * ユーザーリスト取得
	 *
	 * @return ユーザーリスト
	 */
	public List<User> getUserList() {
		return userRepository.getUserList();
	}
	
	/**
	 * ユーザー登録
	 *
	 * @return ユーザーリスト
	 */
	public User createUser(UserForm userForm) {
		User user = new User();

        // ユーザー情報初期設定
        user.setUserId(userForm.getUserId());
        user.setUserName(userForm.getUserName());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setMailAddress(userForm.getMailAddress());
        user.setJoinDate(userForm.getJoinCompanyDate());
        user.setActiveFlg(true);
   
        user.setRegistrationDateTime(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
		
	}
}
