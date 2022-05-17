package com.robintegg.platform.content;

import org.springframework.context.ApplicationEvent;

public class ContentCreatedEvent extends ApplicationEvent {

    public ContentCreatedEvent(Object source) {
        super(source);
    }
    
}
