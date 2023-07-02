package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.UserEditForm;
import com.example.demo.form.UserRegisterForm;
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
	@Transactional
	public User getUser(String userId) {
		return userRepository.getById(userId);
	}

	/**
	* ユーザーリスト取得
	*
	* @return ユーザーリスト
	*/
	@Transactional
	public List<User> getUserList() {
		return userRepository.getUserList();
	}
	
	/**
	* ユーザー登録
	*
	* @return ユーザーリスト
	*/
	@Transactional
	public User createUser(UserRegisterForm userForm) {
		User user = new User();

        // ユーザー情報初期設定
        user.setUserId(userForm.getUserId());
        user.setUserName(userForm.getUserName());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setMailAddress(userForm.getMailAddress());
        user.setJoinDate(userForm.getJoinCompanyDate());
        user.setActiveFlg(true);
        user.setRegistrationDateTime(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
	}
	
   /**
    * 編集ユーザー情報セット
    *
    * @param userRegisterForm
    * @return 編集ユーザー情報
    */
	@Transactional
    public User editUser(UserEditForm userForm) {
        User editUser = userRepository.getById(userForm.getUserId());
        editUser.setUserName(userForm.getUserName());
        editUser.setMailAddress(userForm.getMailAddress());
        editUser.setPhoneNumber(userForm.getPhoneNumber());
        editUser.setUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(editUser);
    }
    
    @Transactional
    public void deleteUser(String userId) {
        User user = userRepository.getById(userId);
        userRepository.delete(user);
    }
}
