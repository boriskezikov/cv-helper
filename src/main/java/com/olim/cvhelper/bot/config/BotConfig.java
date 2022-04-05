package com.olim.cvhelper.bot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class BotConfig {

    @Value("${telegram.bot.name}")
    private String name;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.admin}")
    private String admin;

    @Value("${telegram.bot.webhook-path}")
    private String webhookPath;
}
