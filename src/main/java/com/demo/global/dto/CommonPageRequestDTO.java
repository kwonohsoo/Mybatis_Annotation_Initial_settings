package com.demo.global.dto;

import com.demo.global.message.CommonMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "페이지 요청 정보")
public abstract class CommonPageRequestDTO {

    @Min(value = 1)
    @Schema(description = "페이지 번호", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private int pageNum;

    @Range(min = 1)
    @Schema(description = "페이지에 보여줄 content 개수", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    private int pageSize;

    @NotNull(message = CommonMessage.VALID_NOT_NULL_DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Schema(description = "시작 날짜", example = "2022-01-01")
    private LocalDate startDate;

    @NotNull(message = CommonMessage.VALID_NOT_NULL_DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Schema(description = "종료 날짜", example = "2024-12-31")
    private LocalDate endDate;
}
