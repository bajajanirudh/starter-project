package com.bajajanirudh;

// CHECKSTYLE OFF: VariableDeclarationUsageDistance
// [START monitoring_quickstart]

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.*;
import com.google.protobuf.util.Timestamps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Imports the Google Cloud client library

public class QuickStartSample {

    public static void publishMetric(double metricValue) throws Exception{
        String projectId = "bajajanirudh-cdf-intern";

//        if (projectId == null) {
//            System.err.println("Usage: QuickstartSample -projectId=YOUR_PROJECT_ID");
//            return;
//        }

        // Instantiates a client
        MetricServiceClient metricServiceClient = MetricServiceClient.create();

        // Prepares an individual data point
        TimeInterval interval =
                TimeInterval.newBuilder()
                        .setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
                        .build();
        TypedValue value = TypedValue.newBuilder().setDoubleValue(metricValue).build();
        Point point = Point.newBuilder().setInterval(interval).setValue(value).build();

        List<Point> pointList = new ArrayList<>();
        pointList.add(point);

        ProjectName name = ProjectName.of(projectId);

        // Prepares the metric descriptor
        String author = "bajajanirudh";
        String MetricName = "bajajanirudh-custom-metric";
        Map<String, String> metricLabels = new HashMap<>();
        metricLabels.put("store_id", author);
        Metric metric = Metric.newBuilder()
                .setType("custom.googleapis.com/" + MetricName)
                .putAllLabels(metricLabels)
                .build();

        // create MonitoredResource
        String Zone = "us-central1";
        Map<String, String> resourceLabels = new HashMap<>();
        resourceLabels.put("location", Zone);
        resourceLabels.put("cluster_name", "starter-cluster-project");
        resourceLabels.put("project_id", projectId);
        resourceLabels.put("namespace_name", System.getenv("NAMESPACE"));
        resourceLabels.put("pod_name", System.getenv("POD_NAME"));
        MonitoredResource resource = MonitoredResource.newBuilder().setType("k8s_pod").putAllLabels(resourceLabels).build();


        // Create time series request
        TimeSeries timeSeries =
                TimeSeries.newBuilder()
                        .setMetric(metric)
                        .setResource(resource)
                        .addAllPoints(pointList)
                        .build();
        List<TimeSeries> timeSeriesList = new ArrayList<>();
        timeSeriesList.add(timeSeries);
        CreateTimeSeriesRequest request = CreateTimeSeriesRequest.newBuilder()
                .setName(name.toString())
                .addAllTimeSeries(timeSeriesList)
                .build();

        // Writes time series data
        metricServiceClient.createTimeSeries(request);

        //System.out.printf("Done writing time series data.%n");


        metricServiceClient.close();
    }
}