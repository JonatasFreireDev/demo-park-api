package com.jonatas.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonatas.demo_park_api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
