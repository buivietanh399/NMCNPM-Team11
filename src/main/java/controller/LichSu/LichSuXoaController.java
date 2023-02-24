package controller.LichSu;

import entity.HoKhau;
import entity.LichSuHoatDongXoa;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import repository.LichSuHoatDongRepositoryImpl;
import utility.DbUtil;
import utility.SQLCommand;
import utility.SoundClick;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static utility.SQLCommand.*;

public class LichSuXoaController implements Initializable {
    @FXML
    private Pane mainPane;

    @FXML
    private TableView<LichSuHoatDongXoa> table;
    @FXML
    private TableColumn<LichSuHoatDongXoa,Integer> idColumn;
    @FXML
    private TableColumn<LichSuHoatDongXoa,String>  hoTenColumn;

    @FXML
    private TableColumn<LichSuHoatDongXoa,String>  CMNDColumn;
    @FXML
    private TableColumn<LichSuHoatDongXoa, LocalDate>  thoiGianColumn;

    @FXML
    private ComboBox truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;



    @FXML
    ObservableList<LichSuHoatDongXoa>  lichSuHoatDongXoaList = FXCollections.observableArrayList();
    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();

    private String query = null;
    private String query_hoTen=null;
    private String query_CMND=null;
    private String query_thoiGian=null;
    private String query_Id=null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    NhanKhau nhanKhau = null ;
    private String truongTraCuu=" ";
    private String duLieuTraCuu=" ";

    int option = LichSuController.option;

    @SneakyThrows
    private void refreshTable(){
        lichSuHoatDongXoaList = lichSuHoatDongRepository.bangLSHD_Xoa(option);
        table.setItems(lichSuHoatDongXoaList);

    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idHD" ));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        thoiGianColumn.setCellValueFactory(new PropertyValueFactory<>("thoiGianHD"));
        ObservableList<String> listTruongTraCuu;
        if( option == 1)  {
            listTruongTraCuu = FXCollections.observableArrayList("Id","Họ tên","Chứng minh nhân dân","Thời gian");
            idColumn.setText("Id Nhân khẩu");
            hoTenColumn.setText("Họ tên Nhân khẩu");
            CMNDColumn.setText("CMND Nhân khẩu");
        }
        else{
            listTruongTraCuu = FXCollections.observableArrayList("Id","Họ tên chủ hộ","Chứng minh nhân dân chủ hộ","Thời gian");
            idColumn.setText("Id Hộ khẩu");
            hoTenColumn.setText("Họ tên chủ hộ");
            CMNDColumn.setText("CMND chủ hộ");
        }
        truongTraCuuF.setItems(listTruongTraCuu);
        refreshTable();
    }

    @FXML
    private void Select(ActionEvent event) {
        truongTraCuu = truongTraCuuF.getSelectionModel().getSelectedItem().toString();
    }



    /*Timf kieem*/
    @SneakyThrows
    @FXML
    public void findF(MouseEvent mouseEvent) {
        connection = DbUtil.getInstance().getConnection();

        lichSuHoatDongXoaList.clear();
        duLieuTraCuu=duLieuF.getText();
        query_hoTen="SELECT * FROM 'lich_su_hoat_dong_xoa' WHERE hoTen like '%" + duLieuTraCuu+"%' and nhom = " + Integer.toString(option);;
        query_CMND="SELECT * FROM 'lich_su_hoat_dong_xoa' WHERE cmnd like '%" + duLieuTraCuu+"%'  and nhom = " + Integer.toString(option);;
        query_Id="SELECT * FROM 'lich_su_hoat_dong_xoa' WHERE idHD like '%" + duLieuTraCuu+"%'  and nhom = " + Integer.toString(option);;

        String thoiGian;
        if(truongTraCuu.contains("Thời gian") ){
            if(duLieuTraCuu!=null && duLieuTraCuu.length() <= 10){
                if( duLieuTraCuu.contains("-") && !duLieuTraCuu.contains("/")){
                    String[] a = duLieuTraCuu.split("-");
                    if (a[1].length() == 1) a[0] = "0"+a[1];
                    if (a[2].length() == 1) a[0] = "0"+a[2];
                    thoiGian = a[2] + "-" + a[1] + "-" + a[0];
                    duLieuTraCuu = thoiGian;

                }
                else if ( !duLieuTraCuu.contains("-") && duLieuTraCuu.contains("/")){
                    String[] a = duLieuTraCuu.split("/");
                    if (a[1].length() == 1) a[0] = "0"+a[1];
                    if (a[2].length() == 1) a[0] = "0"+a[2];
                    thoiGian = a[2] + "-" + a[1] + "-" + a[0];
                    duLieuTraCuu = thoiGian;
                }
            }
        }

        query_thoiGian="SELECT * FROM 'lich_su_hoat_dong_xoa' WHERE thoiGianHD like '%" + duLieuTraCuu+"%' and nhom = " + Integer.toString(option);;


//        "Id","Họ tên","Chứng minh nhân dân","Thời gian"
        if(truongTraCuu.contains("Họ tên")){
                preparedStatement = connection.prepareStatement(query_hoTen);
            } else if(truongTraCuu.contains("Chứng minh nhân dân")){
                preparedStatement = connection.prepareStatement(query_CMND);
            }else if(truongTraCuu.contains("Id")){
                preparedStatement = connection.prepareStatement(query_Id);
            }else if(truongTraCuu.contains("Thời gian")){
                preparedStatement = connection.prepareStatement(query_thoiGian);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Bạn cần chọn trường tra cứu");
                alert.showAndWait();
                return;
            }

            resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            LichSuHoatDongXoa lichSuHoatDongXoa = new LichSuHoatDongXoa();
            lichSuHoatDongXoa.setCMND(resultSet.getString("cmnd"));
            lichSuHoatDongXoa.setHoTen(resultSet.getString("hoTen"));
            lichSuHoatDongXoa.setThoiGianHD(resultSet.getDate("thoiGianHD"));
            lichSuHoatDongXoa.setNoiDungHDXoa(resultSet.getString("noiDungHDXoa"));
            lichSuHoatDongXoa.setIdHD(resultSet.getInt("idHD"));
            lichSuHoatDongXoa.setNhom(resultSet.getInt("nhom"));
            lichSuHoatDongXoaList.add(0, lichSuHoatDongXoa);

        }
                table.setItems(lichSuHoatDongXoaList);
    }



    //hoan tac
    @SneakyThrows
    public void khoiPhuc(ActionEvent e) throws IOException{
        connection = DbUtil.getInstance().getConnection();
        LichSuHoatDongXoa selected = table.getSelectionModel().getSelectedItem();
        if(selected == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không có đói tượng nào được chọn");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        //nhan_khau
        if( option == 1){
//
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Khôi phục nhân khẩu");
                alert.setHeaderText("Xác nhận khôi phục");
            alert.setContentText("Bạn có thực sự muốn khôi phục nhân khẩu này ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                //lấy nội dung lưu trữ
                String noiDung = selected.getNoiDungHDXoa();

                String[] data = noiDung.split("\\r?\\n");
                preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_INSERT_NHANKHAU);
                for ( int i = 0 ;i < data.length; i++){
                    if(data[i]!=null) preparedStatement.setString(i+1, data[i]);
                    else preparedStatement.setString(i+1, " ");
                }
                preparedStatement.execute();

                //xóa khỏi bộ nhớ backup
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_NHAN_KHAU_XOA);
                preparedStatement.setString(1, selected.getCMND());
                preparedStatement.execute();

                refreshTable();

            }
            else if (option.get() == ButtonType.CANCEL){

            }



        }
        //             idHoKhau INT NOT NULL AUTO_INCREMENT,
//                        idChuHo INT NOT NULL,
//                        tinhThanhPho NVARCHAR(255) NOT NULL,
//                        quanHuyen NVARCHAR(255) NOT NULL,
//                        phuongXa NVARCHAR(255) NOT NULL,
//                        diaChi NVARCHAR(255) NOT NULL,
//                        ngayTao DATE NOT NULL,
//                        trangThai NVARCHAR(255) NOT NULL,
        //ho_khau
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Khôi phục hộ khẩu");
            alert.setHeaderText("Xác nhận khôi phục");
            alert.setContentText("Bạn có thực sự muốn khôi phục hộ khẩu khẩu này ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                //lấy nội dung lưu trữ

                //thêm ho khau
                String noiDung = selected.getNoiDungHDXoa();
                String[] data = noiDung.split("\\r?\\n");

                preparedStatement = connection.prepareStatement(LICH_SU_KHOI_PHUC_HO_KHAU);
                preparedStatement.setInt(1, Integer.parseInt(data[0]));
                for ( int i = 1; i <= 6; i++){
                    preparedStatement.setString(i+1, data[i]);
                }
                preparedStatement.execute();

                //thêm hk-nk nếu có
                if( data.length == 8){
                    int id_moi = 0;
                    preparedStatement = connection.prepareStatement(HO_KHAU_MAX);
                    resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        id_moi = resultSet.getInt(1);
                    }
                    String[] listNhanKhau = data[7].split(",");

                    for(String nhanKhau: listNhanKhau){
                        String[] id_quanhe = nhanKhau.split("-");
                        //ktra xem thành viên đã bị xóa chưa
                        int check = -1;
                        preparedStatement = connection.prepareStatement(LICH_SU_CHECK_THANH_VIEN_DA_XOA_CHUA);
                        preparedStatement.setInt(1, Integer.parseInt(id_quanhe[0]));
                        resultSet = preparedStatement.executeQuery();
                        while(resultSet.next()){
                            check = resultSet.getInt(1);
                        }

                        //Nếu chưa bị xóa
                        if( check != -1){
                            //HO_KHAU_MAX
                            preparedStatement = connection.prepareStatement(HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_MOI);
                            preparedStatement.setInt(1, id_moi);
                            preparedStatement.setInt(2, Integer.parseInt(id_quanhe[0]));
                            preparedStatement.setString(3, id_quanhe[1]);
                            preparedStatement.execute();
                        }
                    }
                }

                //xóa khỏi bộ nhớ backup
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_HO_KHAU_XOA);
                preparedStatement.setString(1, selected.getCMND());
                preparedStatement.execute();

                refreshTable();

            }
            else if (option.get() == ButtonType.CANCEL){
            }

        }
    }


    //xóa vĩnh viễn thông tin
    @SneakyThrows
    public void xoaVinhVien(ActionEvent e) throws IOException{
        connection = DbUtil.getInstance().getConnection();
        LichSuHoatDongXoa selected = table.getSelectionModel().getSelectedItem();
        if(selected == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không có đói tượng nào được chọn");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        //nhan_khau
        if( option == 1){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa vĩnh viễn nhân khẩu");
            alert.setHeaderText("Bạn có muốn xóa vĩnh viễn nhân khẩu ?");
            alert.setContentText("Thông tin về nhân khẩu này sẽ bị xóa vĩnh viễn và không thể khôi phục");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_NHAN_KHAU_XOA);
                preparedStatement.setString(1, selected.getCMND());
                preparedStatement.execute();
                refreshTable();
            }
            else if (option.get() == ButtonType.CANCEL){
            }


        }

        //hộ khâu
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa vĩnh viễn hộ khẩu");
            alert.setHeaderText("Bạn có muốn xóa vĩnh viễn hộ khẩu ?");
            alert.setContentText("Thông tin về hộ khẩu này sẽ bị xóa vĩnh viễn và không thể khôi phục");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_HO_KHAU_XOA);
                preparedStatement.setString(1, selected.getCMND());
                preparedStatement.execute();
                refreshTable();

            }
            else if (option.get() == ButtonType.CANCEL){
            }

        }
    }

    //xóa bộ nhớ backup
    @SneakyThrows
    public void xoaToanBo(ActionEvent e) throws IOException{
        connection = DbUtil.getInstance().getConnection();
        if( option == 1){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa toàn bộ");
            alert.setHeaderText("Xác nhận xóa toàn bộ");
            alert.setContentText("Thông tin về toàn bộ nhân khẩu đã xóa sẽ bị xóa vĩnh viễn và không thể khôi phục");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_ALL_NHAN_KHAU_XOA);
                preparedStatement.execute();
                refreshTable();
            }
            else if (option.get() == ButtonType.CANCEL){
            }



        }
        //hộ khâu
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa toàn bộ");
            alert.setHeaderText("Xác nhận xóa toàn bộ");
            alert.setContentText("Thông tin về toàn bộ hộ khẩu đã xóa sẽ bị xóa vĩnh viễn và không thể khôi phục");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == null) {

            } else if (option.get() == ButtonType.OK) {
                preparedStatement = connection.prepareStatement(LICH_SU_DELETE_ALL_HO_KHAU_XOA);
                preparedStatement.execute();
                refreshTable();
            }
            else if (option.get() == ButtonType.CANCEL){
            }

        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        SoundClick SoundClick = new SoundClick();
        SoundClick.playSound();
        Parent lichSuPane  = FXMLLoader.load(getClass().getResource("/view/lichSu/chiTietLichSu.fxml")) ;
        mainPane.getChildren().add(lichSuPane);
    }



}
