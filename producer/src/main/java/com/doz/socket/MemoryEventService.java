package com.doz.socket;

import com.doz.cpu.CpuLoadEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.UUID;
import java.util.logging.Logger;

@ConditionalOnProperty("events.enabled")
@Service("memoryEventProducer")
public class MemoryEventService {

    private static final Logger LOGGER = Logger.getLogger(MemoryEventService.class.getName());
    private final StreamBridge streamBridge;

    public MemoryEventService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 2000)
    public void startRecording() {
        LOGGER.info("Producer Sending Memory Event");

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        MemoryEvent memoryEvent = new MemoryEvent(UUID.randomUUID().toString(), heapMemoryUsage.getUsed());

        streamBridge.send("memory-v1", memoryEvent);
    }
}
