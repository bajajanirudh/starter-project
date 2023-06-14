package com.bajajanirudh;

import com.google.api.Metric;

import java.util.HashMap;
import java.util.Map;

public class PrepareMetricDescriptor {
    public Metric MetricDescriptor(String author, String MetricName){
        Map<String, String> metricLabels = new HashMap<>();
        metricLabels.put("store_id", author);
        return Metric.newBuilder()
                        .setType("custom.googleapis.com/" + MetricName)
                        .putAllLabels(metricLabels)
                        .build();
    }

}
