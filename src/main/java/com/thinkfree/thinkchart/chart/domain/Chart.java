package com.thinkfree.thinkchart.chart.domain;

import com.thinkfree.thinkchart.circle.domain.Circle;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
    private String xAxisName = "X축";
    @Builder.Default
    private String yAxisName = "Y축";
    @CreatedDate
    private Long createdAt;
}
