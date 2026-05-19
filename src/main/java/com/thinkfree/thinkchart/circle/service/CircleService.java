package com.thinkfree.thinkchart.circle.service;

import com.thinkfree.thinkchart.chart.dto.UpdateBarRequest;
import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.dto.*;
import com.thinkfree.thinkchart.circle.repository.CircleRepository;
import com.thinkfree.thinkchart.common.event.StompBroadcastEvent;
import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CircleService {

    private final CircleRepository circleRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public CreateCircleResponse createCircle(CreateCircleRequest request) {
        Circle circle = circleRepository.save(request.toEntity());
        CreateCircleResponse response = CreateCircleResponse.from(circle, request.getClientCircleId());

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CIRCLE_CREATED, response)
                )
        );

        return response;
    }

    public List<CircleResponse> getAllCircles() {
        List<Circle> circles = circleRepository.findAll();
        return circles.stream()
                .map(circle -> CircleResponse.from(circle))
                .toList();
    }

    public CircleResponse getCircle(String id) {
        Circle circle = circleRepository.findById(id).orElseThrow(
                () -> new GlobalException(ErrorCode.CIRCLE_NOT_FOUND));
        CircleResponse response = CircleResponse.from(circle);
        return response;
    }

    @Transactional
    public void deleteCircle(List<String> ids) {
        List<Circle> circles = circleRepository.findAllById(ids);

        if (circles.size() != ids.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        if (circles.stream().anyMatch(Circle::isUsedForChart)) {
            throw new GlobalException(ErrorCode.ALREADY_USED_CIRCLE);
        }

        circleRepository.deleteAll(circles);

        List<CircleResponse> responses = circles.stream()
                .map(circle -> CircleResponse.from(circle))
                .toList();

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CIRCLE_DELETED, responses)
                )
        );
    }

    @Transactional
    public CircleResponse updateCircle(String id, UpdateCircleRequest request) {
        Circle circle = circleRepository.findById(id).orElseThrow(() -> new GlobalException(ErrorCode.CIRCLE_NOT_FOUND));
        boolean changed = false;

        if (request.getUserId() != null && circle.updateUserId(request.getUserId())) {
            changed = true;
        }

        if (request.getX() != null && circle.updateX(request.getX())) {
            changed = true;
        }

        if (request.getY() != null && circle.updateY(request.getY())) {
            changed = true;
        }

        if (request.getValue() != null && circle.updateValue(request.getValue())) {
            changed = true;
        }

        if (request.getColor() != null && circle.updateColor(request.getColor())) {
            changed = true;
        }

        if (request.getOpacity() != null && circle.updateOpacity(request.getOpacity())) {
            changed = true;
        }

        if (!changed) {
            return CircleResponse.from(circle);
        }

        Circle savedCircle = circleRepository.save(circle);
        CircleResponse response = CircleResponse.from(savedCircle);

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CIRCLE_UPDATED, response)
                )
        );
        return response;
    }

    @Transactional
    public void assignChartId(List<String> circleIds, String chartId) {
        List<Circle> circles = circleRepository.findAllById(circleIds);

        if (circles.size() != circleIds.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        if (circles.stream().anyMatch(circle -> circle.isUsedForChart())) {
            throw new GlobalException(ErrorCode.ALREADY_USED_CIRCLE);
        }

        circles.forEach(circle -> circle.updateChartId(chartId));
        circleRepository.saveAll(circles);
    }

    public List<Circle> findAllById(List<String> circleIds) {
        List<Circle> circles = circleRepository.findAllById(circleIds);
        if (circles.size() != circleIds.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }
        return circles;
    }

    @Transactional
    public void releaseCharts(List<String> circleIds) {
        List<Circle> circles = circleRepository.findAllById(circleIds);

        if (circles.size() != circleIds.size()) {
            throw new GlobalException(ErrorCode.CIRCLE_NOT_FOUND);
        }

        circles.forEach(circle -> circle.releaseChart());
        circleRepository.saveAll(circles);
    }

    @Transactional
    public void releaseChart(String circleId) {
        Circle circle = circleRepository.findById(circleId).orElseThrow(
                () -> new GlobalException(ErrorCode.CIRCLE_NOT_FOUND)
        );

        circle.releaseChart();
        circleRepository.save(circle);
    }

    @Transactional
    public Circle updateCircleByChart(String circleId, UpdateBarRequest request) {
        Circle circle = circleRepository.findById(circleId).orElseThrow(
                () -> new GlobalException(ErrorCode.CIRCLE_NOT_FOUND)
        );

        boolean changed = false;

        if (request.getName() != null && circle.updateName(request.getName())) {
            changed = true;
        }
        if (request.getValue() != null && circle.updateValue(request.getValue())) {
            changed = true;
        }
        if (request.getColor() != null && circle.updateColor(request.getColor())) {
            changed = true;
        }
        if (request.getOpacity() != null && circle.updateOpacity(request.getOpacity())) {
            changed = true;
        }

        if (!changed) {
            return circle;
        }

        return circleRepository.save(circle);
    }

    @Transactional
    public List<CircleResponse> moveCircles(List<MoveCirclesRequest> requests) {
        List<String> circleIds = requests.stream()
                .map(req -> req.getId())
                .toList();

        List<Circle> circles = circleRepository.findAllById(circleIds);

        Map<String, Circle> circleMap = circles.stream()
                .collect(Collectors.toMap(
                        circle -> circle.getId(),
                        Function.identity())
                );

        for (MoveCirclesRequest request : requests) {
            Circle circle = circleMap.get(request.getId());

            if (circle == null) {
                continue;
            }

            if (request.getX() != null) {
                circle.updateX(request.getX());
            }
            if (request.getY() != null) {
                circle.updateY(request.getY());
            }
        }

        List<Circle> updatedCircles = circleRepository.saveAll(circles);

        List<CircleResponse> responses = updatedCircles.stream()
                .map(circle -> CircleResponse.from(circle))
                .toList();

        eventPublisher.publishEvent(
                new StompBroadcastEvent(
                        "/topic/canvas",
                        new WsMessage<>(WsAction.CIRCLE_UPDATED, responses)
                )
        );

        return responses;
    }
}