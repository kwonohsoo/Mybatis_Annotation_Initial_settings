package com.demo.global.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public abstract class CommonInfo {
    private int useYn;
    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;

    public LocalDateTime getCreatedDt() {
        return (createdDt != null) ? createdDt : LocalDateTime.now();
    }
}