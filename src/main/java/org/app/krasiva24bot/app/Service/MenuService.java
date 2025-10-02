package org.app.krasiva24bot.app.Service;

import lombok.SneakyThrows;
import org.app.krasiva24bot.app.Configuration.TelegramBotConfig;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MessageService messageService;
    private final FileService fileService;
    private final TelegramClient telegramClient;


    public MenuService(MessageService messageService, FileService fileService, TelegramBotConfig telegramBotConfig) {
        this.messageService = messageService;
        this.fileService = fileService;
        this.telegramClient = new OkHttpTelegramClient(telegramBotConfig.getBotToken());
    }

    @SneakyThrows
    public void sendMainMenu(Long chatId) {
        SendMessage sendMessage = SendMessage.builder()
                .text("Добро пожаловать, выберите действие: ")
                .chatId(chatId)
                .build();

        var scheduleButton = InlineKeyboardButton.builder()
                .text("Расписание")
                .callbackData("schedule")
                .build();

        var priceButton = InlineKeyboardButton.builder()
                .text("Прайс")
                .callbackData("price")
                .build();

        var discountsButton = InlineKeyboardButton.builder()
                .text("Скидки (Кому и когда)")
                .callbackData("discounts")
                .build();

        List<InlineKeyboardRow> buttons = List.of(
                new InlineKeyboardRow(scheduleButton),
                new InlineKeyboardRow(priceButton),
                new InlineKeyboardRow(discountsButton)
        );

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(buttons);

        sendMessage.setReplyMarkup(markup);
        telegramClient.execute(sendMessage);

    }

    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getFrom().getId();
        String data = callbackQuery.getData();

        switch (data) {
            case "schedule" -> sendFileContent(chatId, "textResources/Schedule.txt");
            case "price" -> sendFileContent(chatId, "textResources/Prices.txt");
            case "discounts" -> sendFileContent(chatId, "textResources/Discounts.txt");
            default -> messageService.sendMessage(chatId, "Неизвестная команда");
        }
    }

    @SneakyThrows
    private void sendFileContent(Long chatId, String filePath) {
        String content = fileService.readFile(filePath);
        messageService.sendMessage(chatId, content);

        KeyboardButton backButton = new KeyboardButton("⬅ Назад");
        KeyboardRow row = new KeyboardRow();
        row.add(backButton);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);

        ReplyKeyboardMarkup keyboardMarkup = ReplyKeyboardMarkup.builder()
                .keyboard(keyboard)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();

        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(content)
                .replyMarkup(keyboardMarkup)
                .build();

        telegramClient.execute(sendMessage);
    }
}
