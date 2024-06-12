package com.farm.pedia.user.service;

import com.farm.pedia.user.domain.User;
import com.farm.pedia.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // User - UUID 저장
    public void save(String uuid) {
        userMapper.save(uuid);
    }

    // User - UUID 조회
    public User findByUuid(String uuid) {
        return userMapper.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}