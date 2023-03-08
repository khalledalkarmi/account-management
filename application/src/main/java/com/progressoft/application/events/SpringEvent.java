package com.progressoft.application.events;

import event.Event;
import org.springframework.context.ApplicationEvent;


public class SpringEvent extends ApplicationEvent {
    private final String message;
    private final Object paylaod;

    public Object getPaylaod() {
        return paylaod;
    }

    public SpringEvent(Object source, Event event) {
        super(source);
        this.message = event.getMessage();
        this.paylaod = event.getPayload();
    }

    public String getMessage() {
        return message;
    }
}
