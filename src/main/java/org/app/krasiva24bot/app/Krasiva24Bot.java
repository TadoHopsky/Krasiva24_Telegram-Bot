package org.app.krasiva24bot.app;

import org.app.krasiva24bot.app.Configuration.TelegramBotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;


@Component
public class Krasiva24Bot implements SpringLongPollingBot {

    private final UpdateConsumer updateConsumer;
    private final TelegramBotConfig telegramBotConfig;

    public Krasiva24Bot(UpdateConsumer updateConsumer, TelegramBotConfig telegramBotConfig) {
        this.updateConsumer = updateConsumer;
        this.telegramBotConfig = telegramBotConfig;
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getBotToken();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return updateConsumer;
    }
}
