package controller.hoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.LichSuHoatDong;
import entity.LichSuHoatDongXoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repository.HoKhauRepositoryImpl;
import repository.LichSuHoatDongRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {
    @FXML
    private TableView<HoKhau> table;

    @FXML
    private TableColumn<HoKhau, Integer> id_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, Integer> id_chu_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> address_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> hoten_chu_hoCol;

    @FXML
    private TableColumn<HoKhau, Date> ngay_taoCol;

    @FXML
    private TableColumn<HoKhau, String> trang_thaiCol;


    @FXML
    private TextField search_ho_khau;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> list_combo_box = FXCollections.observableArrayList("Địa chỉ","Tên chủ hộ","Trạng thái","Ngày tạo");

    private ObservableList<HoKhau> hokhauList = FXCollections.observableArrayList();

    private ObservableList<HoKhau> searchList = FXCollections.observableArrayList();

    //lưu id cho LSHD

    public  static int id_chuyenHK;

    public  static int id_suaHK;
    public  static int idChuHo_suaHK;

    public  static int id_tachHK;




    //repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();

    //checked//
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/themHoKhau.fxml"));
        Parent them_ho_khau = loader.load();
        Scene scene = new Scene(them_ho_khau);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Thêm hộ khẩu");
        stage.setScene(scene);
        stage.show();
    }

    public void xem_lich_su(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/lichSuChuyenDi.fxml"));
        Parent lich_su_scene = loader.load();
        lichSuChuyenDiController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        //thêm LSHD
        LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
        lichSuHoatDong.setTenHD("Xem lịch sử chuyển đi hộ khẩu");
        lichSuHoatDong.setNoiDungHD("Xóa hộ khẩu với ID chủ hộ: " + hk.getIdChuHo());
        lichSuHoatDong.setIdHD(hk.getIdHoKhau());
        lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
        lichSuHoatDongRepository.addHK(lichSuHoatDong);


        controller.lich_su_chuyen_di(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Lịch sử chuyển đi");
        Scene scene = new Scene(lich_su_scene);
        stage.setScene(scene);
        stage.show();



    }

    //checked//
    public void delete(ActionEvent event){
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xoá");
        alert.setHeaderText("Bạn có muốn xoá không?");
        alert.setContentText("Có hoặc Không:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Thông báo!");

        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){

//             idHoKhau INT NOT NULL AUTO_INCREMENT,
//                        idChuHo INT NOT NULL,
//                        tinhThanhPho NVARCHAR(255) NOT NULL,
//                        quanHuyen NVARCHAR(255) NOT NULL,
//                        phuongXa NVARCHAR(255) NOT NULL,
//                        diaChi NVARCHAR(255) NOT NULL,
//                        ngayTao DATE NOT NULL,
//                        trangThai NVARCHAR(255) NOT NULL,
            //thêm LSHD xóa
            LichSuHoatDongXoa lichSuHoatDong = new LichSuHoatDongXoa();
            lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
            lichSuHoatDong.setIdHD(hk.getIdHoKhau());
            String noiDung = Integer.toString(hk.getIdChuHo()) + "\n"
                    + hk.getTinhThanhPho() + "\n"
                    + hk.getQuanHuyen() + "\n"
                    + hk.getPhuongXa() + "\n"
                    + hk.getDiachi() + "\n"
                    + hk.getNgayTao() + "\n"
                    + hk.getTrangThai() + "\n";
            HoKhauRepositoryImpl hoKhauRepository = new HoKhauRepositoryImpl() ;
            ObservableList<HoKhauNhanKhau> list = hoKhauRepository.loadDataXemHoKhauController(hk.getIdHoKhau());
            for( HoKhauNhanKhau x : list){
                noiDung+= Integer.toString(x.getIdNhanKhau()) + "-" + x.getQuanHeChuHo() + ",";
            }
            lichSuHoatDong.setNoiDungHDXoa(noiDung);
            lichSuHoatDong.setHoTen(hk.getHotenChuho());
            HoKhauRepositoryImpl hoKhauRepository1 = new HoKhauRepositoryImpl();
            lichSuHoatDong.setCMND(hoKhauRepository1.get_CMND_ChuHo(hk.getIdHoKhau()));
            lichSuHoatDongRepository.addHK_Xoa(lichSuHoatDong);




            //xóa
            int idHoKhau = hk.getIdHoKhau();
            update_nk_after_delete(idHoKhau);
            update_ch_after_delete(idHoKhau);
            delete_hk(idHoKhau);
            hokhauList.remove(hk);
            alert1.setContentText("Thành công");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }

    }

    private void delete_hk(int a){
        HoKhauRepo.delete_hk(a);
    }

    private void update_nk_after_delete(int a){
        HoKhauRepo.update_nk_after_delete(a);
    }

    private void update_ch_after_delete(int a){
        HoKhauRepo.update_ch_after_delete(a);
    }

    //checked//
    public void show(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/xemHoKhau.fxml"));
        Parent show_ho_khau = loader.load();
        XemHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.show_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Xem hộ khẩu");
        Scene scene = new Scene(show_ho_khau);
        stage.setScene(scene);
        stage.show();

        //thêm LSHD
        LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
        lichSuHoatDong.setTenHD("Xem chi tiết hộ khẩu");
        lichSuHoatDong.setIdHD(hk.getIdHoKhau());
        lichSuHoatDong.setNoiDungHD("");
        lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
        lichSuHoatDongRepository.addHK(lichSuHoatDong);
    }

    //checked//
    public void change(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/suaHoKhau.fxml"));
        Parent change_ho_khau = loader.load();
        SuaHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.change_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Sửa hộ khẩu");
        Scene scene = new Scene(change_ho_khau);
        stage.setScene(scene);
        stage.show();

        //LSHD
        id_suaHK = hk.getIdHoKhau();
        idChuHo_suaHK = hk.getIdHoKhau();
    }

    //checked//
    public void tach(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/tachHoKhau.fxml"));
        Parent tach_ho_khau = loader.load();
        TachHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        id_tachHK = hk.getIdHoKhau();

        controller.tach_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Tách hộ khẩu");
        Scene scene = new Scene(tach_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void chuyen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/chuyenHoKhau.fxml"));
        Parent chuyen_ho_khau = loader.load();
        ChuyenHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        id_chuyenHK = hk.getIdHoKhau();

        controller.chuyen_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.setTitle("Chuyển hộ khẩu");
        Scene scene = new Scene(chuyen_ho_khau);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadDataHKController();
    }

    private void initCol(){
        id_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idHoKhau"));
        id_chu_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idChuHo"));
        address_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("diachi"));
        hoten_chu_hoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("hotenChuho"));
        ngay_taoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Date>("ngayTao"));
        trang_thaiCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("trangThai"));
    }

    private void loadDataHKController(){
        hokhauList.clear();
        hokhauList.addAll(HoKhauRepo.loadDataHKController());
        table.setItems(hokhauList);
        comboBox.setItems(list_combo_box);
    }

    public void searchClick(MouseEvent mouseEvent) {
        searchList.clear();
        String search_text = search_ho_khau.getText().trim().toLowerCase(); ;
        String sc = comboBox.getValue();
        try{
            if(sc.equals("Địa chỉ")){
                for(HoKhau a : hokhauList){
                    if((a.getDiachi().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Tên chủ hộ")){
                for(HoKhau a : hokhauList){
                    if((a.getHotenChuho().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Trạng thái")){
                for(HoKhau a : hokhauList){
                    if((a.getTrangThai().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Ngày tạo")){
                for(HoKhau a : hokhauList){
                    if((String.valueOf(a.getNgayTao())).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
        }catch(NullPointerException ex){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn trường tìm kiếm!");
            m.setContentText("Mời chọn lại!");
            m.show();
            return;
        }

    }

    public void refresh_button(ActionEvent e){
        loadDataHKController();
    }
}
