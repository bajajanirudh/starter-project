package com.bajajanirudh;

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.monitoring.v3.CreateTimeSeriesRequest;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeSeries;

import java.util.ArrayList;
import java.util.List;

public class WriteTimeSeries {

    public CreateTimeSeriesRequest writeTimeSeries (Metric metric, MonitoredResource resource, List<Point> pointList, ProjectName name){
        // Prepares the time series request
        TimeSeries timeSeries =
                TimeSeries.newBuilder()
                        .setMetric(metric)
                        .setResource(resource)
                        .addAllPoints(pointList)
                        .build();
        List<TimeSeries> timeSeriesList = new ArrayList<>();
        timeSeriesList.add(timeSeries);

        return CreateTimeSeriesRequest.newBuilder()
                        .setName(name.toString())
                        .addAllTimeSeries(timeSeriesList)
                        .build();
    }
}
