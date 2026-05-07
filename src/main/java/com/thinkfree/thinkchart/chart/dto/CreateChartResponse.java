package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.chart.domain.Chart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateChartResponse {
    private String id;
    private String name;
    private List<String> circleIds;
    private Long createdAt;

    public static CreateChartResponse from(Chart chart) {
        return new CreateChartResponse(
                chart.getId(),
                chart.getName(),
                chart.getCircleIds(),
                chart.getCreatedAt()
        );
    }
}
