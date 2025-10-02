package org.app.krasiva24bot.app.Configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    private String botToken;

}
