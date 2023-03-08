package event.eventusecases;

import lombok.AllArgsConstructor;
import event.Event;
import event.EventPublisher;

@AllArgsConstructor
public class CreateAccountEventUseCase {
    private final EventPublisher eventPublisher;
    private static final String MESSAGE = "ACCOUNT_CREATED";

    //TODO Event should also include the payload/object
    public void execute() {
        eventPublisher.publish(new Event(MESSAGE));
    }
}
