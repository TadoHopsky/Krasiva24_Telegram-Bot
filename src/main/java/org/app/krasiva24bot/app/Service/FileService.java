package org.app.krasiva24bot.app.Service;

import lombok.RequiredArgsConstructor;
import org.app.krasiva24bot.app.FileReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileReader fileReader;

    public String readFile(String filePath) {
        return fileReader.readFileToString(filePath);
    }
}
