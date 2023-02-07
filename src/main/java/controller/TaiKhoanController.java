package controller;
import controller.nhanKhau.ThongTinNhanKhauController;
import entity.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.Message;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.Optional;

import entity.NguoiDung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utility.DbUtil;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.NGUOI_DUNG_QUERY_LAY_THONG_TIN;
import repository.TaiKhoanRepositoryImpl;
import repository.NguoiDungRepositoryImpl;
import controller.NguoiDungController;


public class TaiKhoanController {
    @FXML
    private Button anhDaiDienButton;

    @FXML
    private ImageView anhDaiDienImageView;

    @FXML
    private Label hoTenLabel;

    @FXML
    private Label gioiTinhLabel;

    @FXML
    private Label ngaySinhLabel;

    @FXML
    private Label SDTLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label chucVuLabel;

    @FXML
    private Label donViCongTacLabel;

    private int id_ND;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;




    //chinh sua anh dai dien
    TaiKhoanRepositoryImpl taiKhoanRepositoryImpl = new TaiKhoanRepositoryImpl();
    public void taiAnhDaiDien(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        if (file != null) {
            String mimetype = Files.probeContentType(file.toPath());
            if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                String url = file.getPath();
                Image image = new Image(url);
                anhDaiDienImageView.setImage(image);
                taiKhoanRepositoryImpl.chinhSuaAnhDaiDien(url);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauDoiFileAnh);
                alert.show();
            }
        }
    }

    public void setNguoiDung()  {
        id_ND = NguoiDungRepositoryImpl.nguoiDung.getId();
        loadData();
    }
    @FXML
    private void loadData() {
        try {
            connection = DbUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(NGUOI_DUNG_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id_ND) {
                    hoTenLabel.setText(resultSet.getString("hoTen"));
                    gioiTinhLabel.setText(resultSet.getString("gioiTinh"));
                    String ngaySinh = resultSet.getString("ngaySinh").substring(8) + "-" + resultSet.getString("ngaySinh").substring(5, 7) + "-" + resultSet.getString("ngaySinh").substring(0, 4);
                    ngaySinhLabel.setText(ngaySinh);
                    gioiTinhLabel.setText(resultSet.getString("gioiTinh"));
                    emailLabel.setText(resultSet.getString("email"));
                    SDTLabel.setText(resultSet.getString("SDT"));
                    chucVuLabel.setText(resultSet.getString("chucVu"));
                    donViCongTacLabel.setText(resultSet.getString("donViCongTac"));
                    Image image = new Image(resultSet.getString("anhDaiDien"));
                    anhDaiDienImageView.setImage(image);
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(TaiKhoanController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }



}
