package com.voicebot;

import java.awt.Desktop;
import java.net.URI;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CommandHandler {
    private final TextToSpeech tts;
    private final String[] jokes = {
        "Why do Java developers wear glasses? Because they can't C#.",
        "I would tell you a UDP joke, but you might not get it.",
        "Why was the JavaScript developer sad? Because he didn't Node how to Express himself."
    };

    public CommandHandler(TextToSpeech tts) {
        this.tts = tts;
    }

    public void execute(String command) {
        command = command.toLowerCase().trim();

        try {
            if (command.contains("notepad")) {
                Runtime.getRuntime().exec("notepad.exe");
                tts.speak("Opening notepad");
            }
            else if (command.contains("calculator")) {
                Runtime.getRuntime().exec("calc.exe");
                tts.speak("Opening calculator");
            }
            else if (command.contains("time")) {
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a"));
                tts.speak("The time is " + time);
            }
            else if (command.contains("google")) {
                Desktop.getDesktop().browse(new URI("https://google.com"));
                tts.speak("Opening Google");
            }
            else if (command.contains("joke")) {
                int i = (int) (Math.random() * jokes.length);
                tts.speak(jokes[i]);
            }
            else if (command.contains("exit") || command.contains("stop")) {
                tts.speak("Goodbye");
                Thread.sleep(1000);
                System.exit(0);
            }
            else {
                tts.speak("Sorry, I did not understand that command");
            }
        } catch (Exception e) {
            tts.speak("Error executing command");
            e.printStackTrace();
        }
    }
}