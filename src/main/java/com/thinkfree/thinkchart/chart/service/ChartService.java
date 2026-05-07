package com.thinkfree.thinkchart.chart.service;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.chart.dto.ChartResponse;
import com.thinkfree.thinkchart.chart.dto.CreateChartResponse;
import com.thinkfree.thinkchart.chart.dto.CreateChartRequest;
import com.thinkfree.thinkchart.chart.repository.ChartRepository;
import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.repository.CircleRepository;
import com.thinkfree.thinkchart.common.dto.WsAction;
import com.thinkfree.thinkchart.common.dto.WsMessage;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final CircleRepository circleRepository;
    private final ChartRepository chartRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Transactional
    public CreateChartResponse createChart(CreateChartRequest request) {
        List<String> circleIds = request.getCircleIds();
        List<Circle> circles = circleRepository.findAllById(circleIds);

        if (circles.size() != circleIds.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        if (circles.stream().anyMatch(circle -> circle.isUsedForChart())) {
            throw new GlobalException(ErrorCode.ALREADY_USED_CIRCLE);
        }

        Chart chart = chartRepository.save(request.toEntity());
        circles.forEach(circle -> circle.updateChartId(chart.getId()));
        circleRepository.saveAll(circles);

        CreateChartResponse response = CreateChartResponse.from(chart);
        messagingTemplate.convertAndSend("/topic/canvas", new WsMessage<>(WsAction.CHART_CREATED, response));
        return response;
    }

    public ChartResponse getChart(String id) {
        Chart chart = chartRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CHART_NOT_FOUND)
        );

        List<String> circleIds = chart.getCircleIds();
        List<Circle> circles = circleRepository.findAllById(circleIds);
        if (circles.size() != circleIds.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        return ChartResponse.from(chart, circles);
    }

}
