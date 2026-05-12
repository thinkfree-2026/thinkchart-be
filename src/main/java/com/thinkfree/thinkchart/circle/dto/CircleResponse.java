package com.thinkfree.thinkchart.circle.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.*;

@Getter
@AllArgsConstructor
public class CircleResponse {

    private String id;
    private String chartId;
    private String name;
    private double x;
    private double y;
    private long value;
    private double opacity;
    private String color;
    private Long createdAt;

    public static CircleResponse from(Circle circle) {
        return new CircleResponse(
                circle.getId(),
                circle.getChartId(),
                circle.getName(),
                circle.getX(),
                circle.getY(),
                circle.getValue(),
                circle.getOpacity(),
                circle.getColor(),
                circle.getCreatedAt()
        );
    }
}
