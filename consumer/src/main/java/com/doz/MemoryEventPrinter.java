package com.doz;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component("memoryEventPrinter")
public class MemoryEventPrinter implements Consumer<Object> {

    @Override
    public void accept(Object event) {
        System.out.println("Memory Event" + event.toString());
    }
}
