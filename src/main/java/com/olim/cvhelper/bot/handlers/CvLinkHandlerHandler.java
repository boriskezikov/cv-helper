package com.olim.cvhelper.bot.handlers;

import com.olim.cvhelper.backoffice.entity.CvApplication;
import com.olim.cvhelper.backoffice.entity.CvApplicationStatus;
import com.olim.cvhelper.backoffice.service.CvApplicationService;
import com.olim.cvhelper.backoffice.service.UserService;
import com.olim.cvhelper.bot.config.BotState;
import static com.olim.cvhelper.bot.util.TextConstants.DO_NOT_UNDERSTAND;
import static com.olim.cvhelper.bot.util.TextConstants.WAIT_FOR_HELP;
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
@BotState(orderId = StateOrder.CV_LINK)
@Component
public class CvLinkHandlerHandler extends AbstractHandler {

    private final CvApplicationService cvApplicationService;
    private final UserService userService;


    protected CvLinkHandlerHandler(StateRepository stateRepository, CvApplicationService cvApplicationService, UserService userService) {
        super(stateRepository);
        this.cvApplicationService = cvApplicationService;
        this.userService = userService;
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
            state.setStateId(StateOrder.WAIT_FOR_HELP.getOrder());
            State finalState = stateRepository.save(state);
            CvApplication cvApplication = toCvApplication(chatId, finalState);
            cvApplicationService.update(cvApplication);
            response(WAIT_FOR_HELP, chatId, absSender);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private CvApplication toCvApplication(Long chatId, State finalState) {
        return CvApplication.builder()
                .cvLink(finalState.getStateData().getCvLink())
                .linkedInLink(finalState.getStateData().getLinkedInLink())
                .chatId(chatId)
                .fullName(finalState.getStateData().getFullName())
                .assignee(userService.pickRandom())
                .telegramUsername(finalState.getStateData().getTelegramUsername())
                .status(CvApplicationStatus.OPEN).build();
    }
}