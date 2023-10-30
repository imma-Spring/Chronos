package com.chronos.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.io.ByteArrayInputStream;

/**
 * Represents a sound clip that can be played.
 */
public class SoundClip {
    private final Clip clip;  // The audio clip
    private final FloatControl gainControl;  // Control for adjusting volume

    /**
     * Constructs a SoundClip object.
     *
     * @param path The path to the audio file.
     */
    public SoundClip(String path) {
        try {
            // Load the audio file and prepare it for playback
            InputStream audioSrc = SoundClip.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(Objects.requireNonNull(audioSrc));
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);

            // Open the clip for playback
            clip = AudioSystem.getClip();
            clip.open(dais);

            // Get the gain control for volume adjustment
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Plays the sound clip.
     */
    public void play() {
        if (clip == null)
            return;

        // Stop any ongoing playback and set frame position to beginning
        stop();
        clip.setFramePosition(0);
        while (!clip.isRunning()) {
            clip.start();
        }
    }

    /**
     * Stops the playback of the sound clip.
     */
    public void stop() {
        if (clip.isRunning())
            clip.stop();
    }

    /**
     * Closes the sound clip, stopping playback and releasing resources.
     */
    public void close() {
        stop();
        clip.drain();
        clip.close();
    }

    /**
     * Loops the sound clip continuously.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        play();
    }

    /**
     * Sets the volume of the sound clip.
     *
     * @param volume The volume level.
     */
    public void setVolume(float volume) {
        gainControl.setValue(volume);
    }

    /**
     * Checks if the sound clip is currently running (playing).
     *
     * @return True if the sound clip is running, false otherwise.
     */
    public boolean isRunning() {
        return clip.isRunning();
    }
}
