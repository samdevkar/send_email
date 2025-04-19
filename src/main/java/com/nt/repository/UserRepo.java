package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
	List<UserEntity>findAll();

}
