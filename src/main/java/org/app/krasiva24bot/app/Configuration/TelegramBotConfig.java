package org.app.krasiva24bot.app.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

    public String getBotToken() {
        return botToken;
    }
}
