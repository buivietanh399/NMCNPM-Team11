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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.SoundClick;
import utility.SoundLatLich;
import utility.Message;

import static java.lang.Math.abs;
public class ThongKeHoKhau_soThanhVienController implements Initializable{
    @FXML
    private Pane soThanhVienPane;

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
        soThanhVienPane.getChildren().clear();
        // soLuong
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Số lượng thành viên");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số hộ khẩu");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Số lượng thành viên");
        for (Integer i : hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).keySet()) {
            dataSeries.getData().add(new XYChart.Data(String.valueOf(i), hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).get(i)));
        }
        barChart.getData().add(dataSeries);

        barChart.setPrefSize(600, 550);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        soThanhVienPane.getChildren().add(barChart);
    }

    public void nhanXet() {
        int year_int = Integer.parseInt(year);
        nhanXetTextArea.clear();
        String nhanxet = "\n\n\n\n\n"
                + "Nhận xét: " + "\n";

        double tong = 0;

        for (Integer i : hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).keySet()){
            tong+= (double) hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).get(i);
        }

        Set<Integer> set = hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).keySet();
        List<Integer> list = new ArrayList<>(set);
        int n = set.size();
        ArrayList<Integer> soThanhVien;
        int curr = 0;
        for (int i = 0 ; i < n - 1; i ++){
            curr+=  (int)((double)hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).get(list.get(i))/tong * 100) ;
            nhanxet += "%Hộ có " + Integer.toString(list.get(i)) + " thành viên: " + Integer.toString( (int)((double)hoKhauRepository.soLuongThanhVien(Integer.parseInt(year)).get(list.get(i))/tong * 100 )  ) +"%\n";
        }
        nhanxet+= "%Hộ có " + Integer.toString(list.get(n-1)) + " thành viên: " + Integer.toString(100-curr) +"%";
        nhanXetTextArea.setText(nhanxet);


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
