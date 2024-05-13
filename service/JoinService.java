package com.example.springjwt.service;


import com.example.springjwt.dto.JoinDTO;
import com.example.springjwt.entitiy.UserEntity;
import com.example.springjwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //void-> String으로 문자 반환(회원가입)
    public String joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        //추가
        String profilePictureUrl = joinDTO.getProfilePictureUrl();
        String nickname =  joinDTO.getNickname();



        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {

            return "이미 존재하는 아이디입니다.";
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_ADMIN");
        //nickname과 porfile추가
        data.setNickname(nickname);


        userRepository.save(data);
        return "회원가입 되었습니다.";
    }
}
