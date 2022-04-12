package com.olim.cvhelper.bot.listener;

import com.olim.cvhelper.backoffice.events.ApplicationFinished;
import com.olim.cvhelper.bot.repository.StateRepository;
import com.olim.cvhelper.bot.service.CvApplicationBot;
import static com.olim.cvhelper.bot.util.TextConstants.APPLICATION_FINISHED_MESSAGE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationFinishedMessageListener implements ApplicationListener<ApplicationFinished> {

    private final CvApplicationBot bot;
    private final StateRepository stateRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationFinished applicationFinished) {
        SendMessage sm = new SendMessage();
        sm.setChatId(applicationFinished.getChatId().toString());
        sm.setText(APPLICATION_FINISHED_MESSAGE.getText());
        sm.setParseMode(APPLICATION_FINISHED_MESSAGE.getParseMode());
        try {
            bot.execute(sm);
            log.debug("sendMessage - {} sent", sm);
        } catch (TelegramApiException e) {
            log.error("Error notifying chat_id {}, error_message:{}", sm.getChatId(), e.getMessage());
        }
        if (stateRepository.existsById(applicationFinished.getChatId())) {
            stateRepository.deleteById(applicationFinished.getChatId());
        }
    }
}
