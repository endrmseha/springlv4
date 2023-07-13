package com.sparta.springlv4.service;

import com.sparta.springlv4.dto.LoginRequestDto;
import com.sparta.springlv4.dto.SignupRequestDto;
import com.sparta.springlv4.dto.Status;
import com.sparta.springlv4.entity.User;
import com.sparta.springlv4.entity.UserRoleEnum;
import com.sparta.springlv4.jwt.JwtUtil;
import com.sparta.springlv4.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final String ADMIN_TOKEN = "ENDRMSEHA";

    @Transactional
    public Status signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        // 관리자 체크 (admin=true 일시 수행)
        if(signupRequestDto.isAdmin()){
            if(!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)){
                return new Status("관리자 번호가 틀렸습니다", 400);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
        return new Status("회원가입에 성공하였습니다.", 200);
    }



    @Transactional(readOnly = true)
    public Status login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(!user.getPassword().equals(password)) {
            return new Status("비밀번호가 일치하지 않습니다.", 400);
        }
        // JWT Token 생성 및 반환
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));


        return new Status("로그인에 성공하였습니다.", 200);
    }
}
