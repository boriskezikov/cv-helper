package com.olim.cvhelper.bot;

import com.olim.cvhelper.bot.config.BotConfig;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;


public class CvApplicationBot extends SpringWebhookBot {

    private final BotConfig botConfig;


    public CvApplicationBot(SetWebhook setWebhook, BotConfig botConfig) {
        super(setWebhook);
        this.botConfig = botConfig;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return botConfig.getWebhookPath();
    }
    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
