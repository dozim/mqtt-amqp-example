package com.doz;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("cpuEventPrinter")
public class CpuEventPrinter implements Consumer<Object> {

    @Override
    public void accept(Object event) {
        System.out.println("Cpu Event" + event.toString());
    }
}
