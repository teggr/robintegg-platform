package com.robintegg.platform.index;

public interface IndexedContentFactory {

    Class<?> type();

    IndexedContent create(Object source);

}
