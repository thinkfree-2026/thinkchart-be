package com.thinkfree.thinkchart.circle.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "원 생성 요청")
public class CreateCircleRequest {

    @Schema(description = "X좌표", example = "0.8")
    @NotNull
    private Double x;

    @Schema(description = "Y좌표", example = "-0.2")
    @NotNull
    private Double y;

    @Schema(description = "지름", example = "17.5")
    @NotNull
    @Positive
    private Double diameter;


    public Circle toEntity() {
        return Circle.builder()
                .x(this.x)
                .y(this.y)
                .diameter(this.diameter)
                .build();
    }
}
