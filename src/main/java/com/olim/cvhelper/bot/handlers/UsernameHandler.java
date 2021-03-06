package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.bot.config.BotState;
import com.olim.cvhelper.bot.model.CvApplicationRequest;
import com.olim.cvhelper.bot.model.State;
import com.olim.cvhelper.bot.model.StateOrder;
import com.olim.cvhelper.bot.repository.StateRepository;
import static com.olim.cvhelper.bot.util.TextConstants.DO_NOT_UNDERSTAND;
import static com.olim.cvhelper.bot.util.TextConstants.START_COMMAND_PROFESSION;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@BotState(orderId = StateOrder.USERNAME)
@Component
public class UsernameHandler extends AbstractHandler {


    @Autowired
    public UsernameHandler(StateRepository stateRepository) {
        super(stateRepository);
    }

    @Override
    public void handle(AbsSender absSender, Message message) throws TelegramApiException {
        log.info(this.getClass().getSimpleName() + " called");
        Long chatId = message.getChat().getId();
        if (isMessageWithText(message)) {
            handle(absSender, message, chatId);
        } else {
            response(DO_NOT_UNDERSTAND, chatId, absSender);
        }
    }

    private void handle(AbsSender absSender, Message message, Long chatId) throws TelegramApiException {
        String userName = message.getText();
        CvApplicationRequest cvApplicationRequest = CvApplicationRequest
                .builder()
                .fullName(userName)
                .telegramUsername(getUserName(message))
                .build();
        var state = State.builder()
                .stateId(StateOrder.PROFESSION.getOrder())
                .userId(chatId)
                .stateData(cvApplicationRequest).build();
        stateRepository.save(state);
        response(START_COMMAND_PROFESSION, chatId, absSender);
    }


    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

}

