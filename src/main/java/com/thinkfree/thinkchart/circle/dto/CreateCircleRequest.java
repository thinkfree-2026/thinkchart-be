package com.thinkfree.thinkchart.circle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 생성 요청")
public class CreateCircleRequest {

    @Schema(description = "X좌표", example = "0.8")
    @NotBlank
    private double x;

    @Schema(description = "Y좌표", example = "-0.2")
    @NotBlank
    private double y;

    @Schema(description = "지름", example = "17")
    @NotBlank
    private double size;

    @Schema(description = "색상 r", example = "0.28298955543590565")
    @NotBlank
    private double r;

    @Schema(description = "색상 g", example = "0.5171325916737295")
    @NotBlank
    private double g;

    @Schema(description = "색상 b", example = "0.13165857375876888")
    @NotBlank
    private double b;

    @Schema(description = "투명도", example = "1")
    @NotBlank
    private double a;

}
