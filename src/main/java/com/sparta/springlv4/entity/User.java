package com.sparta.springlv4.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique = true -> 유일성 조건 설정
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // user 객체 생성시 필요한 정보 = username, password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
