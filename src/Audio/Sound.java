package Audio;

import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Sound {
    
    public static File playerShooting = new File("PlayerShootingSound.WAV");
    public static File musicLoop = new File("MusicLoop.WAV");
    public static File powerup = new File("powerup.WAV");
    
    public static void playSound(File sound) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playMusic() throws Exception{        
        try {
            AudioData data = new AudioStream(new FileInputStream("laser8.WAV")).getData();
            ContinuousAudioDataStream BGM = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(BGM);      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
