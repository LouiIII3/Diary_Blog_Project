package com.example.springjwt.service;

import com.example.springjwt.dto.PostDTO;
import com.example.springjwt.entitiy.PostEntity;
import com.example.springjwt.entitiy.UserEntity;
import com.example.springjwt.repository.PostRepository;
import com.example.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }




    public List<PostEntity> getAllPostsByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return postRepository.findAllByUser(userEntity);
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    public void createPost(PostDTO postDTO, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            PostEntity postEntity = new PostEntity();
            postEntity.setTitle(postDTO.getTitle());
            postEntity.setContent(postDTO.getContent());
            postEntity.setUser(userEntity);
            postEntity.setDate(postDTO.getDate()); // 날짜 
            postEntity.setDayOfWeek(postDTO.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault())); // 요일 
            postEntity.setWeather(postDTO.getWeather()); // 날씨 

            postRepository.save(postEntity);

        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }


    public void deletePostByIdAndUser(int postId, String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            PostEntity postEntity = postRepository.findById(postId).orElse(null);
            if (postEntity != null && postEntity.getUser().getId() == userEntity.getId()) {
                postRepository.delete(postEntity);
            } else {
                throw new IllegalArgumentException("Post not found or not owned by user!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }

    public void updatePostByIdAndUser(int postId, String username, PostDTO postDTO) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            PostEntity postEntity = postRepository.findById(postId).orElse(null);
            if (postEntity != null && postEntity.getUser().getId() == userEntity.getId()) {
                postEntity.setTitle(postDTO.getTitle());
                postEntity.setContent(postDTO.getContent());
                postRepository.save(postEntity);
            } else {
                throw new IllegalArgumentException("Post not found or not owned by user!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }
}
