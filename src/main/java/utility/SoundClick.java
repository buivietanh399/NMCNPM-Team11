package utility;
import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundClick {
    public void playSound(){
        URL resource = getClass().getResource("/sound/clicked.mp3");
        if (resource != null) {
            //System.out.println("sound!");
            AudioClip sound = new AudioClip(resource.toString());
            sound.play();
        }
    }
}
