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
    private Double centerX;

    @Schema(description = "Y좌표", example = "-0.2")
    @NotBlank
    private Double centerY;

    @Schema(description = "반지름", example = "17")
    @NotBlank
    private Double radius;

    @Schema(description = "색상", example = "#00FF00")
    @NotBlank
    private String color;

}
