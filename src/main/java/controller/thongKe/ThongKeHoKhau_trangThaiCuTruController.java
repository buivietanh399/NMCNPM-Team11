package controller.thongKe;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import repository.HoKhauRepository;
import repository.HoKhauRepositoryImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.SoundClick;
import utility.SoundLatLich;
import utility.Message;

import static java.lang.Math.abs;
public class ThongKeHoKhau_trangThaiCuTruController implements Initializable{
    @FXML
    private Pane trangThaiCuTruPane;

    @FXML
    private ComboBox yearComboBox;



    @FXML
    private TextArea nhanXetTextArea;
    utility.SoundClick SoundClick = new SoundClick();

    NhanKhauRepositoryImpl nhanKhauRepository = new NhanKhauRepositoryImpl();
    HoKhauRepositoryImpl hoKhauRepository = new HoKhauRepositoryImpl();

    private  String year ;

    public final static int MAX_YEAR = 2023;
    int  min_year = 2023;


    //select cac nam
    public  void setOptions(){
        ArrayList<String> listYear = nhanKhauRepository.listYear();
        yearComboBox.getItems().clear();
        int n = listYear.size();
        for( int i = 0 ; i < n; i++){
            if( Integer.parseInt(listYear.get(i)) < min_year) min_year = Integer.parseInt(listYear.get(i));
        }
        for( int i = min_year ; i <= MAX_YEAR; i++){
            MenuItem menuItem = new MenuItem(Integer.toString(i));
            yearComboBox.getItems().add(Integer.toString(i));
        }
    }

    //ve bieu do
    public void draw_chart(){
        trangThaiCuTruPane.getChildren().clear();
        int year_int = Integer.parseInt( year );
        ObservableList<PieChart.Data> dataTrangThai = FXCollections.observableArrayList(
                new PieChart.Data("Thường trú", hoKhauRepository.tongHoKhauThuongTru(Integer.parseInt(year))),
                new PieChart.Data("Đã chuyển đi", hoKhauRepository.tongHoKhauDaChuyenDi(Integer.parseInt(year)))
        );
        final PieChart chartTrangThai = new PieChart(dataTrangThai);
        dataTrangThai.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartTrangThai.setPrefSize(600, 550);
        chartTrangThai.setLegendVisible(false);
        trangThaiCuTruPane.getChildren().add(chartTrangThai);
    }

    public void nhanXet(){
        int year_int = Integer.parseInt(year);
        nhanXetTextArea.clear();

        double tongHoKhauThuongTru = (double) hoKhauRepository.tongHoKhauThuongTru(year_int);
        double tongHoKhauDaChuyenDi = (double)  hoKhauRepository.tongHoKhauDaChuyenDi(year_int);
        double tong = tongHoKhauThuongTru +  tongHoKhauDaChuyenDi;

        int thuongTruTile = (int) (tongHoKhauThuongTru/tong * 100);
        int daChuyenDiTile = 100 - thuongTruTile;

        if(year_int == min_year){
            nhanXetTextArea.setText("\n\n\n\n\n"
                    + "Nhận xét: " + "\n"
                    + "%Hộ khẩu thường trú : " + Integer.toString(thuongTruTile ) + "% " + "\n"
                    + "%Nhân khẩu đã chuyển di : " + Integer.toString(daChuyenDiTile) + "% " + "\n"
            );
        }
        else{
            double tongHoKhauThuongTru_truoc = (double) hoKhauRepository.tongHoKhauThuongTru(year_int-1);
            double tongHoKhauDaChuyenDi_truoc  = (double)  hoKhauRepository.tongHoKhauDaChuyenDi(year_int-1);
            double tong_truoc  = tongHoKhauThuongTru_truoc   + tongHoKhauDaChuyenDi_truoc ;

            int thuongTruTile_truoc  = (int) (tongHoKhauThuongTru_truoc /tong_truoc  * 100);
            int daChuyenDiTile_truoc  = 100 - thuongTruTile_truoc;

            String thuongTruChange,daChuyenDiChange;
            int change1 = thuongTruTile - thuongTruTile_truoc ;
            int change2 = daChuyenDiTile - daChuyenDiTile_truoc;



            if(change1 >= 0 ) thuongTruChange = "Tăng";
            else  thuongTruChange = "Giảm";
            if(change2 >= 0 ) daChuyenDiChange = "Tăng";
            else  daChuyenDiChange = "Giảm";

            nhanXetTextArea.setText(
                    "Nhận xét: " + "\n\n\n\n\n"
                            + "%Hộ khẩu thường trú : " + Integer.toString(thuongTruTile ) + "% " + "\n"
                            + thuongTruChange + " " + Integer.toString(abs(change1)) + "% so với năm trước\n"
                            + "%Hộ khẩu đã chuyển di : " + Integer.toString(daChuyenDiTile) + "% " + "\n"
                            + daChuyenDiChange + " " + Integer.toString(abs(change2)) + "% so với năm trước\n"

            );

        }
    }
    public void yearComboBoxOnAction(ActionEvent event) {
        year = yearComboBox.getSelectionModel().getSelectedItem().toString();
        draw_chart();
        nhanXet();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setOptions();
    }
}
