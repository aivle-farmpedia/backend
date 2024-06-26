package com.farm.pedia.auth.dto.request;

import lombok.Getter;

import java.util.UUID;


@Getter
public class UserSaveRequest {
    private UUID uuid;

    public String getUuid() {
        return uuid.toString();
    }
}
