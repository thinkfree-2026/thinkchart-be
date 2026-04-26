package com.thinkfree.thinkchart.circle.controller;

import com.thinkfree.thinkchart.circle.dto.CreateCircleRequest;
import com.thinkfree.thinkchart.circle.dto.UpdateCircleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "원 API", description = "원 생성/변경/삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CircleController {

    @Operation(summary = "원 생성 (HTTP)", description = "그려진 원을 생성한다.")
    @PostMapping("/canvas/circles")
    public void createCircle(@RequestBody CreateCircleRequest request) {
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
