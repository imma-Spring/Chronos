package com.chronos.audio;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class Microphone {
    /**
     * Captures audio from the microphone and returns it as a SoundClip.
     *
     * @param durationInSeconds The duration (in seconds) to capture audio.
     * @return A SoundClip object containing the captured audio.
     */
    public static SoundClip captureSoundClipFromMicrophone(double durationInSeconds) {
        try {
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                return null;
            }

            TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            System.out.println("Microphone capturing...");

            int bufferSize = (int) (format.getFrameSize() * format.getFrameRate() * durationInSeconds);
            byte[] buffer = new byte[bufferSize];
            line.read(buffer, 0, buffer.length);

            line.stop();
            line.close();

            ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
            return new SoundClip(inputStream);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

}
