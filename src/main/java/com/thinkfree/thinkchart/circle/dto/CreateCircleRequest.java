package com.thinkfree.thinkchart.circle.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 생성 요청")
public class CreateCircleRequest {

    @Schema(description = "state매핑용 임시ID", example = "9c376833b038")
    @NotBlank
    private String clientCircleId;

    @Schema(description = "X좌표", example = "0.8")
    @NotNull
    private Double x;

    @Schema(description = "Y좌표", example = "-0.2")
    @NotNull
    private Double y;

    @Schema(description = "값", example = "175")
    @NotNull
    @Positive
    private Long value;

    @Schema(description = "사용자 ID", example = "5a9610ef-4530-8e7f-2958-9c376833b038")
    @NotBlank
    private String userId;

    public Circle toEntity() {
        return Circle.builder()
                .userId(this.userId)
                .x(this.x)
                .y(this.y)
                .value(this.value)
                .build();
    }
}
