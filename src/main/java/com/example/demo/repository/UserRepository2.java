package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User2;

@Repository
public interface UserRepository2 extends JpaRepository<User2, String> {
	
	List<User2> findByActive(Integer active);
	Page<User2> findByActive(Integer active, Pageable pageable);
	Page<User2> findAll(Pageable pageable);
}
