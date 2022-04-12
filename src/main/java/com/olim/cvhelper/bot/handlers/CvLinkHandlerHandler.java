package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import com.olim.cvhelper.backoffice.entity.CvApplicationStatus;
import com.olim.cvhelper.backoffice.service.CvApplicationService;
import com.olim.cvhelper.backoffice.service.UserService;
import com.olim.cvhelper.bot.config.BotState;
import com.olim.cvhelper.bot.model.State;
import com.olim.cvhelper.bot.model.StateOrder;
import com.olim.cvhelper.bot.repository.StateRepository;
import static com.olim.cvhelper.bot.util.TextConstants.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@BotState(orderId = StateOrder.CV_LINK)
@Component
public class CvLinkHandlerHandler extends AbstractHandler {


    protected CvLinkHandlerHandler(StateRepository stateRepository) {
        super(stateRepository);
    }

    @Override
    public void handle(AbsSender absSender, Message message) throws TelegramApiException {
        log.info(this.getClass().getSimpleName() + "called");
        Long chatId = message.getChat().getId();
        if (isMessageWithText(message)) {
            handle(absSender, message, chatId);
        } else {
            response(DO_NOT_UNDERSTAND, chatId, absSender);
        }
    }

    private void handle(AbsSender absSender, Message message, Long chatId) throws TelegramApiException {
        String cvLink = message.getText();
        Optional<State> stateOptional = stateRepository.findById(chatId);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            state.getStateData().setCvLink(cvLink);
            state.setStateId(StateOrder.QUESTION.getOrder());
            stateRepository.save(state);
            response(START_COMMAND_QUESTION, chatId, absSender);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}