
# 💃 Krasiva24 Telegram Bot

> **Интеллектуальный Telegram-бот для студии танцев Krasiva24**  
> Автоматизация общения, расписание, прайс и акции — всё для удобства клиентов и администраторов!

---

## 📦 Возможности

- **/start** — приветствие и главное меню
- **Главное меню** (инлайн-кнопки):
  - 📅 **Расписание** — актуальное расписание занятий
  - 💸 **Прайс** — полный прайс-лист услуг
  - 🎁 **Скидки** — информация о действующих акциях
- ⬅ **Назад** — возврат в главное меню
- Обработка неизвестных команд с дружелюбным сообщением

---

## 🚀 Быстрый старт

1. **Клонируйте репозиторий:**
   ```bash
   git clone https://github.com/yourusername/krasiva24-bot.git
   cd krasiva24-bot
   ```
2. **Настройте переменные окружения:**
   - В `src/main/resources/application.properties` добавьте:
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

## 🗂️ Структура проекта

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
│           ├── application.properties
│           └── textResources/
│               ├── Schedule.txt
│               ├── Prices.txt
│               └── Discounts.txt
├── README.md
└── ...
```

---

## 📝 Как обновить информацию

- **Расписание:** `src/main/resources/textResources/Schedule.txt`
- **Прайс:** `src/main/resources/textResources/Prices.txt`
- **Скидки:** `src/main/resources/textResources/Discounts.txt`

Измените содержимое файлов — бот автоматически покажет обновлённые данные.

---

## ⚙️ Технологии

- Java 17+
- Spring Boot
- [TelegramBots Spring Starter](https://github.com/rubenlagus/TelegramBots)
- Maven

---

## 💡 Советы

- Никогда не публикуйте токен бота в открытом доступе!
- Для продакшн-использования рекомендуем VPS или облако с поддержкой Java.

---

## 🏷️ Лицензия

MIT License

---

**Сделано с ❤️ для студии танцев Krasiva24**

