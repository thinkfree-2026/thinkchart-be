package com.thinkfree.thinkchart.chart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "차트 바(원) 수정 요청")
public class UpdateBarRequest {

    @Schema(description = "값", example = "359.5")
    @Positive
    private Double value;

    @Schema(description = "색상", example = "#000000")
    @Pattern(
            regexp = "^#[0-9a-fA-F]{6}$",
            message = "올바른 색상 코드 형식이 아닙니다. (예: #000000)"
    )
    private String color;

}
