package org.app.krasiva24bot.app;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@Component
public class FileReader {
    public String readFileToString(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) return "Файл не найден";
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при чтении файла";
        }
    }
}