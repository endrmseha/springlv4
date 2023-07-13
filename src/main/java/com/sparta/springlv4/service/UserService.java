package com.sparta.springlv4.service;

import com.sparta.springlv4.dto.LoginRequestDto;
import com.sparta.springlv4.dto.SignupRequestDto;
import com.sparta.springlv4.entity.User;
import com.sparta.springlv4.jwt.JwtUtil;
import com.sparta.springlv4.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public User signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // JWT Token 생성 및 반환
//        return jwtUtil.createToken(user.getUsername());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
//        Cookie cookie = new Cookie(jwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername())); // Name-Value
//        cookie.setPath("/");

        // Response 객체에 Cookie 추가
//        response.addCookie(cookie);


        return username;
    }
}
