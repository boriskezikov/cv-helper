package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.repository.StateRepository;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractHandler {

    protected StateRepository stateRepository;

    protected AbstractHandler(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public abstract void handle(AbsSender absSender, Message update) throws TelegramApiException;

    protected boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }

    protected boolean isMessageWithText(Message message) {
        return message.hasText();
    }

    protected void response(TextConstants text, Long chatId, AbsSender absSender) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text.getText());
        sendMessage.setParseMode(text.getParseMode());
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId.toString());
        absSender.execute(sendMessage);
    }

}
