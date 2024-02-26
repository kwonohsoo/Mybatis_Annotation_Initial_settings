package com.demo.domain.board.dto;

import com.demo.global.entity.common.CommonInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Schema(description = "게시판 응답 정보")
public class BoardResponse extends CommonInfo {

    @Schema(description = "게시판 번호", example = "1")
    private Long id;

    @Schema(description = "제목", example = "제목")
    private String title;

    @Schema(description = "내용", example = "내용")
    private String content;

    @Schema(description = "생성일", example = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime createdDt;

    @Schema(description = "수정일", example = "YYYY-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDt;

    @Builder
    public BoardResponse(Long id, String title, String content, LocalDateTime createdDt, LocalDateTime modifiedDt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDt = createdDt;
        this.modifiedDt = modifiedDt;
    }
}
