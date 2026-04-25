package com.voicebot;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    private final Voice voice;

    public TextToSpeech() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.setRate(150);
        voice.setPitch(100);
        voice.setVolume(3);
    }

    public void speak(String text) {
        // Run in new thread so it doesn't block listening
        new Thread(() -> {
            voice.speak(text);
        }).start();
    }
}