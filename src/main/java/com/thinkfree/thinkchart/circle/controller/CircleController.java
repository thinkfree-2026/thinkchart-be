package com.thinkfree.thinkchart.circle.controller;

import com.thinkfree.thinkchart.circle.domain.Circle;
import com.thinkfree.thinkchart.circle.dto.CircleResponse;
import com.thinkfree.thinkchart.circle.dto.CreateCircleRequest;
import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import com.thinkfree.thinkchart.circle.service.CircleService;
import com.thinkfree.thinkchart.common.dto.ApiResponse;
import com.thinkfree.thinkchart.common.dto.ApiResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "원 API", description = "원 생성/변경/삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CircleController {

    private final CircleService circleService;

    @Operation(summary = "원 생성 (HTTP)", description = "그려진 원을 생성한다.")
    @PostMapping("/canvas/circles")
    public ApiResponse<CircleResponse> createCircle(@RequestBody @Valid CreateCircleRequest request) {
        CircleResponse circle = circleService.createCircle(request);
        return ApiResponse.of(ApiResponseCode.CIRCLE_CREATED, circle);
    }

    @Operation(summary = "원 단건 조회 (HTTP)", description = "그려진 원을 조회한다.")
    @GetMapping("/canvas/circles/{id}")
    public ApiResponse<CircleResponse> getCircles(@PathVariable String id) {
        CircleResponse circle = circleService.getCircle(id);
        return ApiResponse.of(ApiResponseCode.CANVAS_LOADED, circle);
    }

    @Operation(summary = "모든 원 조회 (HTTP)", description = "캔버스에 그려진 모든 원을 조회한다.")
    @GetMapping("/canvas/circles")
    public ApiResponse<List<CircleResponse>> getCircles() {
        List<CircleResponse> circles = circleService.getAllCircles();
        return ApiResponse.of(ApiResponseCode.CANVAS_LOADED, circles);
    }

    @Operation(summary = "원 단건 삭제 (HTTP)", description = "선택된 원 단건을 삭제한다.")
    @DeleteMapping("/canvas/circles/{id}")
    public void deleteCircle(@PathVariable String id) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 다건 삭제 (HTTP)", description = "선택된 원 다건을 삭제한다.")
    @DeleteMapping("/canvas/circles")
    public void deleteCircles(@RequestParam String ids) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }

    @Operation(summary = "원 옵션 변경 (HTTP)", description = "선택된 원의 사용여부/크기/색상을 변경한다.")
    @PatchMapping("/canvas/circles/{id}")
    public void updateCircle(@PathVariable String id, @RequestBody UpdateCircleRequest request) {
        // 실제 로직은 구현하지 않음 (문서 노출용)
    }
}
