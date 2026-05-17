package com.thinkfree.thinkchart.circle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 수정 요청")
public class UpdateCircleRequest {

    @Schema(description = "사용자 ID", example = "5a9610ef-4530-8e7f-2958-9c376833b038")
    private String userId;

    @Schema(description = "X좌표", example = "10.2")
    private Double x;

    @Schema(description = "Y좌표", example = "5.8")
    private Double y;

    @Schema(description = "값", example = "95")
    @Positive
    private Long value;

    @Schema(description = "색상", example = "#000000")
    @Pattern(
            regexp = "^#[0-9a-fA-F]{6}$",
            message = "올바른 색상 코드 형식이 아닙니다. (예: #000000)"
    )
    private String color;

    @Schema(description = "투명도", example = "0.9")
    @Positive
    private Double opacity;
}
