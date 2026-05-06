package com.thinkfree.thinkchart.circle.domain;

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
    private double diameter;

    @Builder.Default
    private double red = 0.111;
    @Builder.Default
    private double green = 0.222;
    @Builder.Default
    private double blue = 0.333;
    @Builder.Default
    private double opacity = 0.9;

    @CreatedDate
    private Long createdAt;

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

    public boolean updateDiameter(Double diameter) {
        if (Objects.equals(this.diameter, diameter)) {
            return false;
        }
        this.diameter = diameter;
        return true;
    }

    public boolean updateRed(Double red) {
        if (Objects.equals(this.red, red)) {
            return false;
        }
        this.red = red;
        return true;
    }

    public boolean updateGreen(Double green) {
        if (Objects.equals(this.green, green)) {
            return false;
        }
        this.green = green;
        return true;
    }

    public boolean updateBlue(Double blue) {
        if (Objects.equals(this.blue, blue)) {
            return false;
        }
        this.blue = blue;
        return true;
    }

    public boolean updateOpacity(Double opacity) {
        if (Objects.equals(this.opacity, opacity)) {
            return false;
        }
        this.opacity = opacity;
        return true;
    }
}
