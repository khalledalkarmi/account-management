package event.eventusecases;

import lombok.AllArgsConstructor;
import event.Event;
import event.EventPublisher;

@AllArgsConstructor
public class ChangeStatusEventUseCase {
    private final EventPublisher eventPublisher;
    private static final String MESSAGE = "ACCOUNT_STATUS_CHANGED";
    public void execute() {
         eventPublisher.publish(new Event(MESSAGE));
    }
}
