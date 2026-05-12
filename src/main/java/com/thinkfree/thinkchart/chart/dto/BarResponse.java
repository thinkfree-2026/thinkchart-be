package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarResponse {
    private String circleId;
    private String chartId;
    private String name;
    private double value;
    private String color;
    private double opacity;

    public static BarResponse from(String chartId, String circleId, Circle circle) {
        return new BarResponse(chartId, circleId, circle.getName(), circle.getValue(), circle.getColor(), circle.getOpacity());
    }

}
