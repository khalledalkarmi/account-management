package event.eventusecases;

import lombok.AllArgsConstructor;
import event.Event;
import event.EventPublisher;

@AllArgsConstructor
public class CreateAccountEventUseCase {
    private final EventPublisher eventPublisher;
    private static final String MESSAGE = "ACCOUNT_CREATED";

    public void publish() {
        eventPublisher.publish(new Event(MESSAGE));
    }
}
