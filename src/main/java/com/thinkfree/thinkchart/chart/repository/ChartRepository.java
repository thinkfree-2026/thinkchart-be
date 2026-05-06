package com.thinkfree.thinkchart.chart.repository;

import com.thinkfree.thinkchart.chart.domain.Chart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChartRepository extends MongoRepository<Chart, String> {
}
