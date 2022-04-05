package com.olim.cvhelper.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class MessageHandler {


    public BotApiMethod<?> answerMessage(Message message) {
        return null;
    }

}
