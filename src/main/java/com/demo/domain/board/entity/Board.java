package com.demo.domain.board.entity;

import com.demo.global.entity.common.CommonInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board extends CommonInfo {

    private Long id;

    private String title;

    private String content;
}
