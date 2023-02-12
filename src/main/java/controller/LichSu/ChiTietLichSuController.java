package controller.LichSu;

import entity.LichSuHoatDong;
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
import repository.LichSuHoatDongRepositoryImpl;
import utility.SoundClick;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
    private ComboBox<String> tenHDComboBox;

    @FXML
    private TextField idHDTextField;

    @FXML
    private DatePicker thoiGianHDDatePicker;

    @FXML
    private Button  allButton;







 // LocalDate currentDate = LocalDate.now();
    @FXML
    private TableColumn<LichSuHoatDong, String> noiDungHD;

    @FXML
    private Pane mainPane;

    @FXML
    private Button locButton;


    LichSuHoatDongRepositoryImpl lichSuHoatDongRepository = new LichSuHoatDongRepositoryImpl();

    int option = LichSuController.option;

    public  void setOption(int option){
        lichSuHoatDongRepository.init();
        tenHDComboBox.getItems().clear();
        if(option == 1){
            tenHDComboBox.getItems().addAll(LichSuHoatDongRepositoryImpl.listHD_NK);
        }

        else if(option == 2){
            tenHDComboBox.getItems().addAll(LichSuHoatDongRepositoryImpl.listHD_HK);
        }

        else if(option == 3){
            tenHDComboBox.getItems().addAll(LichSuHoatDongRepositoryImpl.listHD_HSG);
        }

        else {
            tenHDComboBox.getItems().addAll(LichSuHoatDongRepositoryImpl.listHD_DB);

        }
        tenHDComboBox.setValue("Tất cả");

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idHD.setCellValueFactory(new PropertyValueFactory<>("idHD" ));
        tenHD.setCellValueFactory(new PropertyValueFactory<>("tenHD"));
        noiDungHD.setCellValueFactory(new PropertyValueFactory<>("noiDungHD"));
        thoiGianHD.setCellValueFactory(new PropertyValueFactory<>("thoiGianHD"));

        try {
            bangLSHD.setItems(lichSuHoatDongRepository.bangLSHD(option) );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(option == 1) idHD.setText("ID nhân khẩu");
        else if(option == 2) idHD.setText("ID hộ khẩu");
        else if(option == 3) idHD.setText("ID dịp học sinh giỏi");
        else idHD.setText("ID dịp đặc biệt");

        setOption(option);

    }

    public void back(MouseEvent mouseEvent) throws IOException {
        SoundClick SoundClick = new SoundClick();
        SoundClick.playSound();
        Parent lichSuPane = FXMLLoader.load(getClass().getResource("/view/lichSu/lichSu.fxml"));
        mainPane.getChildren().add(lichSuPane);
    }

    public  void  locButtonOnAction(ActionEvent event) throws SQLException{
        bangLSHD.getItems().clear();
        String tenHD = tenHDComboBox.getValue();
        String idHD = idHDTextField.getText();
        LocalDate d = thoiGianHDDatePicker.getValue();
        String thoiGianHD = null;
        if(d!=null) thoiGianHD = Date.valueOf(d).toString();

        ObservableList<LichSuHoatDong> list = FXCollections.observableArrayList();
        if( tenHD == "Tất cả"){
            //cả 2 truong
            if(  idHD.matches("-?\\d+(\\.\\d+)?") &&  thoiGianHD!=null){
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getIdHD() == Integer.parseInt(idHD) &&  l.getThoiGianHD().toString().compareTo(thoiGianHD) == 0 ) list.add(l);
                }
            }
            //truong ID
            else if( idHD.matches("-?\\d+(\\.\\d+)?")) {
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getIdHD() == Integer.parseInt(idHD)) list.add(l);
                }
            }
            //truong TG
            else if(thoiGianHD!=null) {
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if ( l.getThoiGianHD().toString().compareTo(thoiGianHD) == 0  ) list.add(l);
                }
            }
            else list = lichSuHoatDongRepository.bangLSHD(option);
            bangLSHD.setItems(list);
        }

        else {
            //cả 2 truong
            if(  idHD.matches("-?\\d+(\\.\\d+)?") && thoiGianHD!=null){
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getTenHD().toLowerCase().contains(tenHD.toLowerCase()) && l.getIdHD() == Integer.parseInt(idHD) &&  l.getThoiGianHD().toString().compareTo(thoiGianHD) == 0  ) list.add(l);
                }
            }
            //truong ID
            else if( idHD.matches("-?\\d+(\\.\\d+)?") ) {
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getTenHD().toLowerCase().contains(tenHD.toLowerCase()) && l.getIdHD() == Integer.parseInt(idHD)) list.add(l);
                }
            }
            //truong TG
            else if(thoiGianHD!=null) {
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getTenHD().toLowerCase().contains(tenHD.toLowerCase()) &&  l.getThoiGianHD().toString().compareTo(thoiGianHD) == 0  ) list.add(l);
                }
            }
            else {
                for (LichSuHoatDong l : lichSuHoatDongRepository.bangLSHD(option)) {
                    if (l.getTenHD().toLowerCase().contains(tenHD.toLowerCase())) list.add(l);
                }
            }
            bangLSHD.setItems(list);
        }
    }

    public void allButtonOnAction(ActionEvent event) throws SQLException{
        bangLSHD.getItems().clear();
        tenHDComboBox.setValue("Tất cả");
        idHDTextField.clear();
        thoiGianHDDatePicker.setValue(null);
        bangLSHD.setItems(lichSuHoatDongRepository.bangLSHD(option) );
    }



}
