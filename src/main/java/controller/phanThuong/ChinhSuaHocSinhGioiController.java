package controller.phanThuong;

import controller.nhanKhau.ThongTinNhanKhauController;
import entity.DipHocSinhGioi;
import entity.LichSuHoatDong;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import repository.LichSuHoatDongRepositoryImpl;
import utility.DbUtil;
import utility.Message;
import utility.Utility;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.LICH_SU_HOC_SINH_GIOI_QUERY_CAC_TRUONG_THEO_ID;

public class ChinhSuaHocSinhGioiController {

    @FXML
    private Label tieuDe;
    @FXML
    private Label namHoc;
    @FXML
    private TextField tienDacBiet;
    @FXML
    private TextField phanThuongDacBiet;
    @FXML
    private TextField phanThuongGioi;
    @FXML
    private TextField tienGioi;
    @FXML
    private TextField phanThuongKha;
    @FXML
    private TextField tienKha;
    @FXML
    private TextField moTa;

    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();


    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    public void setThongTin(DipHocSinhGioi dip) {
        this.dipHocSinhGioi = dip;
        tieuDe.setText(tieuDe.getText() + dip.getNam());
        namHoc.setText(String.valueOf(dip.getNam()));
        phanThuongDacBiet.setText(dip.getPhanQuaDacBiet());
        phanThuongGioi.setText(dip.getPhanQuaGioi());
        phanThuongKha.setText(dip.getPhanQuaKha());
        tienDacBiet.setText(String.valueOf(dip.getTienDacBiet()));
        tienGioi.setText(String.valueOf(dip.getTienGioi()));
        tienKha.setText(String.valueOf(dip.getTienKha()));
        moTa.setText(dip.getMoTa());
    }

    @SneakyThrows
    public void xacNhanClick(MouseEvent mouseEvent) {
        if (phanThuongDacBiet.getText().isEmpty() || phanThuongGioi.getText().isEmpty() || phanThuongKha.getText().isEmpty() ||
                tienDacBiet.getText().isEmpty() || tienGioi.getText().isEmpty() || tienKha.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostive(tienDacBiet.getText()) || !Utility.isPostive(tienGioi.getText()) || !Utility.isPostive(tienKha.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else if (Float.parseFloat(tienDacBiet.getText()) <= Float.parseFloat(tienGioi.getText()) || Float.parseFloat(tienGioi.getText()) <= Float.parseFloat(tienKha.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauDoiTienThuong);
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(Message.xacNhanThayDoiThongTinDip);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //thêm LSHD
                    LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
                    lichSuHoatDong.setTenHD("Chỉnh sửa dịp HSG");
                    lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));;
                    lichSuHoatDong.setIdHD(HocSinhGioiController.id_suaHSG);
                    String noiDung = "Chỉnh sửa: ";
                    try {
                        /*
                        idDip INT NOT NULL AUTO_INCREMENT,
                                  nam INT NOT NULL,
                                  moTa NVARCHAR(255),
                                  phanQuaDacBiet NVARCHAR(255) NOT NULL,
                                  phanQuaGioi NVARCHAR(255) NOT NULL,
                                  phanQuaKha NVARCHAR(255) NOT NULL,
                                  tienDacBiet FLOAT NOT NULL,
                                  tienGioi FLOAT NOT NULL,
                                  tienKha FLOAT NOT NULL,
                         */

                        // kiểm tra các trường
                        connection = DbUtil.getInstance().getConnection();
                        preparedStatement = connection.prepareStatement(LICH_SU_HOC_SINH_GIOI_QUERY_CAC_TRUONG_THEO_ID);
                        preparedStatement.setInt(1, HocSinhGioiController.id_suaHSG);
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            //1 Quà DB

                            if (resultSet.getString("phanQuaDacBiet").compareTo(phanThuongDacBiet.getText()) != 0) {
                                noiDung += "\nPhần quà đặc biệt: "
                                        + resultSet.getString("phanQuaDacBiet") + " => " + phanThuongDacBiet.getText();
                            }

                            //2 Tiền ĐB

                            if (resultSet.getFloat("tienDacBiet")!=Float.parseFloat(tienDacBiet.getText()) ) {
                                noiDung += "\nGiá tiên quà đặc biệt: "
                                        + Float.toString(resultSet.getFloat("tienDacBiet")) + " => " + tienDacBiet.getText();
                            }

                            //3 Qùa giỏi

                            if (resultSet.getString("phanQuaGioi").compareTo(phanThuongGioi.getText()) != 0) {
                                noiDung += "\nPhần quà giỏi: "
                                        + resultSet.getString("phanQuaGioi") + " => " + phanThuongGioi.getText();
                            }

                            //4 Tiền phaanf qua giỏi

                            if (resultSet.getFloat("tienGioi")!=Float.parseFloat(tienGioi.getText())) {
                                noiDung += "\nGiá tiên quà giỏi: "
                                        + Float.toString(resultSet.getFloat("tienGioi")) + " => " + tienGioi.getText();
                            }

                            //5 Qùa khá

                            if (resultSet.getString("phanQuaKha").compareTo(phanThuongKha.getText()) != 0) {
                                noiDung += "\nPhần quà khá: "
                                        + resultSet.getString("phanQuaKha") + " => " + phanThuongKha.getText();
                            }

                            //6 Tiền khá

                            if (resultSet.getFloat("tienKha")!=Float.parseFloat(tienKha.getText())) {
                                noiDung += "\nGiá tiên quà khá: "
                                        + Float.toString(resultSet.getFloat("tienKha")) + " => " + tienKha.getText();
                            }

                            //7 Mô tả
                            if (moTa.getText() != null && moTa.getText() != "") {
                                noiDung += "\nMô tả: "
                                        + resultSet.getString("moTa") + " => " + moTa.getText();
                            }

                        }

                        lichSuHoatDong.setNoiDungHD(noiDung);
                        lichSuHoatDongRepository.addHSG(lichSuHoatDong);

                    dipHocSinhGioi.setPhanQuaDacBiet(phanThuongDacBiet.getText());
                    dipHocSinhGioi.setPhanQuaGioi(phanThuongGioi.getText());
                    dipHocSinhGioi.setPhanQuaKha(phanThuongKha.getText());
                    dipHocSinhGioi.setTienDacBiet(Float.parseFloat(tienDacBiet.getText()));
                    dipHocSinhGioi.setTienGioi(Float.parseFloat(tienGioi.getText()));
                    dipHocSinhGioi.setTienKha(Float.parseFloat(tienKha.getText()));
                    dipHocSinhGioi.setMoTa(moTa.getText());
                    hocSinhGioiImpl.chinhSuaThongTinDip(dipHocSinhGioi.getIdDip(), moTa.getText(),phanThuongDacBiet.getText() , phanThuongGioi.getText(),
                            phanThuongKha.getText(), Float.parseFloat(tienDacBiet.getText()), Float.parseFloat(tienGioi.getText()), Float.parseFloat(tienKha.getText()));
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/phanThuong/chiTietHocSinhGioi.fxml"));
                    Parent p = loader.load();
                    ChiTietHocSinhGioiController c = loader.getController();
                    c.setDipHocSinhGioi(dipHocSinhGioi);
                    Utility.setStage(p);



                    }catch (SQLException ex) {
                        Logger.getLogger(ThongTinNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }
            }
        }
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
