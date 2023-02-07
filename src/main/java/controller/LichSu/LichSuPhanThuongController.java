package controller.LichSu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import utility.SoundClick;

import java.io.IOException;

public class LichSuPhanThuongController {
    @FXML
    private Pane mainPane;

    @FXML
    private Pane tenDipPane;
    public void hocSinhGioiClick() throws IOException {
        LichSuController.option = 3;
        SoundClick.playSound();
        Pane hocSinhGioiPane = FXMLLoader.load(getClass().getResource("/view/lichSu/chiTietLichSu.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(hocSinhGioiPane);
    }

    public void dipDacBietClick() throws IOException {
        LichSuController.option = 4;
        SoundClick.playSound();
        Pane dipDacBietPane = FXMLLoader.load(getClass().getResource("/view/lichSu/chiTietLichSu.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(dipDacBietPane);
    }

    utility.SoundClick SoundClick = new SoundClick();

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSu.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
