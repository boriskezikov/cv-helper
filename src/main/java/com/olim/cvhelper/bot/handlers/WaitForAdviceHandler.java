package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.config.BotState;
import static com.olim.cvhelper.bot.handlers.TextConstants.DO_NOT_UNDERSTAND;
import static com.olim.cvhelper.bot.handlers.TextConstants.WAIT_FOR_HELP;
import com.olim.cvhelper.bot.model.State;
import com.olim.cvhelper.bot.model.StateOrder;
import com.olim.cvhelper.bot.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@BotState(orderId = StateOrder.WAIT_FOR_HELP)
@Component
public class WaitForAdviceHandler extends AbstractHandler {

    protected WaitForAdviceHandler(StateRepository stateRepository) {
        super(stateRepository);
    }

    @Override
    public void handle(AbsSender absSender, Message message) throws TelegramApiException {
        log.info(this.getClass().getSimpleName() + "called");
        Long chatId = message.getChat().getId();
        if (isMessageWithText(message)) {
            handle(absSender, chatId);
        } else {
            response(DO_NOT_UNDERSTAND, chatId, absSender);
        }
    }

    private void handle(AbsSender absSender, Long chatId) throws TelegramApiException {
        Optional<State> stateOptional = stateRepository.findById(chatId);
        if (stateOptional.isPresent()) {
            response(WAIT_FOR_HELP, chatId, absSender);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}