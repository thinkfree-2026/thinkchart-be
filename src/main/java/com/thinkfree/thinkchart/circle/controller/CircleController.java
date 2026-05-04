package com.thinkfree.thinkchart.circle.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Tag(name = "원 API", description = "원 생성/변경/삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CircleController {

    private final CircleService circleService;

    @Operation(summary = "원 생성 (HTTP)", description = "그려진 원을 생성한다.")
    @PostMapping("/canvas/circles")
    public ResponseEntity<ApiResponse<CircleResponse>> createCircle(@RequestBody @Valid CreateCircleRequest request) {
        CircleResponse circle = circleService.createCircle(request);
        return ResponseEntity
                .created(URI.create("/api/v1/canvas/circles/" + circle.getId()))
                .body(ApiResponse.of(ApiResponseCode.CIRCLE_CREATED, circle));
    }

    @Operation(summary = "원 단건 조회 (HTTP)", description = "그려진 원을 조회한다.")
    @GetMapping("/canvas/circles/{id}")
    public ResponseEntity<ApiResponse<CircleResponse>> getCircles(@PathVariable String id) {
        CircleResponse circle = circleService.getCircle(id);
        return ResponseEntity.ok(ApiResponse.of(ApiResponseCode.CANVAS_LOADED, circle));
    }

    @Operation(summary = "모든 원 조회 (HTTP)", description = "캔버스에 그려진 모든 원을 조회한다.")
    @GetMapping("/canvas/circles")
    public ResponseEntity<ApiResponse<List<CircleResponse>>> getCircles() {
        List<CircleResponse> circles = circleService.getAllCircles();
        return ResponseEntity.ok(ApiResponse.of(ApiResponseCode.CANVAS_LOADED, circles));
    }

    @Operation(summary = "원 단건 삭제 (HTTP)", description = "선택된 원 단건을 삭제한다.")
    @DeleteMapping("/canvas/circles/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCircle(@PathVariable String id) {
        circleService.deleteCircle(Collections.singletonList(id));
        return ResponseEntity.ok(ApiResponse.of(ApiResponseCode.CIRCLE_DELETED));
    }

    @Operation(summary = "원 다건 삭제 (HTTP)", description = "선택된 원 다건을 삭제한다.")
    @DeleteMapping("/canvas/circles")
    public ResponseEntity<ApiResponse<String>> deleteCircles(@RequestParam List<String> ids) {
        circleService.deleteCircle(ids);
        return ResponseEntity.ok(ApiResponse.of(ApiResponseCode.CIRCLE_DELETED));
    }

    @Operation(summary = "원 옵션 변경 (HTTP)", description = "선택된 원의 사용여부/크기/색상을 변경한다.")
    @PatchMapping("/canvas/circles/{id}")
    public ResponseEntity<ApiResponse<CircleResponse>> updateCircle(@PathVariable String id, @RequestBody @Valid UpdateCircleRequest request) {
        CircleResponse response = circleService.updateCircle(id, request);
        return ResponseEntity.ok(ApiResponse.of(ApiResponseCode.CIRCLE_UPDATED, response));
    }
}
