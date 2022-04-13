package com.olim.cvhelper.bot.service;

import com.olim.cvhelper.bot.commands.HelpCommand;
import com.olim.cvhelper.bot.commands.StartCommand;
import com.olim.cvhelper.bot.config.BotConfig;
import com.olim.cvhelper.bot.handlers.AbstractHandler;
import com.olim.cvhelper.bot.handlers.HandlerOrchestrator;
import com.olim.cvhelper.bot.model.State;
import static com.olim.cvhelper.bot.model.StateOrder.USERNAME;
import com.olim.cvhelper.bot.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CvApplicationBot extends TelegramLongPollingCommandBot {

    private final BotConfig botConfig;
    private final StateRepository stateRepository;
    private final StartCommand startCommand;
    private final HelpCommand helpCommand;
    private final HandlerOrchestrator handlerOrchestrator;

    @PostConstruct
    public void init() {
        register(startCommand);
        register(helpCommand);
    }


    @SneakyThrows
    @Override
    public void processNonCommandUpdate(Update update) {
        Message message;
        CallbackQuery callbackQuery = update.getCallbackQuery();
        if (update.getMessage() != null) {
            message = update.getMessage();
        } else if (update.getCallbackQuery() != null) {
            message = callbackQuery.getMessage();
            message.setText(callbackQuery.getData());
        }
        else {
            throw new IllegalStateException("Illegal data access request!");
        }
        Long chatId = message.getChatId();
        Optional<State> userState = stateRepository.findById(chatId);
        AbstractHandler stateHandler;
        if (userState.isPresent()) {
            var stateId = userState.get().getStateId();
            stateHandler = handlerOrchestrator.getStateHandler(stateId);
        } else {
            stateHandler = handlerOrchestrator.getStateHandler(USERNAME.getOrder());
        }
        stateHandler.handle(this, message);

    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }
}
