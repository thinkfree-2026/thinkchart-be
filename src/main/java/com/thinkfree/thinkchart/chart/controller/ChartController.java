package com.thinkfree.thinkchart.chart.controller;

import com.thinkfree.thinkchart.chart.dto.*;
import com.thinkfree.thinkchart.chart.service.ChartService;
import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import com.thinkfree.thinkchart.common.dto.ApiResponse;
import com.thinkfree.thinkchart.common.dto.ApiResponseCode;
import com.thinkfree.thinkchart.common.event.WsAction;
import com.thinkfree.thinkchart.common.event.WsMessage;
import com.thinkfree.thinkchart.cursor.dto.CursorMovePayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "3. 차트 API", description = "차트 생성/조회/변경/삭제, 막대 옵션 변경")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChartController {

    private final ChartService chartService;

    @Operation(summary = "차트 생성 (HTTP)", description = "선택된 원으로 차트를 생성한다.")
    @PostMapping("/canvas/charts")
    public ResponseEntity<ApiResponse<ChartResponse>> createChart(@RequestBody @Valid CreateChartRequest request) {
        ChartResponse response = chartService.createChart(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseCode.CHART_CREATED, response));
    }

    @Operation(summary = "차트 조회 (HTTP)", description = "차트 리스트에서 선택한 차트를 조회한다.")
    @GetMapping("/canvas/charts/{id}")
    public ResponseEntity<ApiResponse<ChartDetailResponse>> getChart(@PathVariable String id) {
        ChartDetailResponse response = chartService.getChartDetail(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(ApiResponseCode.CHART_SUCCESS, response));
    }

    @Operation(summary = "차트 목록 조회 (HTTP)", description = "차트 목록을 조회한다.")
    @GetMapping("/canvas/charts")
    public ResponseEntity<ApiResponse<List<ChartResponse>>> getChartList() {
        List<ChartResponse> response = chartService.getChartList();
        return ResponseEntity
                .ok(ApiResponse.success(ApiResponseCode.CHART_SUCCESS, response));
    }

    @Operation(summary = "차트 옵션 변경 (HTTP)", description = "선택된 차트의 이름/x축/y축을 변경한다.")
    @PatchMapping("/canvas/charts/{id}")
    public ResponseEntity<ApiResponse<ChartResponse>> updateChart(@PathVariable String id, @RequestBody UpdateChartRequest request) {
        ChartResponse response = chartService.updateChart(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(ApiResponseCode.CHART_UPDATED, response));
    }

    @Operation(summary = "차트 삭제 (HTTP)", description = "선택된 차트를 삭제한다.")
    @DeleteMapping("/canvas/charts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteChart(@PathVariable String id) {
        chartService.deleteChart(id);
        return ResponseEntity.ok(ApiResponse.success(ApiResponseCode.CHART_DELETED));
    }

    @Operation(summary = "차트 막대 옵션 변경 (HTTP)", description = "선택된 막대의 값/이름/색상을 변경한다.")
    @PatchMapping("/canvas/charts/{chartId}/{barId}")
    public ResponseEntity<ApiResponse<BarResponse>> updateBar(@PathVariable String chartId, @PathVariable String barId, @RequestBody @Valid UpdateBarRequest request) {
        BarResponse response = chartService.updateChartBar(chartId, barId, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(ApiResponseCode.CHART_RESIZED, response));
    }

}