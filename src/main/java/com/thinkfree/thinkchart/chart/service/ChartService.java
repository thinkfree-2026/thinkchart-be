package com.thinkfree.thinkchart.chart.service;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.chart.dto.*;
import com.thinkfree.thinkchart.chart.repository.ChartRepository;
import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.service.CircleService;
import com.thinkfree.thinkchart.common.event.StompBroadcastEvent;
import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {

    private final CircleService circleService;
    private final ChartRepository chartRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final long VALUE_RADIUS_RATIO = 3;

    @Transactional
    public ChartResponse createChart(CreateChartRequest request) {
        Chart chart = chartRepository.save(request.toEntity());
        circleService.assignChartId(request.getCircleIds(), chart.getId());

        ChartResponse response = ChartResponse.from(chart);

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CHART_CREATED, response)
                )
        );

        return response;
    }

    public ChartDetailResponse getChartDetail(String id) {
        Chart chart = chartRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CHART_NOT_FOUND)
        );
        List<Circle> circles = circleService.findAllById(chart.getCircleIds());
        return ChartDetailResponse.from(chart, circles);
    }

    public List<ChartResponse> getChartList() {
        List<Chart> charts = chartRepository.findAll();

        return charts.stream()
                .map(chart -> ChartResponse.from(chart))
                .toList();
    }

    @Transactional
    public void deleteChart(String id) {
        Chart chart = chartRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CHART_NOT_FOUND)
        );

        circleService.deleteByChartId(id);
        chartRepository.delete(chart);

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CHART_DELETED, ChartResponse.from(chart))
                )
        );
    }

    @Transactional
    public ChartResponse updateChart(String id, UpdateChartRequest request) {
        Chart chart = chartRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CHART_NOT_FOUND)
        );

        boolean changed = false;

        if (request.getName() != null && chart.updateName(request.getName())) {
            changed = true;
        }

        if (request.getxAxis() != null && chart.updateXAxis(request.getxAxis())) {
            changed = true;
        }

        if (request.getyAxis() != null && chart.updateYAxis(request.getyAxis())) {
            changed = true;
        }

        if (!changed) {
            return ChartResponse.from(chart);
        }

        Chart savedChart = chartRepository.save(chart);
        ChartResponse response = ChartResponse.from(savedChart);

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CHART_UPDATED, response)
                )
        );

        return response;
    }

    @Transactional
    public BarResponse updateChartBar(String chartId, String barId, UpdateBarRequest request) {
        Chart chart = chartRepository.findById(chartId).orElseThrow(
                () -> new GlobalException(ErrorCode.CHART_NOT_FOUND)
        );

        List<String> circleIds = chart.getCircleIds();
        if (!circleIds.contains(barId)) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        Circle updatedCircle = circleService.updateCircleByChart(barId, request);
        // TODO: 수정된 circle만 반환할지, 차트에 포함된 circle 전체를 반환할지
//        List<Circle> circles = circleService.findAllById(circleIds);
//        ChartDetailResponse response = ChartDetailResponse.from(chart, circles);


        BarResponse response = BarResponse.from(chartId, barId, updatedCircle);

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas/charts/" + chartId,
                        new WsMessage<>(WsAction.CHART_UPDATED, response)
                )
        );

        return response;
    }

}
