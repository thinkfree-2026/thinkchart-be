package com.thinkfree.thinkchart.circle.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Document(collection = "circles")
public class Circle {

    @Id
    private String id;

    private double x;
    private double y;

    private double diameter;

    @Builder.Default
    private double red = 0.111;
    @Builder.Default
    private double green = 0.222;
    @Builder.Default
    private double blue = 0.333;
    @Builder.Default
    private double opacity = 0.9;

    @CreatedDate
    private Long createdAt;
}
