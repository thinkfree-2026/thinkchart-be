package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BarResponse {
    private String circleId;
    private String chartId;
    private double value;
    private String color;

    public static BarResponse from(String chartId, String circleId, Circle circle, double ratio) {
        return new BarResponse(chartId, circleId, circle.getRadius(), circle.getColor());
    }

}
