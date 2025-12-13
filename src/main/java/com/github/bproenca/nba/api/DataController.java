package com.github.bproenca.nba.api;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.bproenca.nba.repo.TenantRepo;

@RestController
public class DataController {
    
    @Autowired
    private TenantRepo tenantRepo;

    private Logger log = LoggerFactory.getLogger(DataController.class);

    @GetMapping("/data/tenant/{tenant}")
	public List<Map<String, Object>> data(@PathVariable String tenant) {
        log.info(">> [GET] /data/tenant/{} at {}", tenant, LocalDateTime.now());
        return tenantRepo.getData(tenant);
	}

    @GetMapping("/nls/tenant/{tenant}")
	public List<Map<String, Object>> nls(@PathVariable String tenant) {
        log.info(">> [GET] /nls/tenant/{} at {}", tenant, LocalDateTime.now());
        return tenantRepo.getNLS(tenant);
	} 
    
}