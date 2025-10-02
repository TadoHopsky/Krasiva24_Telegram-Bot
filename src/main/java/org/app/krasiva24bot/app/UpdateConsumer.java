package org.app.krasiva24bot.app;

import lombok.SneakyThrows;
import org.app.krasiva24bot.app.Configuration.TelegramBotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;


@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final FileReader fileReader;

    public UpdateConsumer(FileReader fileReader, TelegramBotConfig telegramBotConfig) {
        this.fileReader = fileReader;
        this.telegramClient = new OkHttpTelegramClient(telegramBotConfig.getBotToken());
    }

    @SneakyThrows
    @Override
    public void consume(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if(message.equals("/start")){
                sendMainMenu(chatId);
            }else if(message.equals("⬅ Назад")){
                sendMainMenu(chatId);
            }
            else {
                sendMessage(chatId, "Такой команды не существует :(");
            }
        }else if(update.hasCallbackQuery()) {
            handleCallbackQuery(update.getCallbackQuery());
        }
    }

    public void handleCallbackQuery(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getFrom().getId();
        var data = callbackQuery.getData();

        switch (data) {
            case "schedule" -> sendSchedule(chatId);
            case "price" -> sendPrice(chatId);
            case "discounts" -> sendDiscounts(chatId);
            default -> sendMessage(chatId, "Неизвестная команда");
        }
    }

    @SneakyThrows
    private void sendSchedule(Long chatId) {
        String FILE_PATH_SCHEDULE = "textResources/Schedule.txt";
        String textFromFileSchedule = fileReader.readFileToString(FILE_PATH_SCHEDULE);
        backButtonHandler(chatId, textFromFileSchedule);
    }

    @SneakyThrows
    private void sendPrice(Long chatId) {
        String FILE_PATH_PRICE = "textResources/Prices.txt";
        String textFromFilePrice = fileReader.readFileToString(FILE_PATH_PRICE);
        backButtonHandler(chatId, textFromFilePrice);
    }

    @SneakyThrows
    private void sendDiscounts(Long chatId) {
        String FILE_PATH_DISCOUNTS = "textResources/Discounts.txt";
        String textFromFileDiscounts = fileReader.readFileToString(FILE_PATH_DISCOUNTS);
        backButtonHandler(chatId, textFromFileDiscounts);
    }

    @SneakyThrows
    private void sendMessage(Long chatId, String messageText) {
        SendMessage sendMessage = SendMessage.builder()
                .text(messageText)
                .chatId(chatId)
                .build();
        telegramClient.execute(sendMessage);
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

    @SneakyThrows
    private void backButtonHandler(Long chatId, String textFromFile) {
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
                .text(textFromFile)
                .replyMarkup(keyboardMarkup)
                .build();

        telegramClient.execute(sendMessage);
    }
}
