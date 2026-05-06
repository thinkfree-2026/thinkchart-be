package com.thinkfree.thinkchart.chart.service;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.chart.dto.CreateChartRequest;
import com.thinkfree.thinkchart.chart.repository.ChartRepository;
import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.repository.CircleRepository;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final CircleRepository circleRepository;
    private final ChartRepository chartRepository;

    public void createChart(CreateChartRequest request) {
        List<Circle> circles = circleRepository.findAllById(request.getCircleIds());

        if (circles.size() != request.getCircleIds().size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        Chart chart = request.toEntity(circles);
        Chart savedChart = chartRepository.save(chart);
    }
}
