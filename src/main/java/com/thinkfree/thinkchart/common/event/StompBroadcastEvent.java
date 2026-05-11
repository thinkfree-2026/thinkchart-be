package com.thinkfree.thinkchart.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StompBroadcastEvent {

    private final String destination;
    private final WsMessage<?> message;
}