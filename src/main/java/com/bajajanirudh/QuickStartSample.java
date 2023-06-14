package com.bajajanirudh;

// CHECKSTYLE OFF: VariableDeclarationUsageDistance
// [START monitoring_quickstart]

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.CreateTimeSeriesRequest;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import java.util.ArrayList;
import java.util.List;

// Imports the Google Cloud client library

public class QuickStartSample {

    public static void main(String... args) throws Exception {
        // Your Google Cloud Platform project ID
        String projectId = "bajajanirudh-cdf-intern";

//        if (projectId == null) {
//            System.err.println("Usage: QuickstartSample -projectId=YOUR_PROJECT_ID");
//            return;
//        }

        // Instantiates a client
        MetricServiceClient metricServiceClient = MetricServiceClient.create();

        // Prepares an individual data point
        Double metricValue = 3.14;
        IndividualDataPoint individualDataPoint = new IndividualDataPoint();
        Point point = individualDataPoint.individualDataPoint(metricValue);

        List<Point> pointList = new ArrayList<>();
        pointList.add(point);

        ProjectName name = ProjectName.of(projectId);

        // Prepares the metric descriptor
        String author = "bajajanirudh";
        String MetricName = "bajajanirudh-custom-metric";
        PrepareMetricDescriptor metricDescriptor = new PrepareMetricDescriptor();
        Metric metric = metricDescriptor.MetricDescriptor(author, MetricName);

        // create MonitoredResource
        String InstanceID = "1234567890123456789";
        String Zone = "us-central1";
        MonitoredResourceDescriptor monitoredResourceDescriptor = new MonitoredResourceDescriptor();
        MonitoredResource resource = monitoredResourceDescriptor.monitoredResourceDescriptor(InstanceID, Zone);


        // Create time series request
        WriteTimeSeries writetimeseries = new WriteTimeSeries();
        CreateTimeSeriesRequest request = writetimeseries.writeTimeSeries(metric, resource, pointList, name);

        // Writes time series data
        metricServiceClient.createTimeSeries(request);

        System.out.printf("Done writing time series data.%n");


        metricServiceClient.close();
    }
}