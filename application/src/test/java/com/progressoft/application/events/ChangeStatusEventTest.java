package com.progressoft.application.events;

import event.eventusecases.ChangeStatusEventUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.event.EventListener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ChangeStatusEventTest {
    @SpyBean
    private EventTestListener eventListener;
    @Autowired
    ChangeStatusEventUseCase changeStatusEventUseCase;

    @Test
    public void testEventFires() {
        changeStatusEventUseCase.execute();
        verify(eventListener).handle(any(SpringEvent.class));
    }

    @TestComponent
    public static class EventTestListener {
        @EventListener
        public void handle(SpringEvent event) {
            Assertions.assertThat(event.getMessage()).isEqualTo("ACCOUNT_STATUS_CHANGED");
        }
    }
}

