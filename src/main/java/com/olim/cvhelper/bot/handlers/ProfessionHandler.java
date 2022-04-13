package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.config.BotState;
import com.olim.cvhelper.bot.model.State;
import com.olim.cvhelper.bot.model.StateOrder;
import com.olim.cvhelper.bot.repository.StateRepository;
import static com.olim.cvhelper.bot.util.TextConstants.DO_NOT_UNDERSTAND;
import static com.olim.cvhelper.bot.util.TextConstants.START_COMMAND_LINKEDIN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@BotState(orderId = StateOrder.PROFESSION)
@Component
public class ProfessionHandler extends AbstractHandler {

    protected ProfessionHandler(StateRepository stateRepository) {
        super(stateRepository);
    }

    @Override
    public void handle(AbsSender absSender, Message message) throws TelegramApiException {
        log.debug(this.getClass().getSimpleName() + "called");
        Long chatId = message.getChat().getId();
        if (isMessageWithText(message)) {
            handle(absSender, message, chatId);
        } else {
            response(DO_NOT_UNDERSTAND, chatId, absSender);
        }
    }

    private void handle(AbsSender absSender, Message message, Long chatId) throws TelegramApiException {

        Optional<State> stateOptional = stateRepository.findById(chatId);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            state.getStateData().setProfession(message.getText());
            state.setStateId(StateOrder.LINKEDIN_LINK.getOrder());
            stateRepository.save(state);
            response(START_COMMAND_LINKEDIN, chatId, absSender);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
