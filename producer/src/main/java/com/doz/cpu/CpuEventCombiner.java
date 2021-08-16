package com.doz.cpu;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("cpuEventCombiner")
public class CpuEventCombiner implements Function<CpuLoadEvent, CpuEvent> {

    @Override
    public CpuEvent apply(CpuLoadEvent cpuLoadEvent) {
        return new CpuEvent(cpuLoadEvent.uuid(), cpuLoadEvent.load());
    }
}
