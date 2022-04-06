package com.olim.cvhelper.bot.events;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateCreationEvent extends CreationEvent<Update> {

    public UpdateCreationEvent(Update object) {
        super(object);
    }
}