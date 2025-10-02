package org.app.krasiva24bot.app.Service;

import lombok.SneakyThrows;
import org.app.krasiva24bot.app.Configuration.TelegramBotConfig;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class MessageService {

    private final TelegramClient telegramClient;

    public MessageService( TelegramBotConfig telegramBotConfig) {
        this.telegramClient = new OkHttpTelegramClient(telegramBotConfig.getBotToken());
    }

    @SneakyThrows
    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
        telegramClient.execute(sendMessage);
    }
}
