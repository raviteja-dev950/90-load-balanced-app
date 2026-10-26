package com.raviteja.loadbalancer.service;

import java.lang.management.ManagementFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InstanceService {
	@Value("${instance.id:instance-1}")
	private String instanceId;
	@Value("${app.region:ap-south-1}")
	private String region;
	
	public String getInstanceId() { return instanceId; }
	public String getRegion() { return region; }
	public long getUptime() { return ManagementFactory.getRuntimeMXBean().getUptime(); }
}