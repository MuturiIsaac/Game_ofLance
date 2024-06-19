package com.game.lanceofdestiny;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    public SoundEffect(String soundFileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();
        }
    }
}

