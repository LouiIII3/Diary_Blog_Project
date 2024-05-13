package com.example.springjwt.repository;


import com.example.springjwt.entitiy.PostEntity;
import com.example.springjwt.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findAllByUser(UserEntity user);
}
