package com.olim.cvhelper.bot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final BotConfig botConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(botConfig.getWebhookPath()).build();
    }
}
