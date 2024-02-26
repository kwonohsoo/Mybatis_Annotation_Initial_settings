package com.demo.domain.board.dto;

import com.demo.domain.board.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "게시판 요청 정보")
public class BoardRequest {

    @Schema(description = "제목", example = "제목test")
    private String title;

    @Schema(description = "내용", example = "내용teste")
    private String content;

    public Board createBoardEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
