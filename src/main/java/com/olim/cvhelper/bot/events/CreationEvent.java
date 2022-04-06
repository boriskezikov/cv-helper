package com.olim.cvhelper.bot.events;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreationEvent<T> {
    private final T object;

    public T get() {
        return object;
    }
}
