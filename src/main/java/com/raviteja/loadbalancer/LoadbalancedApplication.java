package com.raviteja.loadbalancer;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.raviteja.loadbalancer.repository.UserRepository;
import com.raviteja.loadbalancer.service.InstanceService;

@SpringBootApplication
@RestController
public class LoadbalancedApplication {
	@Autowired private UserRepository userRepository;
	@Autowired private InstanceService instanceService;

	public static void main(String[] args) {
		SpringApplication.run(LoadbalancedApplication.class, args);
	}

	@GetMapping("/")
	public Map<String, Object> home() {
		return Map.of(
			"owner", "RaviTeja - 90/100",
			"project", "90-load-balanced-app",
			"tier", "9 - DevOps + Cloud - Load Balanced",
			"database", "H2 In-Memory (RDS-Ready)",
			"loadBalancer", "Ready - ALB + 2 Instances Pattern",
			"instanceId", instanceService.getInstanceId(),
			"region", instanceService.getRegion(),
			"status", "Live - Load Balanced",
			"endpoints", List.of("GET /","GET /health","GET /instance","GET /load-test","GET /users","POST /users","GET /users/{id}","DELETE /users/{id}"),
			"totalUsers", userRepository.count()
		);
	}

	@GetMapping("/health")
	public Map<String, String> health() {
		return Map.of(
			"status", "UP",
			"database", "Connected",
			"project", "90-load-balanced-app",
			"instanceId", instanceService.getInstanceId(),
			"loadBalancer", "Healthy"
		);
	}
}