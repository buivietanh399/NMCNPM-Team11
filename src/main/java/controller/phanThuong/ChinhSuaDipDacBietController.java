package controller.phanThuong;

import entity.DipDacBiet;
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
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;
import repository.LichSuHoatDongRepositoryImpl;
import utility.DbUtil;
import utility.Message;
import utility.Utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;

import static utility.SQLCommand.*;

public class ChinhSuaDipDacBietController {

    @FXML
    private Label namHoc;
    @FXML
    private TextField tenDip;
    @FXML
    private TextField phanThuong05;
    @FXML
    private TextField phanThuong614;
    @FXML
    private TextField phanThuong1517;
    @FXML
    private TextField tien05;
    @FXML
    private TextField tien614;
    @FXML
    private TextField tien1517;
    @FXML
    private TextField moTa;

    private DipDacBiet dipDacBiet = new DipDacBiet();
    private DipDacBietRepository dipDacBietImpl = new DipDacBietRepositoryImpl();

    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();


    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    private String tenGoc;

    public void setThongTin(DipDacBiet dip) {
        this.dipDacBiet = dip;
        namHoc.setText(String.valueOf(dip.getNam()));
        tenDip.setText(dip.getTen());
        phanThuong05.setText(dip.getPhanQua05());
        phanThuong614.setText(dip.getPhanQua1517());
        phanThuong1517.setText(dip.getPhanQua1517());
        tien05.setText(String.valueOf(dip.getTien05()));
        tien614.setText(String.valueOf(dip.getTien614()));
        tien1517.setText(String.valueOf(dip.getTien1517()));
        moTa.setText(dip.getMoTa());
        tenGoc = dip.getTen();
    }

    @SneakyThrows
    public void xacNhanClick(MouseEvent mouseEvent) {
        if (tenDip.getText().isEmpty() || phanThuong05.getText().isEmpty() || phanThuong614.getText().isEmpty() || phanThuong1517.getText().isEmpty() ||
                tien05.getText().isEmpty() || tien614.getText().isEmpty() || tien1517.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostive(tien05.getText()) || !Utility.isPostive(tien614.getText()) || !Utility.isPostive(tien1517.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else {
                if (!tenDip.getText().equals(tenGoc) && dipDacBietImpl.traCuuDipByTenNam(Integer.parseInt(namHoc.getText()), tenDip.getText()) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiTenDip);
                    alert.show();
                }
                else if (Float.parseFloat(tien05.getText()) <= Float.parseFloat(tien614.getText()) || Float.parseFloat(tien614.getText()) <= Float.parseFloat(tien1517.getText())) {
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
                        lichSuHoatDong.setTenHD("Chỉnh sửa dịp đặc biệt");
                        lichSuHoatDong.setThoiGianHD(Date.valueOf(LocalDate.now()));
                        lichSuHoatDong.setIdHD(DipDacBietController.id_suaDB);
                        String noiDung = "Chỉnh sửa: ";
                        // kiểm tra các trường
                        connection = DbUtil.getInstance().getConnection();
                        preparedStatement = connection.prepareStatement(LICH_SU_DAC_BIET_QUERY_CAC_TRUONG_THEO_ID);
                        preparedStatement.setInt(1, HocSinhGioiController.id_suaHSG);
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            //idDip INT NOT NULL AUTO_INCREMENT,
                            //                             ten NVARCHAR(255) NOT NULL,
                            //                             nam INT NOT NULL,
                            //                             moTa NVARCHAR(255),
                            //                             phanQua05 NVARCHAR(255) NOT NULL,
                            //                             phanQua614 NVARCHAR(255) NOT NULL,
                            //                             phanQua1517 NVARCHAR(255) NOT NULL,
                            //                             tien05 FLOAT NOT NULL,
                            //                             tien614 FLOAT NOT NULL,
                            //                             tien1517 FLOAT NOT NULL,
                            //1 Quà 05

                            if (resultSet.getString("phanQua05").compareTo(phanThuong05.getText()) != 0) {
                                noiDung += "\nPhần quà 0-15 tuổi: "
                                        + resultSet.getString("phanQua05") + " => " + phanThuong05.getText();
                            }

                            //2 Tiền 05

                            if (resultSet.getFloat("tien05")!=Float.parseFloat(tien05.getText()) ) {
                                noiDung += "\nGiá tiên quà 0-15 tuổi: "
                                        + Float.toString(resultSet.getFloat("tien05")) + " => " + tien05.getText();
                            }

                            //3 Qùa 614

                            if (resultSet.getString("phanQua614").compareTo(phanThuong614.getText()) != 0) {
                                noiDung += "\nPhần quà 6-14 tuổi: "
                                        + resultSet.getString("phanQua614") + " => " + phanThuong614.getText();
                            }

                            //4 Tiền 614

                            if (resultSet.getFloat("tien614")!=Float.parseFloat(tien614.getText())) {
                                noiDung += "\nGiá tiên quà 6-14 tuổi: "
                                        + Float.toString(resultSet.getFloat("tien614")) + " => " + tien614.getText();
                            }

                            //5 Qùa 1517

                            if (resultSet.getString("phanQua1517").compareTo(phanThuong1517.getText()) != 0) {
                                noiDung += "\nPhần quà 15-17 tuổi: "
                                        + resultSet.getString("phanQua1517") + " => " + phanThuong1517.getText();
                            }

                            //6 Tiền 1517

                            if (resultSet.getFloat("tien1517")!=Float.parseFloat(tien1517.getText())) {
                                noiDung += "\nGiá tiên quà 15-17 tuổi: "
                                        + Float.toString(resultSet.getFloat("tien1517")) + " => " + tien1517.getText();
                            }

                            //7 Mô tả
                            if (moTa.getText() != null && moTa.getText() != "") {
                                noiDung += "\nMô tả: "
                                        + resultSet.getString("moTa") + " => " + moTa.getText();
                            }

                        }

                        lichSuHoatDong.setNoiDungHD(noiDung);
                        lichSuHoatDongRepository.addDB(lichSuHoatDong);

                        dipDacBiet.setPhanQua05(phanThuong05.getText());
                        dipDacBiet.setPhanQua614(phanThuong614.getText());
                        dipDacBiet.setPhanQua1517(phanThuong1517.getText());
                        dipDacBiet.setTien05(Float.parseFloat(tien05.getText()));
                        dipDacBiet.setTien614(Float.parseFloat(tien614.getText()));
                        dipDacBiet.setTien1517(Float.parseFloat(tien1517.getText()));
                        dipDacBietImpl.chinhSuaThongTinDip(dipDacBiet.getIdDip(), tenDip.getText(), moTa.getText(), phanThuong05.getText(), phanThuong614.getText(),
                                phanThuong1517.getText(), Float.parseFloat(tien05.getText()), Float.parseFloat(tien614.getText()), Float.parseFloat(tien1517.getText()));
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/phanThuong/chiTietDipDacBiet.fxml"));
                        Parent p = loader.load();
                        ChiTietDipDacBietController c = loader.getController();
                        c.setDipDacBiet(dipDacBiet);
                        Utility.setStage(p);





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
