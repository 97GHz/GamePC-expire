package com.gamepc.GamePC.controller;

import com.gamepc.GamePC.domain.user.Role;
import com.gamepc.GamePC.domain.user.User;
import com.gamepc.GamePC.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = userService.findAllUser();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.findUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        System.out.println(user.toString());
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }

    // TODO : Transaction Error 시 예외 처리 방법 작성
    // TODO : 나중에 이 코드는 지울 것(POST로 쓸거 GET으로 쓰면 안됨)
    @GetMapping("/save")
    public ResponseEntity<User> saveUserTest() {
        User user = User.builder()
                .email("tmdduq525@naver.com")
                .name("한승엽")
                .role(Role.ADMIN).build();

        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
