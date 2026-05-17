package com.thinkfree.thinkchart.circle.dto;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCircleResponse {

    private String id;
    private String clientCircleId;
    private String userId;
    private String chartId;
    private String name;
    private double x;
    private double y;
    private long value;
    private double opacity;
    private String color;
    private Long createdAt;

    public static CreateCircleResponse from(Circle circle, String clientCircleId) {
        return new CreateCircleResponse(
                circle.getId(),
                clientCircleId,
                circle.getUserId(),
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
