package com.progressoft.application.events;

import org.springframework.context.ApplicationEvent;


public class SpringEvent extends ApplicationEvent {
    private final String message;

    public SpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
