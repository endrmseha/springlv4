package com.sparta.springlv4.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;




@Setter
@Getter
public class SignupRequestDto {
    // 회원가입 요청에서 필요한 정보를 담는 DTO

    // username과 password의 조건을 넣어줌
    // DTO에서 조건을 설정해주는 것이 일반적으로 사용됨
    @NotBlank
    @Size(min = 4, max = 10, message = "4자에서 10자")
    @Pattern(regexp = "^[a-z0-9]*$",message = "알파벳 소문자(a~z), 숫자(0~9)")
    private String username;


    @NotBlank
    @Size(min = 8, max = 15 , message = "8자에서 15자")
    @Pattern(regexp = "^[a-zA-Z0-9]*$",message = "알파벳 소문자(a~z),알파벳 대문자(A~Z), 숫자(0~9)")
    private String password;
}
