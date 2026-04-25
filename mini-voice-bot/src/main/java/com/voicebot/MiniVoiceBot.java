package com.voicebot;

public class MiniVoiceBot {
    public static void main(String[] args) {
        TextToSpeech tts = new TextToSpeech();
        tts.speak("Voice bot started. I am listening.");

        try {
            SpeechRecognizer recognizer = new SpeechRecognizer("model/vosk-model-small-en-us-0.15");
            CommandHandler handler = new CommandHandler(tts);

            System.out.println("Say commands like: open notepad, what time, tell joke, exit");
            recognizer.listen(text -> {
                System.out.println("Heard: " + text);
                handler.execute(text);
            });

        } catch (Exception e) {
            System.err.println("Error starting bot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}