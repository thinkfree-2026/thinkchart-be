package com.thinkfree.thinkchart.circle.repository;

import com.thinkfree.thinkchart.circle.domain.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CircleRepository extends MongoRepository<Circle, String> {
}
