package com.olim.cvhelper.backoffice.events;

import org.springframework.context.ApplicationEvent;

public class NewApplicationEvent extends ApplicationEvent {

    public NewApplicationEvent(Object source) {
        super(source);
    }
}
