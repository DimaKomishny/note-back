package com.service.api.note.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    FULL("full"),
    DEFAULT("default");

    private final String permission;
}
