package com.robintegg.platform.content;

import org.springframework.context.ApplicationEventPublisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ContentEditor {

    private final ApplicationEventPublisher eventPublisher;

    protected void publishCreatedEvent(Object source) {
        eventPublisher.publishEvent(new ContentCreatedEvent(source));
    }

    protected void publishUpdatedEvent(Object source) {
        eventPublisher.publishEvent(new ContentUpdatedEvent(source));
    }

    protected void publishDeletedEvent(Object source) {
        eventPublisher.publishEvent(new ContentDeletedEvent(source));
    }

}
