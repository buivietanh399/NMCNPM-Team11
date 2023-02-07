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

public class ThongKeNhanKhau_gioiTinhController implements Initializable{

    @FXML
    private Pane gioiTinhPane;

    @FXML
    private ComboBox yearComboBox;


    @FXML
    private Label thongBaoLabel;

    @FXML
    private TextArea nhanXetTextArea;
    SoundClick SoundClick = new SoundClick();

    NhanKhauRepositoryImpl nhanKhauRepository = new NhanKhauRepositoryImpl();


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



    //ve bieu do gioi tinh
    public void draw_chart(){
        gioiTinhPane.getChildren().clear();
        if ( !year.equals("")){
            ObservableList<PieChart.Data> dataGioiTinh = FXCollections.observableArrayList(
                    new PieChart.Data("Nam", nhanKhauRepository.tongNam( Integer.parseInt( year ) ) ) ,
                    new PieChart.Data("Nữ", nhanKhauRepository.tongNu( Integer.parseInt( year ) ) )
            );
            PieChart chartGioiTinh = new PieChart(dataGioiTinh);
            dataGioiTinh.forEach(data ->
                    data.nameProperty().bind((
                            Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                    )));
            chartGioiTinh.setPrefSize(600, 600);

            //System.out.println(chartGioiTinh.getLayoutX());
            //System.out.println(chartGioiTinh.getLayoutY());
            chartGioiTinh.setLegendVisible(false);
            gioiTinhPane.getChildren().add(chartGioiTinh);

        }

    }

    public void nhanXet(){
        //System.out.println("nhan xet");
        int year_int = Integer.parseInt(year);
        nhanXetTextArea.clear();
        if(year_int == min_year){
            double tongNam = (double) nhanKhauRepository.tongNam( Integer.parseInt( year ) );
            double tongNu = (double) nhanKhauRepository.tongNu( Integer.parseInt( year ) );
            double tong = tongNu + tongNam;
            int namTile = (int) (tongNam/tong * 100);
            int nuTile = 100-namTile;
            nhanXetTextArea.setText("\n\n\n\n\n\n"
                    + "Nhận xét: " + "\n"
                    + "%Nam : " + Integer.toString(namTile) + "% " + "\n"
                    + "%Nữ  : " + Integer.toString(nuTile) + "% " + "\n"
            );
        }
        else{
            double tongNam = (double) nhanKhauRepository.tongNam( Integer.parseInt( year ) );
            double tongNu = (double) nhanKhauRepository.tongNu( Integer.parseInt( year ) );
            double tong = tongNu + tongNam;
            int namTile = (int) (tongNam/tong * 100);
            int nuTile = 100-namTile;
            double tongNam_truoc = (double) nhanKhauRepository.tongNam( year_int - 1 );
            double tongNu_truoc = (double) nhanKhauRepository.tongNu( year_int - 1 );
            double tong_truoc = tongNam_truoc + tongNu_truoc;
            int namTile_truoc = (int) (tongNam_truoc/tong_truoc * 100 );
            int nuTile_truoc = 100-namTile_truoc;
            int namChange_double = namTile - namTile_truoc, nuChange_double = nuTile - nuTile_truoc;
            String namChange, nuChange;

            if ( namTile > namTile_truoc ) namChange = "Tăng";
            else if (namTile == namTile_truoc ) namChange = "Không đổi";
            else namChange = "Giảm";

            if ( nuTile > nuTile_truoc ) nuChange = "Tăng";
            else if (nuTile == nuTile_truoc ) nuChange = "Không đổi";
            else nuChange = "Giảm";

            nhanXetTextArea.setText("\n\n\n\n\n\n"
                    + "Nhận xét: " + "\n"
                    + "% Nam : " + Integer.toString(namTile) + "% " + "\n"
                    + namChange + " " + Integer.toString(abs(namChange_double)) + "% so với năm trước\n"
                    + "% Nữ: " + Integer.toString(nuTile) + "% " + "\n"
                    + nuChange + " " + Integer.toString(abs(nuChange_double)) + "% so với năm trước"
            );

        }

    }
    @FXML
    public void yearComboBoxOnAction(ActionEvent event) {
        year = yearComboBox.getSelectionModel().getSelectedItem().toString();
        draw_chart();
        nhanXet();
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nhanXetTextArea.setStyle("-fx-text-fill: black;");
        setOptions();
    }




}
