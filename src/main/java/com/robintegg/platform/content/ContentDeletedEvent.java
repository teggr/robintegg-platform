package com.robintegg.platform.content;

import org.springframework.context.ApplicationEvent;

public class ContentDeletedEvent extends ApplicationEvent {

    public ContentDeletedEvent(Object source) {
        super(source);
    }
    
}
