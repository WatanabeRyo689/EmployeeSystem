package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserSkillLevel;
import com.example.demo.entity.UserSkillLevelId;

public interface UserSkillLevelRepository extends JpaRepository<UserSkillLevel, UserSkillLevelId> {
	List<UserSkillLevel> findByUserId(String userId);
}
