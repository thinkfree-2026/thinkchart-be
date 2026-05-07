package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.circle.domain.Circle;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "차트 생성 요청")
public class CreateChartRequest {

    @NotEmpty
    private List<String> circleIds;

    public Chart toEntity() {
        return Chart.builder()
                .circleIds(this.circleIds)
                .build();

    }
}
