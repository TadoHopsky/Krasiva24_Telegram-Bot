
# 🤖 Krasiva24 Telegram Bot

Добро пожаловать в **Krasiva24 Telegram Bot** — умного помощника для клиентов и администраторов студии танцев!  
Бот автоматизирует ответы на частые вопросы, предоставляет расписание, прайс-листы и информацию о скидках.

---

## 🚀 Быстрый старт

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/yourusername/krasiva24-bot.git
   cd krasiva24-bot
   ```
2. **Настройте переменные окружения:**
   - В `application.properties` добавьте ваш токен Telegram-бота:
     ```
     telegram.bot.token=ВАШ_ТОКЕН
     ```
3. **Запустите приложение:**
   ```bash
   ./mvnw spring-boot:run
   ```
   или
   ```bash
   mvn spring-boot:run
   ```

---

## ✨ Функциональность

- **/start** — приветствие и главное меню.
- **Главное меню** (инлайн-кнопки):
  - 📅 **Расписание** — актуальное расписание работы.
  - 💸 **Прайс** — полный прайс-лист услуг.
  - 🎁 **Скидки** — информация о скидках и специальных предложениях.
- **⬅ Назад** — возвращение в главное меню.
- **Обработка неизвестных команд** — дружелюбное сообщение об ошибке.

---

## 🛠️ Структура проекта

```
krasiva24-bot/
├── src/
│   └── main/
│       ├── java/org/app/krasiva24bot/
│       │   ├── Krasiva24BotApplication.java
│       │   └── app/
│       │       ├── Krasiva24Bot.java
│       │       ├── UpdateConsumer.java
│       │       ├── FileReader.java
│       │       └── Configuration/
│       │           └── TelegramBotConfig.java
│       └── resources/
│           └── textResources/
│               ├── Schedule.txt
│               ├── Prices.txt
│               └── Discounts.txt
├── README.md
└── ...
```

---

## ⚙️ Технологии

- Java 17+
- Spring Boot
- [TelegramBots](https://github.com/rubenlagus/TelegramBots) (Spring Starter)
- Maven

---

## 📄 Как добавить/изменить информацию

- **Расписание:** `src/main/resources/textResources/Schedule.txt`
- **Прайс:** `src/main/resources/textResources/Prices.txt`
- **Скидки:** `src/main/resources/textResources/Discounts.txt`

Измените содержимое файлов — бот автоматически покажет обновлённую информацию.

---

## 💡 Советы

- Не публикуйте токен бота в публичных репозиториях!
- Для деплоя используйте VPS или облачные платформы с поддержкой Java.

---

## 📝 Лицензия

MIT License

---

**Сделано с ❤️ для студии танцев Krasiva24**
