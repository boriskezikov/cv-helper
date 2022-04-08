package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.config.BotState;
import com.olim.cvhelper.bot.exception.IllegalLinkedInProfileException;
import static com.olim.cvhelper.bot.util.TextConstants.*;
import com.olim.cvhelper.bot.model.State;
import com.olim.cvhelper.bot.model.StateOrder;
import com.olim.cvhelper.bot.repository.StateRepository;
import static java.util.regex.Pattern.compile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@BotState(orderId = StateOrder.LINKEDIN_LINK)
@Component
public class LinkedInProfileHandlerHanlder extends AbstractHandler {


    protected LinkedInProfileHandlerHanlder(StateRepository stateRepository) {
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
        String linkedinLinkRaw = message.getText();
        try {
            String validLinkedInUrl = checkValid(linkedinLinkRaw);
            Optional<State> stateOptional = stateRepository.findById(chatId);
            if (stateOptional.isPresent()) {
                State state = stateOptional.get();
                state.getStateData().setLinkedInLink(validLinkedInUrl);
                state.setStateId(StateOrder.CV_LINK.getOrder());
                stateRepository.save(state);
                response(START_COMMAND_CV_LINK, chatId, absSender);
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (IllegalLinkedInProfileException e) {
            response(INVALID_LINKEDIN_LINK_ERROR_MESSAGE, chatId, absSender);
        }
    }

    private String checkValid(String linkedInUrl) {
        var linkedInValidUrlPattern = compile("((https?:\\/\\/)?((www|\\w\\w)\\.)?linkedin\\.com\\/)((([\\w]{2,3})?)|([^\\/]+\\/(([\\w|\\d-&#?=])+\\/?){1,}))$");
        boolean matches = linkedInValidUrlPattern.matcher(linkedInUrl).matches();
        if (!matches) {
            throw new IllegalLinkedInProfileException(INVALID_LINKEDIN_LINK_ERROR_MESSAGE.getText());
        }
        return linkedInUrl.replace("/mwlite", "");
    }
}
