package com.bajajanirudh;

import com.google.api.MonitoredResource;

import java.util.HashMap;
import java.util.Map;

public class MonitoredResourceDescriptor {
    public MonitoredResource monitoredResourceDescriptor(String InstanceID, String Zone) {

        // Prepares the monitored resource descriptor
        Map<String, String> resourceLabels = new HashMap<>();
        resourceLabels.put("instance_id", InstanceID);
        resourceLabels.put("zone", Zone);
        return MonitoredResource.newBuilder().setType("gce_instance").putAllLabels(resourceLabels).build();
    }
}
