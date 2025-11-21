package com.github.bproenca.nba.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostInfoController {

    private static final Logger log = LoggerFactory.getLogger(HostInfoController.class);

    @GetMapping("/info")
    public String hostInfo() {
        return getHostInfo();
    }

    public static String getHostInfo() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostname = inetAddress.getHostName();
            String ipAddress = inetAddress.getHostAddress();
            String result = "Hostname: " + hostname + " | IP Address: " + ipAddress + " | Version v6" + " | At: " + LocalTime.now();
            log.info(result);
            return result;
        } catch (UnknownHostException e) {
            log.error("Unable to retrieve host information");
            return "Unable to retrieve host information";
        }
    }

}
