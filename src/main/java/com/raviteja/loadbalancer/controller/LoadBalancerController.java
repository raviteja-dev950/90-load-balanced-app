package com.raviteja.loadbalancer.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.raviteja.loadbalancer.repository.UserRepository;
import com.raviteja.loadbalancer.service.InstanceService;

@RestController
public class LoadBalancerController {
	@Autowired private InstanceService instanceService;
	@Autowired private UserRepository userRepository;

	@GetMapping("/instance")
	public Map<String, Object> instanceInfo() {
		return Map.of(
			"instanceId", instanceService.getInstanceId(),
			"region", instanceService.getRegion(),
			"port", "9797",
			"status", "Healthy - In ALB Target Group",
			"uptimeMs", instanceService.getUptime(),
			"totalUsers", userRepository.count()
		);
	}
	@GetMapping("/load-test")
	public Map<String, Object> loadTest() {
		return Map.of(
			"message", "Load Balancer Test - Round Robin OK",
			"servedBy", instanceService.getInstanceId(),
			"timestamp", System.currentTimeMillis(),
			"load", "Low - Ready for 1000+ RPS"
		);
	}
}