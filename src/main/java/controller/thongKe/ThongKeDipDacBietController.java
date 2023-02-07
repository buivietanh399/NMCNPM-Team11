package controller.thongKe;

import entity.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import lombok.SneakyThrows;
import repository.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import utility.SoundClick;


public class ThongKeDipDacBietController implements Initializable {

    @FXML
    private Pane dipDacBietMainPane;


    //các dịp
    @FXML
    private TableView<DipDacBiet> bangDB;

    @FXML
    private TableColumn<DipDacBiet, Integer> idDip;
    @FXML
    private TableColumn<DipDacBiet, Integer> nam;
    @FXML
    private TableColumn<DipDacBiet, String> tenDip;
    @FXML
    private TableColumn<DipDacBiet, String> moTa;

    //xem chi tiết các dịp
    //bảng đã trao
    @FXML
    private  Label soHoDaTraoLabel;
    @FXML
    private TableView<HoDaTrao_DB> hoDaTraoTableView;
    @FXML
    private TableColumn<HoDaTrao_DB, Integer> idHo;
    @FXML
    private TableColumn<HoDaTrao_DB, Integer> soThanhVienDuocTraoThuong;
    @FXML
    private TableColumn<HoDaTrao_DB, Integer> tongSoTienDuocTraoThuong;

    //2. Chưa trao
    @FXML
    private  Label soHoChuaTraoLabel;
    @FXML
    private TableView<HoChuaTrao_DB> hoChuaTraoTableView;
    @FXML
    private TableColumn<HoChuaTrao_DB, Integer> idHo_ChuaTrao;
    @FXML
    private TableColumn<HoChuaTrao_DB, Integer> soThanhVienChuaTraoThuong;
    @FXML
    private TableColumn<HoChuaTrao_DB, Integer> tongSoTienCanTraoThuong;

    //3. chi tiết

    @FXML
    private TableView<ChiTiet_TraoThuong_DB> chiTietTraoThuongTableView;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_DB, Integer> idNhanKhau;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_DB, String> tenNhanKhau;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_DB,  String> doTuoi;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_DB,  String> phanQua;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_DB,  Integer> soTien;




    @FXML
    private Button chiTietBackButton;

    @FXML
    private ImageView backImageView;




    @FXML
    private ComboBox yearComboBox;

    @FXML
    private Label cacDipTraoThuongLabel;

    @FXML
    private Label namHocLabel;

    @FXML
    private Label titleLabel;

    //

    DipDacBietRepositoryImpl dipDacBietImpl = new DipDacBietRepositoryImpl();


    private  String year ;

    public final static int MAX_YEAR = 2023;
    int  min_year = 2023;

    //select cac nam
    public  void setOptions(){
        ArrayList<Integer> listYear = dipDacBietImpl.listYear();
        yearComboBox.getItems().clear();
        int n = listYear.size();
        for( int i = 0 ; i < n; i++){
            if(listYear.get(i) < min_year) min_year = listYear.get(i);
        }
        for( int i = min_year ; i <= MAX_YEAR; i++){
            MenuItem menuItem = new MenuItem(Integer.toString(i));
            yearComboBox.getItems().add(Integer.toString(i));
        }
    }

    public void yearComboBoxOnAction(ActionEvent event) {
        year = yearComboBox.getSelectionModel().getSelectedItem().toString();
        try {
            ObservableList<DipDacBiet> dipDacBiet = dipDacBietImpl.timNam(Integer.valueOf(year));
            bangDB.setItems(dipDacBiet);
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOptions();
        soHoDaTraoLabel.setVisible(false);
        hoDaTraoTableView.setVisible(false);
        soHoChuaTraoLabel.setVisible(false);
        hoChuaTraoTableView.setVisible(false);
        chiTietBackButton.setVisible(false);
        chiTietTraoThuongTableView.setVisible(false);


        //set up các bảng

        //bảng dịp
        idDip.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        nam.setCellValueFactory(new PropertyValueFactory<>("nam"));
        tenDip.setCellValueFactory(new PropertyValueFactory<>("ten"));
        moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ObservableList<DipDacBiet> dipDacBiet= dipDacBietImpl.bangDipDacBiet();
        bangDB.setItems(dipDacBiet);

        //bảng đã trao
        idHo.setCellValueFactory(new PropertyValueFactory<>("idhoKhau"));
        soThanhVienDuocTraoThuong.setCellValueFactory(new PropertyValueFactory<>("soThanhVienDaTrao"));
        tongSoTienDuocTraoThuong.setCellValueFactory(new PropertyValueFactory<>("tongTienDaTrao"));

        //bảng chưa trao
        idHo_ChuaTrao.setCellValueFactory(new PropertyValueFactory<>("idhoKhau"));
        soThanhVienChuaTraoThuong.setCellValueFactory(new PropertyValueFactory<>("soThanhVienChuaTrao"));
        tongSoTienCanTraoThuong.setCellValueFactory(new PropertyValueFactory<>("tongTienCanTrao"));

        //bảng chi tiết
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        tenNhanKhau.setCellValueFactory(new PropertyValueFactory<>("hoten"));
        doTuoi.setCellValueFactory(new PropertyValueFactory<>("doTuoi"));
        phanQua.setCellValueFactory(new PropertyValueFactory<>("phanQua"));
        soTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
    }

    /*public void tim() throws SQLException {
        if (namTimNoiDung.getText().isBlank() && tenDipNoiDung.getText().isBlank()) {
            ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.bangDipDacBiet();
            bangDB.setItems(dipDacBiet);
        } else if (namTimNoiDung.getText().isBlank() || tenDipNoiDung.getText().isBlank()){
            if (namTimNoiDung.getText().isBlank()) {
                ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.tenDipDacBiet(tenDipNoiDung.getText());
                bangDB.setItems(dipDacBiet);
            } else {
                try {
                    ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.namDipDacBiet(Integer.valueOf(namTimNoiDung.getText()));
                    bangDB.setItems(dipDacBiet);
                } catch(Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Tìm kiếm dịp đặc biệt");
                    alert.setHeaderText(null);
                    alert.setContentText("Năm học có kiểu là số tự nhiên");
                    alert.showAndWait();
                }
            }
        } else {
            try {
                ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.namTenDipDacBiet(Integer.valueOf(namTimNoiDung.getText()), tenDipNoiDung.getText());
                bangDB.setItems(dipDacBiet);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tìm kiếm dịp đặc biệt");
                alert.setHeaderText(null);
                alert.setContentText("Năm học có kiểu là số tự nhiên");
                alert.showAndWait();
            }
        }
    }
*/
    SoundClick SoundClick = new SoundClick();
    @SneakyThrows
    public void backClick() {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        dipDacBietMainPane.getChildren().clear();
        dipDacBietMainPane.getChildren().add(pane);
    }

    @SneakyThrows
    public void xemThongKe(MouseEvent mouseEvent) {
        DipDacBiet dipDacBiet = bangDB.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (dipDacBiet  != null) {
                //new
                //1. bảng đã trao

                int tongTien_DaTrao = dipDacBietImpl.tongTien_datrao(dipDacBiet.getIdDip());
                soHoDaTraoLabel.setText("Đã trao: " + String.valueOf(dipDacBietImpl.nguoiDaTrao(dipDacBiet.getIdDip()))
                        +" nhân khẩu - "
                        +" Tổng: "
                        + Integer.toString(tongTien_DaTrao) + " VND");
                soHoDaTraoLabel.setVisible(true);
                ObservableList<HoDaTrao_DB> hoDaTrao = dipDacBietImpl.bangHoDaTrao(dipDacBiet.getIdDip());
                hoDaTraoTableView.setItems(hoDaTrao);
                hoDaTraoTableView.setVisible(true);

                //2. bảng chưa trao

                int tongTien_ChuaTrao = dipDacBietImpl.tongTien_chuatrao(dipDacBiet.getIdDip());
                soHoChuaTraoLabel.setText("Chưa trao: " + String.valueOf(dipDacBietImpl.nguoiChuaTrao(dipDacBiet.getIdDip()))
                        +" nhân khẩu - "
                        +" Tổng: "
                        + Integer.toString(tongTien_ChuaTrao ) + " VND");
                soHoChuaTraoLabel.setVisible(true);
                ObservableList<HoChuaTrao_DB> hoChuaTrao = dipDacBietImpl.bangHoChuaTrao(dipDacBiet.getIdDip());
                hoChuaTraoTableView.setItems(hoChuaTrao);
                hoChuaTraoTableView.setVisible(true);


            }
        }
    }


    //xem chi tiết các nhân khẩu đã trao - chưa trao

    // 1.Đã trao
    @SneakyThrows
    public void hoDaTraoMouseClicked(MouseEvent mouseEvent){
        DipDacBiet dipDacBiet = bangDB.getSelectionModel().getSelectedItem();
        HoDaTrao_DB hoDaTraoDB = hoDaTraoTableView.getSelectionModel().getSelectedItem();


        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (dipDacBiet != null && hoDaTraoDB!=null) {
                bangDB.setVisible(false);
                hoDaTraoTableView.setVisible(false);
                hoChuaTraoTableView.setVisible(false);
                soHoChuaTraoLabel.setVisible(false);
                soHoDaTraoLabel.setVisible(false);
                yearComboBox.setVisible(false);
                backImageView.setVisible(false);
                cacDipTraoThuongLabel.setVisible(false);
                namHocLabel.setVisible(false);

                ObservableList<ChiTiet_TraoThuong_DB> nhanKhauDaTrao = dipDacBietImpl.bangNhanKhauDaTrao(hoDaTraoDB.getIdhoKhau(), dipDacBiet.getIdDip());
                chiTietTraoThuongTableView.setItems(nhanKhauDaTrao);
                phanQua.setText("Phần thưởng được trao");
                chiTietTraoThuongTableView.setVisible(true);
                chiTietBackButton.setVisible(true);
                titleLabel.setText("Chi tiết trao thưởng");

            }

        }

    }

    //2. Chưa trao
    @SneakyThrows
    public void hoChuaTraoMouseClicked(MouseEvent mouseEvent){
        DipDacBiet dipDacBiet = bangDB.getSelectionModel().getSelectedItem();
        HoChuaTrao_DB hoChuaTraoDB = hoChuaTraoTableView.getSelectionModel().getSelectedItem();



        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (dipDacBiet != null && hoChuaTraoDB!=null) {
                bangDB.setVisible(false);
                hoDaTraoTableView.setVisible(false);
                hoChuaTraoTableView.setVisible(false);
                soHoChuaTraoLabel.setVisible(false);
                soHoDaTraoLabel.setVisible(false);
                yearComboBox.setVisible(false);
                backImageView.setVisible(false);
                cacDipTraoThuongLabel.setVisible(false);
                namHocLabel.setVisible(false);


                ObservableList<ChiTiet_TraoThuong_DB> nhanKhauChuaTrao = dipDacBietImpl.bangNhanKhauChuaTrao(hoChuaTraoDB.getIdhoKhau(),dipDacBiet.getIdDip());
                chiTietTraoThuongTableView.setItems(nhanKhauChuaTrao);
                phanQua.setText("Phần thưởng cần trao");
                chiTietTraoThuongTableView.setVisible(true);
                chiTietBackButton.setVisible(true);
                titleLabel.setText("Chi tiết trao thưởng");

            }

        }

    }

    //3. Back
    @SneakyThrows
    public void chiTietBackButtonOnAction(ActionEvent event) {
        SoundClick.playSound() ;
        bangDB.setVisible(true);
        hoDaTraoTableView.setVisible(true);
        hoChuaTraoTableView.setVisible(true);
        soHoChuaTraoLabel.setVisible(true);
        soHoDaTraoLabel.setVisible(true);
        yearComboBox.setVisible(true);
        backImageView.setVisible(true);
        cacDipTraoThuongLabel.setVisible(true);
        namHocLabel.setVisible(true);


        titleLabel.setText("Thống kê dịp đặc biệt");

        chiTietTraoThuongTableView.setVisible(false);
        chiTietBackButton.setVisible(false);

    }

}
