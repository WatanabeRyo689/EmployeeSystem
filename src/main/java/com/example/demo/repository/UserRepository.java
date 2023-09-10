package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * FROM user u ORDER BY u.user_id", nativeQuery = true)
	public List<User> getUserList();

	// 部署ごとのユーザー一覧を取得するクエリを定義
	@Query(value = "SELECT * FROM user u WHERE u.department_id = :departmentId", nativeQuery = true)
	List<User> findUsersByDepartmentId(int departmentId);

	@Modifying // 更新操作を行うことを宣言
	@Query(value = "UPDATE User u SET u.is_active = :active WHERE u.user_id = :userId", nativeQuery = true)
	public void updateUserActive(String userId, int active);
	
	// 合計金額取得
	@Query(value = "SELECT SUM(u.sales) FROM User u WHERE u.department_id = :departmentId", nativeQuery = true)
	public Integer totalSales(int departmentId);
	
	// 合計人数
	@Query(value = "SELECT COUNT(*) FROM User u WHERE u.department_Id = :departmentId", nativeQuery = true)
	public Integer countUsersByDepId(int departmentId);
}
