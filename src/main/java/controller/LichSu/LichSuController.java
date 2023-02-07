package controller.LichSu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;

import utility.SoundClick;
public class LichSuController {
    @FXML
    private Pane mainPane;
    public static int option ;//lựa chọn loại lịch sử hd nào

    SoundClick SoundClick = new SoundClick();
    public void nhanKhauModeClick(MouseEvent mouseEvent) throws IOException {
        option = 1;
        SoundClick.playSound();
        Pane nhanKhauPane = FXMLLoader.load(getClass().getResource("/view/lichSu/chiTietLichSu.fxml"));
        mainPane.getChildren().add(nhanKhauPane);
    }

    public void hoKhauModeClick(MouseEvent mouseEvent) throws IOException {
        option = 2;
        SoundClick.playSound();
        Parent hoKhauPane = FXMLLoader.load(getClass().getResource("/view/lichSu/chiTietLichSu.fxml"));
        mainPane.getChildren().add(hoKhauPane);
    }



    public void phanThuongModeClick(MouseEvent mouseEvent) throws IOException {

        SoundClick.playSound();
        Pane phanThuongPane = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSuChon.fxml"));
        mainPane.getChildren().add(phanThuongPane);
    }
}
