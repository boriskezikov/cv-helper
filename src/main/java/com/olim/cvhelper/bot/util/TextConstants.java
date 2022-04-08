package com.olim.cvhelper.bot.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.ParseMode;

@Getter
@RequiredArgsConstructor
public enum TextConstants {


    START_COMMAND_USERNAME("Привет\uD83D\uDE43 Я помогу провести валидацию твоего резюме. Как тебя зовут?", ParseMode.HTML),
    START_COMMAND_LINKEDIN("Приятно познакомиться\uD83D\uDE48\n\nА тебе удалось посмотреть наш гайд по составлению профиля в LinkedIn?  " +
            "Если нет, переходи по [ссылке] (https://glib-suit-0c7.notion.site/LinkedIn-06d38889e47640069e7e17090e2910ad)\n" +
            "Убедись, что у тебя все красиво\uD83D\uDCA5 и присылай ссылку на свой профиль! ", ParseMode.MARKDOWNV2),
    START_COMMAND_CV_LINK("Теперь задание! Загрузи свое физическое CV на любой файлообменник и пришли мне ссылку на него\uD83D\uDE4C", ParseMode.HTML),
    WAIT_FOR_HELP("Все готово✅\n Я создал заявку, наши ребята очень скоро с тобой свяжутся и дадут свой рекомендации!", ParseMode.HTML),

    DO_NOT_UNDERSTAND("Не понял о чем ты. Давай еще раз!", ParseMode.HTML),
    INVALID_LINKEDIN_LINK_ERROR_MESSAGE("Пришли мне рабочую ссылку на свой LinkedIn. Похоже то, что ты мне отправил - не валидно\uD83D\uDC40", ParseMode.HTML),

    HELP_MESSAGE("Я помогу сделать ревью твоего резюме! Мы тестируем функциональность системы для достижения лучшего результата и будем благодарны отзывам\\!\n" +
            "Если что\\-то идет не так, пиши @kezik\\_off, он прикрутит костыль\uD83D\uDE09" +
            "\n\nТвой Стартап Нейшен\uD83D\uDE09", ParseMode.MARKDOWNV2);

    private final String text;
    private final String parseMode;

}
