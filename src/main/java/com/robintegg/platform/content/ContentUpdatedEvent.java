package com.robintegg.platform.content;

import org.springframework.context.ApplicationEvent;

public class ContentUpdatedEvent extends ApplicationEvent {

    public ContentUpdatedEvent(Object source) {
        super(source);
    }
    
}
