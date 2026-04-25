package com.voicebot;

import org.vosk.*;
import javax.sound.sampled.*;
import java.util.function.Consumer;

public class SpeechRecognizer {
    private final Recognizer recognizer;
    private final TargetDataLine microphone;

    public SpeechRecognizer(String modelPath) throws Exception {
        LibVosk.setLogLevel(LogLevel.WARNINGS);
        Model model = new Model(modelPath);
        recognizer = new Recognizer(model, 16000.0f);

        AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        microphone = (TargetDataLine) AudioSystem.getLine(info);
        microphone.open(format);
        microphone.start();
    }

    public void listen(Consumer<String> onResult) {
        byte[] buffer = new byte[4096];
        while (!Thread.interrupted()) {
            int bytesRead = microphone.read(buffer, 0, buffer.length);
            if (recognizer.acceptWaveForm(buffer, bytesRead)) {
                String result = recognizer.getResult();
                String text = extractText(result);
                if (!text.isBlank()) {
                    onResult.accept(text);
                }
            }
        }
    }

    private String extractText(String json) {
        // Vosk returns: {"text" : "what you said"}
        int start = json.indexOf("\"text\" : \"") + 10;
        int end = json.lastIndexOf("\"");
        if (start > 9 && end > start) {
            return json.substring(start, end);
        }
        return "";
    }
}