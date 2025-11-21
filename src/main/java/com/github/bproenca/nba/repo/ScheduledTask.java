package com.github.bproenca.nba.repo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.bproenca.nba.db.TenantDataSourceProperties;

@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private TenantDataSourceProperties tenantDataSourceProperties;

    private int cnt = 0;

    @Scheduled(fixedRateString = "${myapp.fixedRate}")
    public void insertData() {
        for (Map<String, String> tenantProperties : tenantDataSourceProperties.getDatasource()) {
            if (!"NA".equals(tenantProperties.get("poolName"))) {
                tenantRepo.insertData(tenantProperties.get("poolName"));
            }
        }
        printMemoryStatus(++cnt);
    }

    private void printMemoryStatus(int cnt) {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        StringBuilder builder = new StringBuilder();
        builder.append("## ").append(cnt);
        builder.append("\tUsed Memory   : " + (runtime.totalMemory() - runtime.freeMemory()) / mb + " mb");
        builder.append("\tFree Memory   : " + runtime.freeMemory() / mb + " mb");
        builder.append("\tTotal Memory  : " + runtime.totalMemory() / mb + " mb");
        builder.append("\tMax Memory    : " + runtime.maxMemory() / mb + " mb");
        log.info(builder.toString());
    }
}