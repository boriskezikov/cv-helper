package com.olim.cvhelper.bot.commands;

import com.olim.cvhelper.bot.handlers.AbstractHandler;
import com.olim.cvhelper.bot.handlers.HandlerOrchestrator;
import com.olim.cvhelper.bot.handlers.TextConstants;
import com.olim.cvhelper.bot.model.State;
import static com.olim.cvhelper.bot.model.StateOrder.USERNAME;
import com.olim.cvhelper.bot.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StartCommand implements IBotCommand {

    private final HandlerOrchestrator handlerOrchestrator;
    private final StateRepository stateRepository;

    @Override
    public String getCommandIdentifier() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Старт";
    }

    @SneakyThrows
    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        Long chatId = message.getChatId();
        Optional<State> userState = stateRepository.findById(chatId);
        AbstractHandler stateHandler;
        if (userState.isPresent()) {
            var stateId = userState.get().getStateId();
            stateHandler = handlerOrchestrator.getStateHandler(stateId);
            stateHandler.handle(absSender, message);
        } else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(TextConstants.START_COMMAND_USERNAME.getText());
            sendMessage.setParseMode(TextConstants.START_COMMAND_USERNAME.getParseMode());
            sendMessage.enableMarkdown(true);
            sendMessage.setChatId(chatId.toString());
            absSender.execute(sendMessage);
        }
    }
}
