package com.springboot.hello01.controller;

import com.springboot.hello01.dao.UserDao;
import com.springboot.hello01.domain.User;
import com.springboot.hello01.domain.UserRequestDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController               // 해당 controller를 controller로 사용하기 위해 등록
@RequestMapping("/api/v1") // 접속 시 url 설정
@Slf4j                       // logback 사용하여 로그남기기
public class UserController {

    // final(X)
    // Autowired는 의존성 주입
    // 생성자를 만들어서 주입받는 방법이 아닌 @Autowired 사용
    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/users")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto userRequestDto) {
        User user = new User(userRequestDto.getId(),
                            userRequestDto.getName(),
                            userRequestDto.getPassword());
        log.info("user가 add 되었습니다.");
        return ResponseEntity.ok().body(userDao.add(user));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Integer> deleteAll() {
        log.info("모든 user가 delete 되었습니다.");
        return ResponseEntity.ok().body(userDao.deleteAll());
    }

    @GetMapping("/{id}") // findById는 결과값을 저장해서 그걸 가져와야하기 때문에
    public ResponseEntity<User> findById(@PathVariable String id) {
        log.info("user를 찾았습니다.");
        return ResponseEntity.ok().body(this.userDao.findById(id));
    }

}
