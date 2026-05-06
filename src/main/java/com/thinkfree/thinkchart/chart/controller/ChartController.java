package com.thinkfree.thinkchart.chart.controller;

import com.thinkfree.thinkchart.chart.domain.Chart;
import com.thinkfree.thinkchart.chart.dto.ChartResponse;
import com.thinkfree.thinkchart.chart.dto.CreateChartRequest;
import com.thinkfree.thinkchart.chart.dto.UpdateBarRequest;
import com.thinkfree.thinkchart.chart.dto.UpdateChartRequest;
import com.thinkfree.thinkchart.chart.service.ChartService;
import com.thinkfree.thinkchart.common.dto.ApiResponse;
import com.thinkfree.thinkchart.common.dto.ApiResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "차트 API", description = "차트 생성/변경/삭제, 막대 옵션 변경")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChartController {

    private final ChartService chartService;

    @Operation(summary = "차트 생성 (HTTP)", description = "선택된 원으로 차트를 생성한다.")
    @PostMapping("/canvas/charts")
    public ResponseEntity<ApiResponse<?>> createChart(@RequestBody CreateChartRequest request) {
        ChartResponse response = chartService.createChart(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ApiResponseCode.CHART_CREATED, response));
    }

    @Operation(summary = "차트 옵션 변경 (HTTP)", description = "선택된 차트의 이름/x축/y축을 변경한다.")
    @PatchMapping("/canvas/charts/{id}")
    public void updateChart(@PathVariable String id, @RequestBody UpdateChartRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "차트 삭제 (HTTP)", description = "선택된 차트를 삭제한다.")
    @DeleteMapping("/canvas/charts/{id}")
    public void deleteChart(@PathVariable String id) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "차트 막대 옵션 변경 (HTTP)", description = "선택된 막대의 값/이름/색상을 변경한다.")
    @PatchMapping("/canvas/charts/{chartId}/{barId}")
    public void updateBar(@PathVariable String chartId, @PathVariable String barId, @RequestBody UpdateBarRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

}