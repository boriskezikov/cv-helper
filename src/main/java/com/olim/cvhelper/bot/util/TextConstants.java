package com.olim.cvhelper.bot.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.ParseMode;

@Getter
@RequiredArgsConstructor
public enum TextConstants {


    START_COMMAND_USERNAME("Давай начнем\uD83D\uDE43 Как тебя зовут?", ParseMode.HTML),

    START_COMMAND_PROFESSION("Опиши род своей деятельности!\nНапример, дизайнер-интерфейсов, back-end разработчик и тд.", ParseMode.HTML),

    START_COMMAND_LINKEDIN("Приятно познакомиться\uD83D\uDE48\n\nА тебе удалось посмотреть наш гайд по составлению профиля в LinkedIn?  " +
            "Если нет, переходи по [ссылке](https://glib-suit-0c7.notion.site/LinkedIn-06d38889e47640069e7e17090e2910ad)\n" +
            "Убедись, что у тебя все красиво\uD83D\uDCA5 и присылай ссылку на свой профиль! ", ParseMode.MARKDOWNV2),

    START_COMMAND_CV_LINK("Теперь задание! Загрузи свое физическое CV на любой файлообменник и пришли мне ссылку на него." +
            "Так-же просим ознакомиться с нашей инструкцией по составлению [резюме](https://tsssvet.notion.site/CV-b412a542ef6a40939e28c52e006b9301)." +
            "\n\n✅Отправляя нам свое резюме ты подтверждаешь, что ознакомился с нашими гайдлайнами и применил их, а так-же даешь согласие на обработку персональных данных.", ParseMode.HTML),

    START_COMMAND_QUESTION("Опиши свой вопрос или какая помощь тебе требуется в свободном формате", ParseMode.HTML),

    WAIT_FOR_HELP("Все готово✅\nЯ создал заявку, наши ребята очень скоро с тобой свяжутся и дадут свой рекомендации!", ParseMode.HTML),

    DO_NOT_UNDERSTAND("Не понял о чем ты. Давай еще раз!", ParseMode.HTML),

    INVALID_LINKEDIN_LINK_ERROR_MESSAGE("Пришли мне рабочую ссылку на свой LinkedIn. Похоже то, что ты мне отправил - не валидно\uD83D\uDC40", ParseMode.HTML),

    HELP_MESSAGE("Я помогу сделать ревью твоего резюме! Мы тестируем функциональность системы для достижения лучшего результата и будем благодарны отзывам!\n" +
            "Если что-то идет не так, пиши @kezik\\_off, он прикрутит костыль\uD83D\uDE09" +
            "\n\nТвой Стартап Нейшен\uD83D\uDE09", ParseMode.MARKDOWNV2),

    APPLICATION_FINISHED_MESSAGE("Заявка закрыта! Надеемся наша помощь была полезна. Если возникнут новые вопросы - мы рады помочь!" +
            "\n\nТвой Стартап Нейшен\uD83D\uDE09", ParseMode.HTML);

    private final String text;
    private final String parseMode;


}
