package com.thinkfree.thinkchart.circle.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.*;

@Getter
@AllArgsConstructor
public class CircleResponse {

    private String id;
    private String chartId;
    private double x;
    private double y;
    private double radius;
    private String color;
    private Long createdAt;

    public static CircleResponse from(Circle circle) {
        return new CircleResponse(
                circle.getId(),
                circle.getChartId(),
                circle.getX(),
                circle.getY(),
                circle.getRadius(),
                circle.getColor(),
                circle.getCreatedAt()
        );
    }
}
