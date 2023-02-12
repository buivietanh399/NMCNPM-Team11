package controller.nhanKhau;

import entity.HoKhau;
import entity.NhanKhau;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.LichSuHoatDongRepositoryImpl;
import utility.DbUtil;
import entity.LichSuHoatDong;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.HO_KHAU_QUERY_LAY_THONG_TIN;
import static utility.SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN;



public class NhanKhauController implements Initializable {
    @FXML
    private TableView<NhanKhau>table;
    @FXML
    private TableColumn<NhanKhau,Integer>  idColumn;
    @FXML
    private TableColumn<NhanKhau,String>  hoTenColumn;
    @FXML
    private TableColumn<NhanKhau,String>  ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  CMNDColumn;
    @FXML
    private TableColumn<NhanKhau,String>  trangThaiColumn;
    @FXML
    private Button themNhanKhau;
    @FXML
    private ComboBox truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    ObservableList<NhanKhau>  nhanKhauList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<HoKhau> hokhauList = FXCollections.observableArrayList();
    private String query = null;
    private String query_hoTen=null;
    private String query_CMND=null;
    private String query_trangThai=null;
    private String query_nguyenQuan=null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    NhanKhau nhanKhau = null ;
    private String truongTraCuu=null;
    private String duLieuTraCuu=null;
    private int id_NK;

    //lưu id nhân khẩu được chọn phục vụ cho lshd
    public static int id_chinhsuaNk;
    public static int id_chuyenNk;
    public static int id_khaituNk;
    public static int id_tamtruNk;
    public static int id_tamvangNk;

    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();


    @FXML
    private void Select(ActionEvent event) {
        truongTraCuu = truongTraCuuF.getSelectionModel().getSelectedItem().toString();
    }
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {

        try {
            nhanKhauList.clear();
            duLieuTraCuu=duLieuF.getText();
            query_hoTen="SELECT * FROM `nhan_khau` WHERE hoTen like '%" + duLieuTraCuu+"%'";
            query_CMND="SELECT * FROM `nhan_khau` WHERE cmnd like '%" + duLieuTraCuu+"%'";
            query_trangThai="SELECT * FROM `nhan_khau` WHERE trangThai like '%" + duLieuTraCuu+"%'";
            query_nguyenQuan="SELECT * FROM `nhan_khau` WHERE ngaySinh like '%" + duLieuTraCuu+"%'";
            if(truongTraCuu=="Họ tên"){
                preparedStatement = connection.prepareStatement(query_hoTen);
            } else if(truongTraCuu=="Chứng minh nhân dân"){
                preparedStatement = connection.prepareStatement(query_CMND);
            }else if(truongTraCuu=="Trạng thái"){
                preparedStatement = connection.prepareStatement(query_trangThai);
            }else if(truongTraCuu=="Ngày sinh"){
                preparedStatement = connection.prepareStatement(query_nguyenQuan);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Bạn cần chọn trường tra cứu");
                alert.showAndWait();
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String bieuDienNgaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);
                nhanKhauList.add(new NhanKhau(
                        resultSet.getInt("idNhanKhau"),
                        resultSet.getString("hoTen"),
                        bieuDienNgaySinh,
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("CMND"),
                        resultSet.getString("trangThai")));
                table.setItems(nhanKhauList);

            }


        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void refreshTable() {
        try {
            nhanKhauList.clear();


            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String bieuDienNgaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);
                nhanKhauList.add(new NhanKhau(
                        resultSet.getInt("idNhanKhau"),
                        resultSet.getString("hoTen"),
                        bieuDienNgaySinh,
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("CMND"),
                        resultSet.getString("trangThai")));
                table.setItems(nhanKhauList);

            }


        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    private void loadData() throws SQLException {
        connection = DbUtil.getInstance().getConnection();
        refreshTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("bieuDienNgaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        table.setItems(nhanKhauList);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Chứng minh nhân dân","Trạng thái","Ngày sinh");
        truongTraCuuF.setItems(listTruongTraCuu);

        loadData();
    }



    public void changScenceThemNhanKhau(ActionEvent e) throws IOException {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/themNhanKhau.fxml"));
        Parent thongTinNK = loader.load();

        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thông tin nhân khẩu");
        Scene scene = new Scene(thongTinNK);
        stage.setScene(scene);
        stage.show();

    }



    public void detailNhanKhau(ActionEvent e) throws IOException {


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chiTietNhanKhau.fxml"));
        Parent thongTinNK = loader.load();
        ThongTinNhanKhauController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }



        controller.setNhanKhau(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thông tin nhân khẩu");
        Scene scene = new Scene(thongTinNK);
        stage.setScene(scene);
        stage.show();

        //thêm LSHD
        LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
        lichSuHoatDong.setIdHD(selected.getId());
        lichSuHoatDong.setTenHD("Xem chi tiết nhân khẩu");
        lichSuHoatDong.setNoiDungHD("");
        lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
        lichSuHoatDongRepository.addNK(lichSuHoatDong);

    }




    public void chinhSuaNK(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chinhSuaNhanKhau.fxml"));
        Parent chinhSuaNKView = loader.load();
        ChinhSuaNhanKhauController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.setChinhSuaNK(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("CHỈNH SỬA NHÂN KHẨU");
        Scene scene = new Scene(chinhSuaNKView);
        stage.setScene(scene);
        stage.show();

        id_chinhsuaNk = selected.getId();
    }


    public void XoaNK(ActionEvent e) throws IOException {
        try {
            int flag=0;
            NhanKhau userlist = table.getSelectionModel().getSelectedItem();
            if (userlist == null) {
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Thông báo!");
                m.setHeaderText("Không nhân khẩu nào được chọn.");
                m.setContentText("Vui lòng chọn lại.");
                m.show();
                return;
            }
            id_NK=userlist.getId();



            connection = DbUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(HO_KHAU_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if(resultSet.getInt("idChuHo")==id_NK) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đang xóa một chủ hộ, vui lòng đổi chủ hộ trước khi xóa!");
                    alert.showAndWait();
                    flag=1;
                 }
            }
            if(flag==0){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xóa nhân khẩu");
                alert.setHeaderText("Bạn có thực sự muốn xóa nhân khẩu này ?");
                alert.setContentText("Việc xóa nhân khẩu sẽ làm mất tất cả các dữ liệu liên quan đến nhân khẩu.");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == null) {

                } else if (option.get() == ButtonType.OK) {
                    nhanKhau = table.getSelectionModel().getSelectedItem();

                    connection = DbUtil.getInstance().getConnection();
                    query = "DELETE FROM `nhan_khau` WHERE idNhanKHau ="+nhanKhau.getId();
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.execute();
                    refreshTable();

                    //thêm lshd
                    LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
                    lichSuHoatDong.setTenHD("Xóa nhân khẩu");
                    lichSuHoatDong.setIdHD(userlist.getId());
                    lichSuHoatDong.setNoiDungHD("");
                    lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
                    lichSuHoatDongRepository.addNK(lichSuHoatDong);


                } else if (option.get() == ButtonType.CANCEL) {

                }
            }



        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




    public void chuyenNK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/chuyenNhanKhau.fxml"));
        Parent chinhSuaNKView = loader.load();
        ChuyenNhanKhauController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selected.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");

            m2.show();
            return;

        }else{

            id_chuyenNk = selected.getId();
            controller.setChuyenNhanKhau(selected);
            Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("CHUYỂN NHÂN KHẨU");
            Scene scene = new Scene(chinhSuaNKView);
            stage.setScene(scene);
            stage.show();
        }

    }

    public void tamVang() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/tamVang.fxml"));
        Parent tamVangNK = loader.load();
        QuanLyTamVangController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selected.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");

            m2.show();
            return;

        }else{
            controller.setTamVang(selected);
            Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("QUẢN LÝ TẠM VẮNG");
            Scene scene = new Scene(tamVangNK);
            stage.setScene(scene);
            stage.show();

            id_tamvangNk = selected.getId();
        }

    }

    public void khaiTu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/khaiTu.fxml"));
        Parent khaiTuNK = loader.load();
        KhaiTuController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selected.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");

            m2.show();
            return;

        }else{

            controller.setKhaiTu(selected);
            Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("KHAI TỬ");
            Scene scene = new Scene(khaiTuNK, 1100, 700);
            stage.setScene(scene);
            stage.show();

            id_khaituNk = selected.getId();
        }

    }

    public void tamTru() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/nhanKhau/tamTru.fxml"));
        Parent tamTruNK = loader.load();
        TamTruController controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        String trangthai_daMat=selected.getTrangThai();
        if(trangthai_daMat.equals("Đã mất") ){
            Alert m2 = new Alert(Alert.AlertType.INFORMATION);
            m2.setTitle("Thông báo!");
            m2.setHeaderText("Không thể thực hiện chức năng do người này đã qua đời.");

            m2.show();
            return;

        }else{
            controller.setTamTru(selected);
            Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("QUẢN LÝ TẠM TRÚ");
            Scene scene = new Scene(tamTruNK);
            stage.setScene(scene);
            stage.show();

            id_tamtruNk = selected.getId();
        }

    }
}

