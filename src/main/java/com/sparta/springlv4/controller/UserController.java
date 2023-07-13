package com.sparta.springlv4.controller;

import com.sparta.springlv4.dto.ApiResult;
import com.sparta.springlv4.dto.LoginRequestDto;
import com.sparta.springlv4.dto.SignupRequestDto;
import com.sparta.springlv4.entity.User;
import com.sparta.springlv4.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public ApiResult signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        User user = userService.signup(signupRequestDto);
        return new ApiResult("회원가입 성공", HttpStatus.OK.value()); // 회원가입 성공시 ApiResult Dto를 사용하여 성공메세지와 starusCode를 띄움
    }

    // 로그인 API
    @ResponseBody
    @PostMapping("/login")
    public ApiResult login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String token = userService.login(loginRequestDto, response);
        return new ApiResult("로그인 성공", HttpStatus.OK.value()); // 로그인 성공시 ApiREsult Dto를 사용하여 성공메세지와 statusCode를 띄움
    }
}
