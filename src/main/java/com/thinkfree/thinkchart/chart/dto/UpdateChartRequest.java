package com.thinkfree.thinkchart.chart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "차트 수정 요청")
public class UpdateChartRequest {

    @Schema(description = "차트 이름", example = "씽크차트")
    @Getter
    private String name;

    @Schema(description = "단위", example = "kg")
    @Getter
    private String unit;

    @Schema(description = "X축 이름", example = "팀명")
    private String xAxis;

    @Schema(description = "Y축 이름", example = "인원수")
    private String yAxis;

    @JsonProperty("xAxis")
    public String getxAxis() {
        return xAxis;
    }

    @JsonProperty("yAxis")
    public String getyAxis() {
        return yAxis;
    }
}
