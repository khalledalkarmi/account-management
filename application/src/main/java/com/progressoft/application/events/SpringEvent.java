package com.progressoft.application.events;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;


public class SpringEvent extends ApplicationEvent {
    private final String message;
    public SpringEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
