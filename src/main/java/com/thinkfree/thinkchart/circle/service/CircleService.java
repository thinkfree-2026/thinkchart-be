package com.thinkfree.thinkchart.circle.service;

import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.dto.CircleResponse;
import com.thinkfree.thinkchart.circle.dto.CreateCircleRequest;
import com.thinkfree.thinkchart.circle.repository.CircleRepository;
import com.thinkfree.thinkchart.common.dto.WsAction;
import com.thinkfree.thinkchart.common.dto.WsMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Circle circle = circleRepository.findById(id).get();
        CircleResponse response = CircleResponse.from(circle);
        log.info(circle.toString());
        return response;
    }


}