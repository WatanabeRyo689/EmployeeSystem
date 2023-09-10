package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.UserEditForm;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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
		BeanUtils.copyProperties(userForm, user);
		user.setActiveFlg(1);
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

	/**
	    * ユーザー物理削除
	    *
	    * @param userId
	    */
	@Transactional
	public void userDeletePhysical(String userId) {
		User user = userRepository.getById(userId);
		userRepository.delete(user);
	}

	/**
	    * ユーザー論理削除
	    *
	    * @param userId
	    */
	@Transactional
	public void userDeleteLogic(String userId) {
		userRepository.updateUserActive(userId, 2);
	}

	/**
	* 部署ごとのユーザーリスト取得
	*
	* @return ユーザーリスト
	*/
	@Transactional
	public List<User> findUsersByDepartmentId(int departmentId) {
		return userRepository.findUsersByDepartmentId(departmentId);
	}
}
