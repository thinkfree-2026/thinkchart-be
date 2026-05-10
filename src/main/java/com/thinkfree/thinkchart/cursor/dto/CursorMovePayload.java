package com.thinkfree.thinkchart.cursor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CursorMovePayload {

    @Schema(description = "커서ID", example = "")
    @NotBlank
    private String id;

    @Schema(description = "X좌표", example = "15.8")
    @NotNull
    private Double x;

    @Schema(description = "y좌표", example = "-65.2")
    @NotNull
    private Double y;

    @Schema(description = "색상", example = "#000000")
    private String color;
}
