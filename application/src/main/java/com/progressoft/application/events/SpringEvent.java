package com.progressoft.application.events;

import event.Event;
import org.springframework.context.ApplicationEvent;


public class SpringEvent extends ApplicationEvent {
    private final String message;

    public SpringEvent(Object source, Event event) {
        super(source);
        this.message = event.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
