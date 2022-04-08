package com.olim.cvhelper.bot.commands;

import com.olim.cvhelper.bot.util.TextConstants;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class HelpCommand implements IBotCommand {

    @SneakyThrows
    @Override
    public void processMessage(AbsSender absSender, Message message, String[] arguments) {
        Chat chat = message.getChat();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(TextConstants.HELP_MESSAGE.getText());
        sendMessage.setParseMode(TextConstants.HELP_MESSAGE.getParseMode());
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chat.getId().toString());
        absSender.execute(sendMessage);
    }

    @Override
    public String getCommandIdentifier() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Помощь";
    }
}
