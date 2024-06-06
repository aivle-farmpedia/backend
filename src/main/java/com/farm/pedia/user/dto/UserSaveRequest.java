package com.farm.pedia.user.dto;

import lombok.Getter;

import java.util.UUID;


@Getter
public class UserSaveRequest {
    private UUID uuid;

    public String getUuid() {
        return uuid.toString();
    }
}
