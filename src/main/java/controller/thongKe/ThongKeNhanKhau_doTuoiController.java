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
public class ThongKeNhanKhau_doTuoiController implements Initializable {
    @FXML
    private Pane doTuoiPane;

    @FXML
    private ComboBox yearComboBox;

    @FXML
    public ComboBox yearStartComboBox;

    @FXML
    public ComboBox yearEndComboBox;

    @FXML
    private Label thongBaoLabel;

    @FXML
    private TextArea nhanXetTextArea;
    SoundClick SoundClick = new SoundClick();

    NhanKhauRepositoryImpl nhanKhauRepository = new NhanKhauRepositoryImpl();


    private  String year ;

    public final static int MAX_YEAR = 2023;
    int  min_year = 2023;

    //select cac nam hien co
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
    public  void draw_chart(){
        doTuoiPane.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Lớp tuổi");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số nhân khẩu");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Dưới tuổi lao động");
        dataSeries1.getData().add(new XYChart.Data("", nhanKhauRepository.tongDuoiLD( Integer.parseInt(year) )));

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Tuổi lao động");
        dataSeries2.getData().add(new XYChart.Data(""  , nhanKhauRepository.tongLD( Integer.parseInt(year) )));

        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("Trên tuổi lao động");

        dataSeries3.getData().add(new XYChart.Data(""  , nhanKhauRepository.tongTrenLD( Integer.parseInt(year) )));


        barChart.getData().add(dataSeries1);
        barChart.getData().add(dataSeries2);
        barChart.getData().add(dataSeries3);

        barChart.setPrefSize(600, 550);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        doTuoiPane.getChildren().add(barChart);
    }

    public void nhanXet(){
        //System.out.println("nhan xet");
        int year_int = Integer.parseInt(year);
        nhanXetTextArea.clear();
        if(year_int == min_year){
            double tongDuoiLD= (double) nhanKhauRepository.tongDuoiLD( year_int );
            double tongLD= (double) nhanKhauRepository.tongLD( year_int );
            double tongTrenLD = (double) nhanKhauRepository.tongTrenLD( year_int );
            double tong = tongDuoiLD + tongLD + tongTrenLD;
            int duoiLDTile = (int) (tongDuoiLD/tong * 100);
            int LDTile = (int) (tongLD/tong * 100);
            int trenLDTile = (int) (tongTrenLD/tong * 100);
            nhanXetTextArea.setText("\n\n\n\n\n\n"
                    + "Nhận xét: " + "\n"
                    + "%Nhóm dưới tuổi lao động : " + Integer.toString(duoiLDTile) + "% " + "\n"
                    + "%Nhóm tuổi lao động      : " + Integer.toString(LDTile) + "% " + "\n"
                    + "%Nhóm trên tuổi lao động : " + Integer.toString(trenLDTile) + "% " + "\n"
            );
        }
        else{
            double tongDuoiLD= (double) nhanKhauRepository.tongDuoiLD( year_int );
            double tongLD= (double) nhanKhauRepository.tongLD( year_int );
            double tongTrenLD = (double) nhanKhauRepository.tongTrenLD( year_int );
            double tong = tongDuoiLD + tongLD + tongTrenLD;
            int duoiLDTile = (int) (tongDuoiLD/tong * 100);
            int LDTile = (int) (tongLD/tong * 100);
            int trenLDTile = 100 - duoiLDTile - LDTile;

            double tongDuoiLD_truoc= (double) nhanKhauRepository.tongDuoiLD( year_int - 1 );
            double tongLD_truoc = (double) nhanKhauRepository.tongLD( year_int - 1 );
            double tongTrenLD_truoc = (double) nhanKhauRepository.tongTrenLD( year_int - 1);
            double tong_truoc = tongDuoiLD_truoc + tongLD_truoc + tongTrenLD_truoc;
            int duoiLDTile_truoc = (int) (tongDuoiLD/tong_truoc * 100);
            int LDTile_truoc = (int) (tongLD/tong_truoc * 100);
            int trenLDTile_truoc = 100 - duoiLDTile_truoc - LDTile_truoc;

            String duoiLDChange, LDChange, trenLDChange;
            int change1 = duoiLDTile - duoiLDTile_truoc , change2 = LDTile - LDTile_truoc, change3 = trenLDTile - trenLDTile_truoc;

            if(change1 >= 0 ) duoiLDChange = "Tăng";
            else  duoiLDChange = "Giảm";

            if(change2 >= 0 ) LDChange = "Tăng";
            else  LDChange = "Giảm";

            if(change3 >= 0 ) trenLDChange = "Tăng";
            else  trenLDChange = "Giảm";

            nhanXetTextArea.setText("\n\n\n\n\n\n"
                    + "Nhận xét: " + "\n"
                    + "%Nhóm dưới tuổi lao động : " + Integer.toString(duoiLDTile) + "% " + "\n"
                    + duoiLDChange + " " + Integer.toString(abs(change1)) + "% so với năm trước\n"
                    + "%Nhóm tuổi lao động      : " + Integer.toString(LDTile) + "% " + "\n"
                    + LDChange + " " + Integer.toString(abs(change2)) + "% so với năm trước\n"
                    + "%Nhóm trên tuổi lao động : " + Integer.toString(trenLDTile) + "% " + "\n"
                    + trenLDChange + " " + Integer.toString(abs(change3)) + "% so với năm trước\n"
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
