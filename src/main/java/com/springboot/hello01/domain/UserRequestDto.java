package com.springboot.hello01.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 롬복 기능
@Getter
public class UserRequestDto {
    private String id;
    private String name;
    private String password;
}
