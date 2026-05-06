package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChartResponse {
    private String id;
    private String name;
    private String xAxisName;
    private String yAxisName;
    private List<Circle> circles;
    private Long createdAt;

    public static ChartResponse from(Chart chart) {
        return new ChartResponse(
                chart.getId(),
                chart.getName(),
                chart.getXAxisName(),
                chart.getYAxisName(),
                chart.getCircles(),
                chart.getCreatedAt()
        );
    }
}
