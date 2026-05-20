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
    private String userId;
    @Builder.Default
    private String name = "";
    private double x;
    private double y;
    private long value;
    @Builder.Default
    private String color = "#c7d2fe";
    @Builder.Default
    private double opacity = 1.0;
    @CreatedDate
    private Long createdAt;

    public void releaseChart() {
        this.chartId = null;
        this.name = "";
    }

    public void updateChartId(String chartId) {
        if (isUsedForChart()) {
            throw new GlobalException(ErrorCode.ALREADY_USED_CIRCLE);
        }

        this.chartId = chartId;
    }

    public boolean isUsedForChart() {
        return this.chartId != null;
    }

    public boolean updateUserId(String userId) {
        if (Objects.equals(this.userId, userId)) {
            return false;
        }
        this.userId = userId;
        return true;
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

    public boolean updateValue(Long value) {
        if (Objects.equals(this.value, value)) {
            return false;
        }
        this.value = value;
        return true;
    }

    public boolean updateColor(String color) {
        if (Objects.equals(this.color, color)) {
            return false;
        }
        this.color = color;
        return true;
    }

    public boolean updateOpacity(Double opacity) {
        if (Objects.equals(this.opacity, opacity)) {
            return false;
        }
        this.opacity = opacity;
        return true;
    }

    public boolean updateName(String name) {
        if (Objects.equals(this.name, name)) {
            return false;
        }
        this.name = name;
        return true;
    }
}
