package com.thinkfree.thinkchart.chart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "차트 수정 요청")
public class UpdateChartRequest {

    @Schema(description = "차트 이름", example = "씽크차트")
    private String name;

    @Schema(description = "X축 이름", example = "팀명")
    @JsonProperty
    private String xAxis;

    @Schema(description = "Y축 이름", example = "인원수")
    @JsonProperty
    private String yAxis;
}
