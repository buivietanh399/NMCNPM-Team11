package utility;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class SoundLatLich {
    public void playSound(){
        URL resource = getClass().getResource("/sound/latLich.mp3");
        if (resource != null) {
            //System.out.println("sound!");
            AudioClip sound = new AudioClip(resource.toString());
            sound.play();
        }
    }
}
