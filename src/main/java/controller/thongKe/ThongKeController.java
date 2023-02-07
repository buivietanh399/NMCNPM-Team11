package controller.thongKe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;

import utility.SoundClick;

public class ThongKeController {

    @FXML
    private Pane mainPane;

    SoundClick SoundClick = new SoundClick();
    public void hoKhauModeClick(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        Parent hoKhauPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeHoKhau.fxml"));
        mainPane.getChildren().add(hoKhauPane);
    }

    public void nhanKhauModeClick(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        Pane nhanKhauPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeNhanKhau.fxml"));
        mainPane.getChildren().add(nhanKhauPane);
    }

    public void phanThuongModeClick(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        Pane phanThuongPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        mainPane.getChildren().add(phanThuongPane);
    }
}

