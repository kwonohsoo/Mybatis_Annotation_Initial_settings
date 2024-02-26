package com.demo.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "페이지 응답 정보")
public abstract class CommonPageResponseDTO {

    @Schema(description = "전체 페이지 수")
    private int totalPage;

    @Schema(description = "전체 데이터 수")
    private Long totalCount;

    @Schema(description = "페이지 번호")
    private int pageNumber;

    @Schema(description = "페이지 사이즈")
    private int pageSize;

    protected CommonPageResponseDTO(int totalPage, Long totalCount, int pageNumber, int pageSize) {
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}