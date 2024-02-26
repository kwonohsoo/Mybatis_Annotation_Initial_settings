package com.demo.domain.board.model;

import com.demo.global.entity.common.CommonInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 관리 정보")
public class BoardPageData extends CommonInfo {

    @Schema(description = "게시글 번호")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;
}
