package controller.LichSu;

import entity.LichSuHoatDong;
import entity.LichSuHoatDongXoa;
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
import utility.SoundClick;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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



    @FXML
    private ComboBox truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;

    private String truongTraCuu=" ";
    private String duLieuTraCuu=" ";






 // LocalDate currentDate = LocalDate.now();
    @FXML
    private TableColumn<LichSuHoatDong, String> noiDungHD;

    @FXML
    private Pane mainPane;


    @FXML
    private Button  dsXoa;


    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();
    @FXML
    ObservableList<LichSuHoatDong>  lichSuHoatDongList = FXCollections.observableArrayList();

    int option = LichSuController.option;

    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;



    @SneakyThrows
    private void refreshTable(){
        lichSuHoatDongList = lichSuHoatDongRepository.bangLSHD(option);
        bangLSHD.setItems(lichSuHoatDongList );
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idHD.setCellValueFactory(new PropertyValueFactory<>("idHD" ));
        tenHD.setCellValueFactory(new PropertyValueFactory<>("tenHD"));
        noiDungHD.setCellValueFactory(new PropertyValueFactory<>("noiDungHD"));
        thoiGianHD.setCellValueFactory(new PropertyValueFactory<>("thoiGianHD"));

        ObservableList<String> listTruongTraCuu;
        if( option == 1)  {
            dsXoa.setVisible(true);
            idHD.setText("ID nhân khẩu");
            listTruongTraCuu = FXCollections.observableArrayList("Id nhân khẩu","Loại hoạt động","Thời gian");

        }
        else if ( option == 2){
            dsXoa.setVisible(true);
            idHD.setText("ID hộ khẩu");
            listTruongTraCuu = FXCollections.observableArrayList("Id hộ khẩu","Loại hoạt động","Thời gian");
        }
        else{
            dsXoa.setVisible(false);
            idHD.setText("ID dịp");
            listTruongTraCuu = FXCollections.observableArrayList("Id dịp trao thưởng","Loại hoạt động","Thời gian");

        }
        truongTraCuuF.setItems(listTruongTraCuu);

        refreshTable();


    }

    @FXML
    private void Select(ActionEvent event) {
        truongTraCuu = truongTraCuuF.getSelectionModel().getSelectedItem().toString();
    }



    @SneakyThrows
    @FXML
    public void findF(MouseEvent mouseEvent) {
        connection = DbUtil.getInstance().getConnection();

//        noiDungHD VARCHAR(255),
//                       tenHD VARCHAR(255) ,
//                       thoiGianHD date not null,
        lichSuHoatDongList.clear();
        duLieuTraCuu=duLieuF.getText();
        String query_loaiHD= "SELECT * FROM lich_su_hoat_dong WHERE tenHD like '%" + duLieuTraCuu + "%' and nhom = " + Integer.toString(option);
        String query_Id= "SELECT * FROM lich_su_hoat_dong WHERE idHD" + Integer.toString(option)+ " like '%" + duLieuTraCuu + "%' and nhom = " + Integer.toString(option);
        String thoiGian;

        if(truongTraCuu.contains("Thời gian") ){
            if(duLieuTraCuu!=null && duLieuTraCuu.length() <= 10){
                if( duLieuTraCuu.contains("-") ){
                    String[] a = duLieuTraCuu.split("-");
                    if (a[1].length() == 1) a[0] = "0"+a[1];
                    if (a[2].length() == 1) a[0] = "0"+a[2];
                    thoiGian = a[2] + "-" + a[1] + "-" + a[0];
                    duLieuTraCuu = thoiGian;
                }
            }
        }

        String query_thoiGian= "SELECT * FROM lich_su_hoat_dong WHERE thoiGianHD like '%" + duLieuTraCuu + "%' and nhom = " + Integer.toString(option);


        if(truongTraCuu.contains("Loại hoạt động")){
                preparedStatement = connection.prepareStatement(query_loaiHD);
            } else if(truongTraCuu.contains("Id")){
                preparedStatement = connection.prepareStatement(query_Id);
            }else if(truongTraCuu.contains("Thời gian")){
                preparedStatement = connection.prepareStatement(query_thoiGian);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Bạn cần chọn trường tra cứu");
                alert.showAndWait();
                refreshTable();
                return;
            }

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LichSuHoatDong lichSuHoatDong = new LichSuHoatDong();
                lichSuHoatDong.setIdHD(Integer.parseInt(resultSet.getString("idHD"+Integer.toString(option))));
                lichSuHoatDong.setThoiGianHD(resultSet.getDate("thoiGianHD"));
                lichSuHoatDong.setTenHD(resultSet.getString("tenHD"));
                lichSuHoatDong.setNoiDungHD(resultSet.getString("noiDungHD"));
                lichSuHoatDongList.add(0, lichSuHoatDong);
        }
        bangLSHD.setItems(lichSuHoatDongList );

    }

    public void back(MouseEvent mouseEvent) throws IOException {
        SoundClick SoundClick = new SoundClick();
        SoundClick.playSound();
        Parent lichSuPane = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSu.fxml"));
        mainPane.getChildren().add(lichSuPane);
    }



    public void dsXoaOnAction(ActionEvent event) throws IOException{
        Pane dsXoa = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSuXoa.fxml"));
        mainPane.getChildren().add(dsXoa);
    }


}
