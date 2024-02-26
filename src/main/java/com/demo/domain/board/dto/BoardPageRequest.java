package com.demo.domain.board.dto;

import com.demo.global.dto.CommonPageRequestDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Schema(description = "페이징 요청 정보")
public class BoardPageRequest extends CommonPageRequestDTO {

    @Schema(description = "검색어", example = " ")
    private String search;

    @Schema(description = "순서 정렬", example = "id desc")
    private String orderBy;

}
