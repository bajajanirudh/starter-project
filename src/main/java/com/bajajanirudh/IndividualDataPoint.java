package com.bajajanirudh;

import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TypedValue;
import com.google.protobuf.util.Timestamps;

public class IndividualDataPoint {
    public Point individualDataPoint(Double metricValue){
        TimeInterval interval =
                TimeInterval.newBuilder()
                        .setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
                        .build();
        TypedValue value = TypedValue.newBuilder().setDoubleValue(metricValue).build();
        return Point.newBuilder().setInterval(interval).setValue(value).build();
    }
}
