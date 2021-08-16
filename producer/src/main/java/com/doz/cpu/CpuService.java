package com.doz.cpu;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.UUID;
import java.util.logging.Logger;

@ConditionalOnProperty("events.enabled")
@Service
public class CpuService {

    private static final Logger LOGGER = Logger.getLogger(CpuService.class.getName());
    private final StreamBridge streamBridge;

    public CpuService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 2000)
    public void startRecording() {
        LOGGER.info("Producer Sending CPU Event");

        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();

        CpuLoadEvent cpuLoadEvent = new CpuLoadEvent(UUID.randomUUID().toString(), systemLoadAverage);

        streamBridge.send("cpu-load-v1", cpuLoadEvent);
    }
}
