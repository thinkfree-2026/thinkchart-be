package com.thinkfree.thinkchart.chart.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Document(collection = "charts")
public class Chart {

    @Id
    private String id;
    @Builder.Default
    private List<String> circleIds = new ArrayList<>();
    @Builder.Default
    private String name = "THINK-CHART";
    @Builder.Default
    private String xAxis = "X축";
    @Builder.Default
    private String yAxis = "Y축";
    @CreatedDate
    private Long createdAt;

    public boolean updateName(String name) {
        if (Objects.equals(this.name, name)) {
            return false;
        }
        this.name = name;
        return true;
    }

    public boolean updateXAxis(String xAxis) {
        if (Objects.equals(this.xAxis, xAxis)) {
            return false;
        }
        this.xAxis = xAxis;
        return true;
    }

    public boolean updateYAxis(String yAxis) {
        if (Objects.equals(this.yAxis, yAxis)) {
            return false;
        }
        this.yAxis = yAxis;
        return true;
    }
}
