package controller.thongKe;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HoKhauRepository;
import repository.HoKhauRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utility.SoundClick;

public class ThongKeHoKhauController implements Initializable {

    @FXML
    private Pane mainPane;



    @FXML
    private ImageView soThanhVienImageView ;

    @FXML
    private ImageView  trangThaiCuTruImageView ;

    HoKhauRepository hoKhauRepository = new HoKhauRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    SoundClick SoundClick = new SoundClick();

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }

    public void trangThaiCuTruImageViewMouseClicked(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongKe/thongKeHoKhau_trangThaiCuTru.fxml"));
        Parent thongKeHoKhau_trangThaiCuTru = loader.load();
        ThongKeHoKhau_trangThaiCuTruController controller= loader.getController();
        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thong ke theo trang thai cu tru");
        Scene scene = new Scene(thongKeHoKhau_trangThaiCuTru);
        stage.setScene(scene);
        stage.show();
    }

    public void soThanhVienImageViewMouseClicked(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongKe/thongKeHoKhau_soThanhVien.fxml"));
        Parent thongKeHoKhau_soThanhVien= loader.load();
        ThongKeHoKhau_soThanhVienController controller= loader.getController();
        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thong ke theo so thanh viên");
        Scene scene = new Scene(thongKeHoKhau_soThanhVien);
        stage.setScene(scene);
        stage.show();
    }
}
