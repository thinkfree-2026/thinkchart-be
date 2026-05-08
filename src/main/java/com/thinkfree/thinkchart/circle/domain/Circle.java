package com.thinkfree.thinkchart.circle.domain;

import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Document(collection = "circles")
public class Circle {

    @Id
    private String id;
    private String chartId;
    private double x;
    private double y;
    private double radius;
    @Builder.Default
    private String color = "#00a0ac";
    @CreatedDate
    private Long createdAt;

    public void updateChartId(String chartId) {
        if (isUsedForChart()) {
            throw new GlobalException(ErrorCode.ALREADY_USED_CIRCLE);
        }

        this.chartId = chartId;
    }

    public boolean isUsedForChart() {
        return this.chartId != null;
    }

    public boolean updateX(Double x) {
        if (Objects.equals(this.x, x)) {
            return false;
        }
        this.x = x;
        return true;
    }

    public boolean updateY(Double y) {
        if (Objects.equals(this.y, y)) {
            return false;
        }
        this.y = y;
        return true;
    }

    public boolean updateRadius(Double radius) {
        if (Objects.equals(this.radius, radius)) {
            return false;
        }
        this.radius = radius;
        return true;
    }

    public boolean updateColor(String color) {
        if (Objects.equals(this.color, color)) {
            return false;
        }
        this.color = color;
        return true;
    }
}
