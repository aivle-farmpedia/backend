package com.farm.pedia.user.mapper;

import com.farm.pedia.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(String uuid);

    Optional<User> findByUuid(String uuid);
}