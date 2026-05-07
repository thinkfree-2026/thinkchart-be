package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.chart.domain.Chart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChartResponse {
    private String id;
    private String name;
    private String xAxis;
    private String yAxis;
    private List<String> circleIds;
    private Long createdAt;

    public static ChartResponse from(Chart chart) {
        return new ChartResponse(
                chart.getId(),
                chart.getName(),
                chart.getXAxisName(),
                chart.getYAxisName(),
                chart.getCircleIds(),
                chart.getCreatedAt()
        );
    }
}
