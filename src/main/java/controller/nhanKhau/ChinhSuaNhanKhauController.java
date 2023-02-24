package controller.nhanKhau;

import entity.LichSuHoatDong;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.LichSuHoatDongRepositoryImpl;
import utility.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.*;

public class ChinhSuaNhanKhauController  {
    @FXML
    private TextField hoTenLabel;
    @FXML
    private TextField biDanhLabel;
    @FXML
    private DatePicker ngaySinhLabel;
    @FXML
    private TextField noiSinhLabel;
    @FXML
    private ComboBox combGioiTinh;
    @FXML
    private TextField nguyenQuanLabel;
    @FXML
    private TextField danTocLabel;
    @FXML
    private TextField tonGiaoLabel;
    @FXML
    private TextField quocTichLabel;
    @FXML
    private TextField ngheNghiepLabel;
    @FXML
    private TextField noiLamViecLabel;
    @FXML
    private TextField CMNDLabel;
    @FXML
    private DatePicker ngayCapLabel;
    @FXML
    private DatePicker chuyenDenNgayLabel;
    @FXML
    private TextField noiThuongTruTruocLabel;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    private int id_NK;
    String gioiTinhC=null;

    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository  = new LichSuHoatDongRepositoryImpl();



    public void setChinhSuaNK(NhanKhau nk){
        id_NK=nk.getId();
        ObservableList<String> listGioiTinh2 = FXCollections.observableArrayList("Nam","Nữ");
        combGioiTinh.setItems(listGioiTinh2);
        loadData_ChinhSua();


    }

    @SneakyThrows
    @FXML
    private void save_ChinhSua(MouseEvent event) {
        connection = DbUtil.getInstance().getConnection();
        String hoTen = hoTenLabel.getText();
        String ngaySinh = String.valueOf(ngaySinhLabel.getValue());

        String noiSinh = noiSinhLabel.getText();

        String nguyenQuan = nguyenQuanLabel.getText();
        String danToc = danTocLabel.getText();
        String tonGiao = tonGiaoLabel.getText();
        String quocTich = quocTichLabel.getText();



        if (hoTen.isEmpty() || ngaySinh.isEmpty() || quocTich.isEmpty() || tonGiao.isEmpty()||
                danToc.isEmpty()||noiSinh.isEmpty()||nguyenQuan.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {

            //thêm LSHD
            LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
            lichSuHoatDong.setIdHD(NhanKhauController.id_chinhsuaNk);
            lichSuHoatDong.setTenHD("Chỉnh sửa nhân khẩu");
            lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
            String noiDung = "Chỉnh sửa: ";

            try {

                //1. kiểm tra họ tên
                connection = DbUtil.getInstance().getConnection();
                preparedStatement = connection.prepareStatement(LICH_SU_NHAN_KHAU_QUERY_CAC_TRUONG_THEO_ID);
                preparedStatement.setInt(1, NhanKhauController.id_chinhsuaNk);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    //1

                    if (resultSet.getString("hoTen").compareTo(hoTen) != 0) {
                        noiDung += "\nHọ tên: "
                                + resultSet.getString("hoTen") + " => " + hoTen;
                    }

                    //2

                    if (resultSet.getString("ngaySinh").compareTo(ngaySinh) != 0) {
                        noiDung += "\nNgày sinh: "
                                + resultSet.getString("ngaySinh") + " => " + ngaySinh;
                    }

                    //3

                    if (resultSet.getString("noiSinh").compareTo(noiSinh) != 0) {
                        noiDung += "\nNơi sinh: "
                                + resultSet.getString("noiSinh") + " => " + noiSinh;
                    }

                    //4

                    if (resultSet.getString("nguyenQuan").compareTo(nguyenQuan) != 0) {
                        noiDung += "\nNguyên quán: "
                                + resultSet.getString("nguyenQuan") + " => " + nguyenQuan;
                    }

                    //5

                    if (resultSet.getString("danToc").compareTo(danToc) != 0) {
                        noiDung += "\nDân tộc: "
                                + resultSet.getString("danToc") + " => " + danToc;
                    }

                    //6

                    if (resultSet.getString("quocTich").compareTo(quocTich) != 0) {
                        noiDung += "\nQuốc tịch: "
                                + resultSet.getString("quocTich") + " => " + quocTich;
                    }

                    //7
                    if (resultSet.getString("tonGiao").compareTo(tonGiao) != 0) {
                        noiDung += "\nTôn giáo: "
                                + resultSet.getString("tonGiao") + " => " + tonGiao;
                    }

                    //8

                    if (resultSet.getString("ngheNghiep").compareTo(ngheNghiepLabel.getText()) != 0) {
                        noiDung += "\nNghề nghiệp: "
                                + resultSet.getString("ngheNghiep") + " => " + ngheNghiepLabel.getText();
                    }

                    //9

                    if (resultSet.getString("noiLamViec").compareTo(noiLamViecLabel.getText()) != 0) {
                        noiDung += "\nNơi làm việc: "
                                + resultSet.getString("noiLamViec") + " => " + noiLamViecLabel.getText();
                    }

                    //10

                    if (resultSet.getString("cmnd").compareTo(CMNDLabel.getText()) != 0) {
                        noiDung += "\nCMND: "
                                + resultSet.getString("cmnd") + " => " + CMNDLabel.getText();
                    }

                    //11

                    if (resultSet.getString("ngayCap").compareTo(ngayCapLabel.getValue().toString()) != 0) {
                        noiDung += "\nNgày cấp: "
                                + resultSet.getString("ngayCap") + " => " + ngayCapLabel.getValue().toString();
                    }

                    //12

                    if (resultSet.getString("chuyenDenNgay").compareTo(chuyenDenNgayLabel.getValue().toString()) != 0) {
                        noiDung += "\nChuyển đến ngày: "
                                + resultSet.getString("chuyenDenNgay") + " => " + chuyenDenNgayLabel.getValue().toString();
                    }

                    //13

                    if (resultSet.getString("noiThuongTruTruoc").compareTo(noiThuongTruTruocLabel.getText()) != 0) {
                        noiDung += "\nNơi thường trú trước: "
                                + resultSet.getString("noiThuongTruTruoc") + " => " + noiThuongTruTruocLabel.getText();
                    }

                }

                lichSuHoatDong.setNoiDungHD(noiDung);
                lichSuHoatDongRepository.addNK(lichSuHoatDong);

            }catch (SQLException ex) {
                Logger.getLogger(ThongTinNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
            }
                update();
                Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
                alert_TC.setHeaderText(null);
                alert_TC.setContentText("Chỉnh sửa thành công");
                alert_TC.showAndWait();
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
    }
    @FXML
    private void selectGioiTinh(ActionEvent event) {
        gioiTinhC = combGioiTinh.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    public void loadData_ChinhSua() {
        try {


            connection = DbUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                if(resultSet.getInt("idNhanKhau")==id_NK){

                    hoTenLabel.setText(resultSet.getString("hoTen"));
                    biDanhLabel.setText(resultSet.getString("biDanh"));
                   //String ngaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);

                    ngaySinhLabel.setValue(LocalDate.parse(resultSet.getString("ngaySinh")));
                    noiSinhLabel.setText(resultSet.getString("noiSinh"));

                    combGioiTinh.getSelectionModel().select(resultSet.getString("gioiTinh"));
                    gioiTinhC=resultSet.getString("gioiTinh");
                    nguyenQuanLabel.setText(resultSet.getString("nguyenQuan"));
                    danTocLabel.setText(resultSet.getString("danToc"));
                    tonGiaoLabel.setText(resultSet.getString("tonGiao"));
                    quocTichLabel.setText(resultSet.getString("quocTich"));
                    ngheNghiepLabel.setText(resultSet.getString("ngheNghiep"));
                    noiLamViecLabel.setText(resultSet.getString("noiLamViec"));
                    CMNDLabel.setText(resultSet.getString("cmnd"));

                    if(resultSet.getString("ngayCap")!=null){
                        ngayCapLabel.setValue(LocalDate.parse(resultSet.getString("ngayCap")));
                    }

                    if(resultSet.getString("chuyenDenNgay")!=null){
                        chuyenDenNgayLabel.setValue(LocalDate.parse(resultSet.getString("chuyenDenNgay")));
                    }


                    noiThuongTruTruocLabel.setText(resultSet.getString("noiThuongTruTruoc"));


                }



            }


        } catch (SQLException ex) {
            Logger.getLogger(ThongTinNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }


    private void update() {

        String trangThaiMacDinh=null;


        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_UPDATE+id_NK);
            preparedStatement.setString(1, hoTenLabel.getText());

            if (biDanhLabel.getText()=="" ){
                preparedStatement.setString(2, null);
            }else{
                preparedStatement.setString(2, biDanhLabel.getText());
            }
            preparedStatement.setString(3, String.valueOf(ngaySinhLabel.getValue()));
            preparedStatement.setString(4, noiSinhLabel.getText());
            preparedStatement.setString(5, gioiTinhC);
            preparedStatement.setString(6, nguyenQuanLabel.getText());
            preparedStatement.setString(7, danTocLabel.getText());
            preparedStatement.setString(8, tonGiaoLabel.getText());
            preparedStatement.setString(9, quocTichLabel.getText());
            if (ngheNghiepLabel.getText()=="" ){
                preparedStatement.setString(10, null);
            }else{
                preparedStatement.setString(10, ngheNghiepLabel.getText());
            }

            if (noiLamViecLabel.getText()=="" ){
                preparedStatement.setString(11, null);
            }else{
                preparedStatement.setString(11, noiLamViecLabel.getText());
            }

            if (CMNDLabel.getText()=="" ){
                preparedStatement.setString(12, "");
            }else{
                preparedStatement.setString(12, CMNDLabel.getText());
            }

            if (ngayCapLabel.getValue()==null ){
                preparedStatement.setString(13, null);
            }else{
                preparedStatement.setString(13, String.valueOf(ngayCapLabel.getValue()));
            }
            if (chuyenDenNgayLabel.getValue()==null ){
                preparedStatement.setString(14, null);
            }else{
                preparedStatement.setString(14, String.valueOf(chuyenDenNgayLabel.getValue()));
            }


            if (noiThuongTruTruocLabel.getText()=="" ){
                preparedStatement.setString(15, null);
            }else{
                preparedStatement.setString(15, noiThuongTruTruocLabel.getText());
            }


            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void huy(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }



}
