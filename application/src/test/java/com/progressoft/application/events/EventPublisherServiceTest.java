package com.progressoft.application.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class EventPublisherServiceTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private EventPublisherService eventPublisherService;

    //TODO verify that applicationEventPublisher was called with the correct arguments


    @Test
    public void shouldCallPublishMethod() {

    }

}