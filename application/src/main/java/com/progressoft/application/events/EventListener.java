package com.progressoft.application.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EventListener implements ApplicationListener<SpringEvent> {

    @Override
    public void onApplicationEvent(SpringEvent event) {
        log.info("Event Publish {}", event.getMessage());
    }
}
