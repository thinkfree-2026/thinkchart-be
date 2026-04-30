package com.thinkfree.thinkchart.circle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 수정 요청")
@ToString
public class UpdateCircleRequest {

    @Schema(description = "X좌표", example = "10.2")
    private Double x;

    @Schema(description = "Y좌표", example = "5.8")
    private Double y;

    @Schema(description = "지름", example = "9.5")
    @Positive
    private Double diameter;

    @Schema(description = "Red 컬러", example = "0.111")
    private Double red;

    @Schema(description = "Green 컬러", example = "0.222")
    private Double green;

    @Schema(description = "Blue 컬러", example = "0.333")
    private Double blue;

    @Schema(description = "투명도", example = "0.9")
    @Positive
    private Double opacity;
}
