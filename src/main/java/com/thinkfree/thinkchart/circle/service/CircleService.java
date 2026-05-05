package com.thinkfree.thinkchart.circle.service;

import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.dto.CircleResponse;
import com.thinkfree.thinkchart.circle.dto.CreateCircleRequest;
import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import com.thinkfree.thinkchart.circle.repository.CircleRepository;
import com.thinkfree.thinkchart.common.dto.WsAction;
import com.thinkfree.thinkchart.common.dto.WsMessage;
import com.thinkfree.thinkchart.common.exception.ErrorCode;
import com.thinkfree.thinkchart.common.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class CircleService {

    private final CircleRepository circleRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public CircleResponse createCircle(CreateCircleRequest request) {
        Circle circle = circleRepository.save(request.toEntity());
        CircleResponse response = CircleResponse.from(circle);
        messagingTemplate.convertAndSend("/topic/canvas", new WsMessage<>(WsAction.CIRCLE_CREATED, response));
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

    public void deleteCircle(List<String> ids) {
        List<Circle> circles = circleRepository.findAllById(ids);
        circleRepository.deleteAllById(ids);

        List<CircleResponse> responses = circles.stream()
                .map(circle -> CircleResponse.from(circle))
                .toList();

        messagingTemplate.convertAndSend("/topic/canvas", new WsMessage<>(WsAction.CIRCLE_DELETED, responses));
    }

    public CircleResponse updateCircle(String id, UpdateCircleRequest request) {
        Circle circle = circleRepository.findById(id).orElseThrow(() -> new GlobalException(ErrorCode.CIRCLE_NOT_FOUND));
        boolean changed = false;

        if (request.getX() != null && circle.updateX(request.getX())) {
            changed = true;
        }

        if (request.getY() != null && circle.updateY(request.getY())) {
            changed = true;
        }

        if (request.getDiameter() != null && circle.updateDiameter(request.getDiameter())) {
            changed = true;
        }

        if (request.getRed() != null && circle.updateRed(request.getRed())) {
            changed = true;
        }

        if (request.getGreen() != null && circle.updateGreen(request.getGreen())) {
            changed = true;
        }

        if (request.getBlue() != null && circle.updateBlue(request.getBlue())) {
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
        messagingTemplate.convertAndSend("/topic/canvas", new WsMessage<>(WsAction.CIRCLE_UPDATED, response));
        return response;
    }
}