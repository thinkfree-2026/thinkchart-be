package com.thinkfree.thinkchart.chart.dto;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.dto.CircleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ChartDetailResponse {

    private String chartId;
    private List<CircleResponse> circles;

    public static ChartDetailResponse from(Chart chart, List<Circle> circles) {
        List<CircleResponse> responses = circles.stream()
                .map(circle -> CircleResponse.from(circle))
                .toList();

        return new ChartDetailResponse(chart.getId(), responses);
    }

}
