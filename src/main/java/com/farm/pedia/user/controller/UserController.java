package com.farm.pedia.user.controller;

import com.farm.pedia.user.domain.User;
import com.farm.pedia.user.dto.UserSaveRequest;
import com.farm.pedia.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 앱 최초 실행 시 UUID 저장
    @PostMapping("/save")
    public ResponseEntity<Void> saveUser(@RequestBody UserSaveRequest userSaveRequest) {
        userService.save(userSaveRequest.getUuid());
        return ResponseEntity.ok().build();
    }

    // UUID로 사용자 조회
    @PostMapping("/find")
    public ResponseEntity<User> findUser(@RequestParam("uuid") String uuid) {
        User user = userService.findByUuid(uuid);
        return ResponseEntity.ok(user);
    }
}
