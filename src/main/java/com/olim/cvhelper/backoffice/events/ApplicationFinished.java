package com.olim.cvhelper.backoffice.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ApplicationFinished extends ApplicationEvent {

    private Long chatId;

    public ApplicationFinished(Object source, Long chatId) {
        super(source);
        this.chatId = chatId;
    }
}
