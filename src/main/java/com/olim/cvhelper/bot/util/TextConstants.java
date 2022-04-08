package com.olim.cvhelper.bot.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.ParseMode;

@Getter
@RequiredArgsConstructor
public enum TextConstants {


    START_COMMAND_USERNAME("Привет! Я помогу провести валидацию твоего резюме. Как тебя зовут?", ParseMode.HTML),
    START_COMMAND_LINKEDIN("Приятно познакомиться! Пришли мне ссылку на свой LinkedIn.", ParseMode.HTML),
    START_COMMAND_CV_LINK("Теперь задание к тебе. Загрузи свое физическое CV на любой файлообменник и пришли мне ссылку на него." +
            "Наши ребята посмотрят и пришлют свои рекомендации!", ParseMode.HTML),
    WAIT_FOR_HELP(" Все готово! Я создал заявку, наши ребята свяжутся с тобой очень скоро и дадут советы!", ParseMode.HTML),

    DO_NOT_UNDERSTAND("Не понял о чем ты. Давай еще раз!", ParseMode.HTML),
    INVALID_LINKEDIN_LINK_ERROR_MESSAGE("Похоже ссылка не корректна и/или такой профиль не существует", ParseMode.HTML);


    private final String text;
    private final String parseMode;

}
