package com.progressoft.application.events;

import event.Event;
import event.EventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class EventPublisherService implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Event event) {
        applicationEventPublisher.publishEvent(new SpringEvent("", event));
    }
}
