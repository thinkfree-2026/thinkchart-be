package com.thinkfree.thinkchart.circle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 다중 이동 요청")
public class MoveCirclesRequest {

    @Schema(description = "원 ID", example = "6a0bf89e693d4ba819ef8ba4")
    @NotBlank
    private String id;

    @Schema(description = "X좌표", example = "10.2")
    private Double x;

    @Schema(description = "Y좌표", example = "5.8")
    private Double y;
}