package com.progressoft.application.events;

import event.Event;

public class EventMapper {

    public SpringEvent map(Event event){
        return new SpringEvent("",event.getMessage());
    }
}
