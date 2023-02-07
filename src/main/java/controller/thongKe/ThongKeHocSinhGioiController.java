package controller.thongKe;

import entity.ChiTiet_TraoThuong_HSG;
import entity.DipHocSinhGioi;
import entity.HoChuaTrao_HSG;
import entity.HoDaTrao_HSG;
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

public class ThongKeHocSinhGioiController implements Initializable {

    @FXML
    private Pane hocSinhGioiMainPane;



    //xem cac dịp
    @FXML
    private TableView<DipHocSinhGioi> bangHSG;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> idDip;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> nam;
    @FXML
    private TableColumn<DipHocSinhGioi, String> moTa;

    //xem chi tiết các dịp
    //1. Đã trao
    @FXML
    private  Label soHoDaTraoLabel;
    @FXML
    private TableView<HoDaTrao_HSG> hoDaTraoTableView;
    @FXML
    private TableColumn<HoDaTrao_HSG, Integer> idHo;
    @FXML
    private TableColumn<HoDaTrao_HSG, Integer> soThanhVienDuocTraoThuong;
    @FXML
    private TableColumn<HoDaTrao_HSG, Integer> tongSoTienDuocTraoThuong;

    //2. Chưa trao
    @FXML
    private  Label soHoChuaTraoLabel;
    @FXML
    private TableView<HoChuaTrao_HSG> hoChuaTraoTableView;
    @FXML
    private TableColumn<HoChuaTrao_HSG, Integer> idHo_ChuaTrao;
    @FXML
    private TableColumn<HoChuaTrao_HSG, Integer> soThanhVienChuaTraoThuong;
    @FXML
    private TableColumn<HoChuaTrao_HSG, Integer> tongSoTienCanTraoThuong;

    //3. chi tiết

    @FXML
    private TableView<ChiTiet_TraoThuong_HSG> chiTietTraoThuongTableView;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_HSG, Integer> idNhanKhau;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_HSG, String> tenNhanKhau;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_HSG,  String> danhHieu;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_HSG,  String> phanQua;
    @FXML
    private TableColumn<ChiTiet_TraoThuong_HSG,  Integer> soTien;




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







    
    
    HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();





    private  String year ;

    public final static int MAX_YEAR = 2023;
    int  min_year = 2023;

    //select cac nam
    public  void setOptions(){
        ArrayList<Integer> listYear = hocSinhGioiImpl.listYear();
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
            ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.timNam(Integer.valueOf(year));
            bangHSG.setItems(dipHocSinhGioi);
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
        moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.bangDipHocSinhGioi();
        bangHSG.setItems(dipHocSinhGioi);

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
        danhHieu.setCellValueFactory(new PropertyValueFactory<>("danhHieu"));
        phanQua.setCellValueFactory(new PropertyValueFactory<>("phanQua"));
        soTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));

    }



    SoundClick SoundClick = new SoundClick();
    @SneakyThrows
    public void backClick() {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        hocSinhGioiMainPane.getChildren().clear();
        hocSinhGioiMainPane.getChildren().add(pane);
    }

    @SneakyThrows
    public void xemThongKe(MouseEvent mouseEvent) {
        DipHocSinhGioi dipHocSinhGioi = bangHSG.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (dipHocSinhGioi != null) {
                //new
                //1. bảng đã trao

                int tongTien_DaTrao = hocSinhGioiImpl.tongTien_datrao(dipHocSinhGioi.getIdDip());
                soHoDaTraoLabel.setText("Đã trao: " + String.valueOf(hocSinhGioiImpl.nguoiDaTrao(dipHocSinhGioi.getIdDip()))
                        +" nhân khẩu - "
                        +" Tổng: "
                        + Integer.toString(tongTien_DaTrao) + " VND");
                soHoDaTraoLabel.setVisible(true);
                ObservableList<HoDaTrao_HSG> hoDaTrao = hocSinhGioiImpl.bangHoDaTrao(dipHocSinhGioi.getIdDip());
                hoDaTraoTableView.setItems(hoDaTrao);
                hoDaTraoTableView.setVisible(true);

                //2. bảng chưa trao

                int tongTien_ChuaTrao = hocSinhGioiImpl.tongTien_chuatrao(dipHocSinhGioi.getIdDip());
                soHoChuaTraoLabel.setText("Chưa trao: " + String.valueOf(hocSinhGioiImpl.nguoiChuaTrao(dipHocSinhGioi.getIdDip()))
                        +" nhân khẩu - "
                        +" Tổng: "
                        + Integer.toString(tongTien_ChuaTrao ) + " VND");
                soHoChuaTraoLabel.setVisible(true);
                ObservableList<HoChuaTrao_HSG> hoChuaTrao = hocSinhGioiImpl.bangHoChuaTrao(dipHocSinhGioi.getIdDip());
                hoChuaTraoTableView.setItems(hoChuaTrao);
                hoChuaTraoTableView.setVisible(true);


            }
        }
    }


    //xem chi tiết các nhân khẩu đã trao - chưa trao

    // 1.Đã trao
    @SneakyThrows
    public void hoDaTraoMouseClicked(MouseEvent mouseEvent){
        DipHocSinhGioi dipHocSinhGioi = bangHSG.getSelectionModel().getSelectedItem();
        HoDaTrao_HSG hoDaTraoHsg = hoDaTraoTableView.getSelectionModel().getSelectedItem();


        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (dipHocSinhGioi != null && hoDaTraoHsg!=null) {
                bangHSG.setVisible(false);
                hoDaTraoTableView.setVisible(false);
                hoChuaTraoTableView.setVisible(false);
                soHoChuaTraoLabel.setVisible(false);
                soHoDaTraoLabel.setVisible(false);
                yearComboBox.setVisible(false);
                backImageView.setVisible(false);
                cacDipTraoThuongLabel.setVisible(false);
                namHocLabel.setVisible(false);

                ObservableList<ChiTiet_TraoThuong_HSG> nhanKhauDaTrao = hocSinhGioiImpl.bangNhanKhauDaTrao(hoDaTraoHsg.getIdhoKhau(), dipHocSinhGioi.getIdDip());
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
        DipHocSinhGioi dipHocSinhGioi = bangHSG.getSelectionModel().getSelectedItem();
        HoChuaTrao_HSG hoChuaTraoHsg = hoChuaTraoTableView.getSelectionModel().getSelectedItem();



        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if (dipHocSinhGioi != null && hoChuaTraoHsg!=null) {
                bangHSG.setVisible(false);
                hoDaTraoTableView.setVisible(false);
                hoChuaTraoTableView.setVisible(false);
                soHoChuaTraoLabel.setVisible(false);
                soHoDaTraoLabel.setVisible(false);
                yearComboBox.setVisible(false);
                backImageView.setVisible(false);
                cacDipTraoThuongLabel.setVisible(false);
                namHocLabel.setVisible(false);

                //System.out.println(hoChuaTraoHsg.getIdhoKhau());
                //System.out.println(dipHocSinhGioi.getIdDip());
                ObservableList<ChiTiet_TraoThuong_HSG> nhanKhauChuaTrao = hocSinhGioiImpl.bangNhanKhauChuaTrao(hoChuaTraoHsg.getIdhoKhau(),dipHocSinhGioi.getIdDip());
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
        bangHSG.setVisible(true);
        hoDaTraoTableView.setVisible(true);
        hoChuaTraoTableView.setVisible(true);
        soHoChuaTraoLabel.setVisible(true);
        soHoDaTraoLabel.setVisible(true);
        yearComboBox.setVisible(true);
        backImageView.setVisible(true);
        cacDipTraoThuongLabel.setVisible(true);
        namHocLabel.setVisible(true);


        titleLabel.setText("Thống kê học sinh giỏi");

        chiTietTraoThuongTableView.setVisible(false);
        chiTietBackButton.setVisible(false);
    }

}
