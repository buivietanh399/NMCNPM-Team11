package controller.LichSu;

import entity.DipHocSinhGioi;
import entity.LichSuHoatDong;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import controller.LichSu.LichSuController;
import utility.History;
import utility.SoundClick;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ChiTietLichSuController implements Initializable {


    @FXML
    private Label titleLabel;

    @FXML
    private ImageView backImageView;

    @FXML
    private TableView<LichSuHoatDong>  bangLSHD;

    @FXML
    private TableColumn<LichSuHoatDong, Integer> idHD;

    @FXML
    private TableColumn<LichSuHoatDong, String> tenHD;

    @FXML
    private TableColumn<LichSuHoatDong, LocalDate> thoiGianHD;




 // LocalDate currentDate = LocalDate.now();
    @FXML
    private TableColumn<LichSuHoatDong, String> noiDungHD;

    @FXML
    private Pane mainPane;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idHD.setCellValueFactory(new PropertyValueFactory<>("idHD"));
        tenHD.setCellValueFactory(new PropertyValueFactory<>("tenHD"));
        noiDungHD.setCellValueFactory(new PropertyValueFactory<>("noiDungHD"));
        thoiGianHD.setCellValueFactory(new PropertyValueFactory<>("thoiGianHD"));

        //1. ls Nhân khẩu
        if(LichSuController.option == 1){
            bangLSHD.getItems().clear();
            ObservableList<LichSuHoatDong> lichSuHoatDongs = History.lishSuHD_NhanKhau;
            bangLSHD.setItems(lichSuHoatDongs);
        }

        //2. ls Hộ khẩu

        else if ( LichSuController.option == 2){
            bangLSHD.getItems().clear();
            ObservableList<LichSuHoatDong> lichSuHoatDongs = History.lishSuHD_HoKhau;
            bangLSHD.setItems(lichSuHoatDongs);

        }

        //3. ls HSG

        else if ( LichSuController.option == 3){
            bangLSHD.getItems().clear();
            ObservableList<LichSuHoatDong> lichSuHoatDongs = History.lishSuHD_HocSinhGioi;
            bangLSHD.setItems(lichSuHoatDongs);

        }

        //4. ls DB

        else {
            bangLSHD.getItems().clear();
            ObservableList<LichSuHoatDong> lichSuHoatDongs = History.lishSuHD_DipDacBiet;
            bangLSHD.setItems(lichSuHoatDongs);

        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        SoundClick SoundClick = new SoundClick();
        SoundClick.playSound();
        Parent lichSuPane = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSu.fxml"));
        mainPane.getChildren().add(lichSuPane);
    }







}
