package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import repository.NguoiDungRepository;
import repository.NguoiDungRepositoryImpl;
import utility.Message;
import utility.SoundClick;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import  javafx.event.EventHandler;
import javafx.scene.media.AudioClip;

public class NguoiDungController  implements Initializable {
    @FXML
    private AnchorPane dangNhapPane;


    @FXML
    private Button huyButton;

    @FXML
    private Button dangNhapButton;

    @FXML
    private TextField taiKhoan;

    @FXML
    private PasswordField matKhau;


    @FXML
    private Label thongBaoLabel;

    public void initialize(URL location, ResourceBundle resources) {
        dangNhapPane.setStyle("-fx-background-image: url('https://images.unsplash.com/photo-1530293959042-0aac487c21e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Ymx1ZSUyMGFuZCUyMHdoaXRlfGVufDB8fDB8fA%3D%3D&w=1000&q=80'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;"
        + "-fx-background-color: white");
    }


    static NguoiDungRepository nguoiDungRepository = new NguoiDungRepositoryImpl();

    SoundClick SoundClick = new SoundClick();
    public void huyButtonOnAction(ActionEvent event) {
        //sound
        SoundClick.playSound();
        Stage stage = (Stage) huyButton.getScene().getWindow();
        stage.close();
    }

    public void dangNhapButtonOnAction(ActionEvent event) throws IOException {

        //sound
        SoundClick.playSound();

        //check tai khoan
        if (taiKhoan.getText().isBlank() == false && matKhau.getText().isBlank() == false) {
            if (nguoiDungRepository.dangNhap(taiKhoan.getText(), matKhau.getText())) {
                Stage stage = (Stage) dangNhapButton.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("manHinhChinh.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Quản lý khu phố");
                stage.setScene(scene);
//                stage.setMaximized(true);
                stage.setWidth(1400);
                stage.setHeight(780);
                stage.setX(20);
                stage.setY(20);
                stage.show();
            } else {
                thongBaoLabel.setText(Message.dangNhapThatBai);
                thongBaoLabel.setVisible(true);
            }
        } else {
            thongBaoLabel.setText(Message.dangNhapTrong);
            thongBaoLabel.setVisible(true);
        }
    }

    //events for mouse: change color
    public void dangNhapMouseEntered() throws IOException{
        dangNhapButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dangNhapButton.setStyle("-fx-background-color: red; -fx-background-radius: 20;");
            }
        });
    }

    public void dangNhapMouseExited() throws IOException{
        //change color
        dangNhapButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                dangNhapButton.setStyle("-fx-background-color: #6b91fb;-fx-background-radius: 20;");
            }
        });
    }

    public void huyMouseEntered() throws IOException{
        huyButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                huyButton.setStyle("-fx-background-color: red; -fx-background-radius: 20;");
            }
        });
    }

    public void huyMouseExited() throws IOException{
        //change color
        huyButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                huyButton.setStyle("-fx-background-color: #6b91fb;-fx-background-radius: 20;");
            }
        });
    }


}

