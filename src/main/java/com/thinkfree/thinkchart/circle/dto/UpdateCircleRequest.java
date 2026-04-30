package com.thinkfree.thinkchart.circle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Double x;

    @Schema(description = "Y좌표", example = "5.8")
    @NotNull
    private Double y;

    @Schema(description = "지름", example = "9.5")
    @NotNull
    private Double diameter;

    @Schema(description = "Red 컬러", example = "0.111")
    @NotNull
    private Double red;

    @Schema(description = "Green 컬러", example = "0.222")
    @NotNull
    private Double green;

    @Schema(description = "Blue 컬러", example = "0.333")
    @NotNull
    private Double blue;

    @Schema(description = "투명도", example = "0.9")
    @NotNull
    private Double opacity;
}
