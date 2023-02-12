package controller;

import controller.nhanKhau.ThongTinNhanKhauController;
import entity.NhanKhau;
import entity.NguoiDung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import view.Main;
import utility.SoundClick;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManHinhChinhController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane tieuDePane;

    @FXML
    private Label tieuDeText;

    @FXML
    private Button trangChuButton;

    @FXML
    private Button nhanKhauButton;

    @FXML
    private Button hoKhauButton;

    @FXML
    private Button phanThuongButton;

    @FXML
    private Button thongKeButton;

    @FXML
    private Button lichSuButton;

    @FXML
    private Button dangXuatButton;

    @FXML
    private Button taiKhoanButton;



    @FXML
    private Pane mainPane;
    SoundClick SoundClick = new SoundClick();

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void resetButtonBackground() {
        trangChuButton.setStyle("-fx-background-color: #00008B;");
        nhanKhauButton.setStyle("-fx-background-color: #00008B;");
        hoKhauButton.setStyle("-fx-background-color: #00008B;");
        phanThuongButton.setStyle("-fx-background-color: #00008B;");
        thongKeButton.setStyle("-fx-background-color: #00008B;");
        lichSuButton.setStyle("-fx-background-color: #00008B;");

    }




    public void trangChuButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        trangChuButton.setStyle("-fx-background-color: #FFA07A;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void nhanKhauButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        nhanKhauButton.setStyle("-fx-background-color: #FFA07A;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/nhanKhau/nhanKhau.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void hoKhauButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        hoKhauButton.setStyle("-fx-background-color: #FFA07A;");

        Pane trangChuPane =  FXMLLoader.load(getClass().getResource("/view/hoKhau/hoKhau.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void phanThuongButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        phanThuongButton.setStyle("-fx-background-color: #FFA07A;");

        Parent trangChuPane =  FXMLLoader.load(getClass().getResource("/view/phanThuong/phanThuong.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void thongKeButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        thongKeButton.setStyle("-fx-background-color: #FFA07A;");

        Parent trangChuPane =  FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void lichSuButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        resetButtonBackground();
        lichSuButton.setStyle("-fx-background-color: #FFA07A;");

        Parent trangChuPane =  FXMLLoader.load(getClass().getResource("/view/lichSu/lichSu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }



    public void dangXuatButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();

        Stage stage = (Stage) dangXuatButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dangNhap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(500);
        stage.setHeight(350);
        stage.show();
    }

    public void taiKhoanButtonOnAction(ActionEvent event) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/taiKhoanNguoiDung.fxml"));
        Parent thongTinND = loader.load();
        TaiKhoanController controller= loader.getController();
        controller.setNguoiDung();

        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thông tin nguoi dung");
        Scene scene = new Scene(thongTinND);
        stage.setScene(scene);
        stage.show();
    }

    //events for mouse: change color
    public void dangXuatButtonMouseEntered() throws IOException{
        dangXuatButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dangXuatButton.setStyle("-fx-background-color: red; -fx-background-radius: 20;");
            }
        });
    }

    public void dangXuatButtonMouseExited() throws IOException{
        //change color
        dangXuatButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dangXuatButton.setStyle("-fx-background-color: #6b91fb;-fx-background-radius: 20;");
            }
        });
    }
}
