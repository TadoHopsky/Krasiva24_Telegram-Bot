package org.app.krasiva24bot.app;

import org.app.krasiva24bot.app.Service.MenuService;
import org.app.krasiva24bot.app.Service.MessageService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {
    private final MenuService menuService;
    private final MessageService messageService;

    public UpdateConsumer(MenuService menuService, MessageService messageService) {
        this.menuService = menuService;
        this.messageService = messageService;
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (message.equals("/start") || message.equals("⬅ Назад")) {
                menuService.sendMainMenu(chatId);
            } else {
                messageService.sendMessage(chatId, "Такой команды не существует :(");
            }
        } else if (update.hasCallbackQuery()) {
            menuService.handleCallbackQuery(update.getCallbackQuery());
        }
    }
}
