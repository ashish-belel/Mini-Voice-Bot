# Mini Voice Bot - Java

Offline voice assistant built in Java using Vosk + FreeTTS.

### Features
- Speech-to-Text using Vosk API - 100% offline
- Text-to-Speech using FreeTTS
- Supports commands: open notepad, calculator, time, google, tell joke, exit
- No API keys needed

### Setup
1. Download Vosk model: vosk-model-small-en-us-0.15 from https://alphacephei.com/vosk/models
2. Extract to model/ folder
3. Run: mvn compile exec:java

### Tech Stack
Java 17, Maven, Vosk API, FreeTTS, Java Sound API

### Demo Commands
"open notepad" | "what time is it" | "tell me a joke" | "open google" | "exit"
### Setup Steps
1. git clone this structure
2. Download model from https://alphacephei.com/vosk/models → vosk-model-small-en-us-0.15.zip ∼50MB
3. Extract into model/ folder
4. mvn compile exec:java
5. Start talking