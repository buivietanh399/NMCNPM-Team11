package controller.thongKe;

import entity.VO.NamSoDipVO;
import entity.VO.TenDipVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import lombok.SneakyThrows;
import repository.PhanThuongRepository;
import repository.PhanThuongRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import utility.SoundClick;

public class ThongKePhanThuongController implements Initializable  {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane tenDipPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void hocSinhGioiClick() throws IOException {
        SoundClick.playSound();
        Pane hocSinhGioiPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeHocSinhGioi.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(hocSinhGioiPane);
    }

    public void dipDacBietClick() throws IOException {
        SoundClick.playSound();
        Pane dipDacBietPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeDipDacBiet.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(dipDacBietPane);
    }

    SoundClick SoundClick = new SoundClick();

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
