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


public class ThongKeNhanKhau_trangThaiCuTruController implements Initializable {
    @FXML
    private Pane trangThaiCuTruPane;

    @FXML
    private ComboBox yearComboBox;



    @FXML
    private TextArea nhanXetTextArea;
    utility.SoundClick SoundClick = new SoundClick();

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

    //ve bieu do
    public void draw_chart(){
        trangThaiCuTruPane.getChildren().clear();
        int year_int = Integer.parseInt( year );
        ObservableList<PieChart.Data> dataTrangThai = FXCollections.observableArrayList(
                new PieChart.Data("Thường trú", nhanKhauRepository.tongNhanKhauThuongTru(year_int)),
                new PieChart.Data("Tạm trú", nhanKhauRepository.tongNhanKhauTamTru(year_int)),
                new PieChart.Data("Tạm vắng", nhanKhauRepository.tongNhanKhauTamVang(year_int)),
                new PieChart.Data("Không xác định", nhanKhauRepository.tongNhanKhauKhongXacDinh(year_int)),
                new PieChart.Data("Đã mất", nhanKhauRepository.tongNhanKhauDaMat(year_int)),
                new PieChart.Data("Đã chuyển đi", nhanKhauRepository.tongNhanKhauDaChuyenDi(year_int))
                );
                PieChart chartTrangThai = new PieChart(dataTrangThai);
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

        double tongNhanKhauThuongTru = (double) nhanKhauRepository.tongNhanKhauThuongTru(year_int);
        double tongNhanKhauTamTru = (double) nhanKhauRepository.tongNhanKhauTamTru(year_int);
        double tongNhanKhauTamVang = (double) nhanKhauRepository.tongNhanKhauTamVang(year_int);
        double tongNhanKhauKhongXacDinh = (double) nhanKhauRepository.tongNhanKhauKhongXacDinh(year_int);
        double tongNhanKhauDaMat = (double) nhanKhauRepository.tongNhanKhauDaMat(year_int);
        double tongNhanKhauDaChuyenDi = (double)  nhanKhauRepository.tongNhanKhauDaChuyenDi(year_int);
        double tong = tongNhanKhauThuongTru + tongNhanKhauTamTru + tongNhanKhauTamVang + tongNhanKhauKhongXacDinh + tongNhanKhauDaMat + tongNhanKhauDaChuyenDi;

        int thuongTruTile = (int) (tongNhanKhauThuongTru/tong * 100);
        int tamTruTile = (int) (tongNhanKhauTamTru/tong * 100);
        int tamVangTile = (int) (tongNhanKhauTamVang/tong * 100);
        int khongXacDinhTile = (int) (tongNhanKhauKhongXacDinh/tong * 100);
        int daMatTile = (int) (tongNhanKhauDaMat/tong * 100);
        int daChuyenDiTile = 100 - thuongTruTile-tamTruTile-tamVangTile - khongXacDinhTile-daMatTile;

        if(year_int == min_year){
            nhanXetTextArea.setText("\n"
                    + "Nhận xét: " + "\n"
                    + "%Nhân khẩu thường trú : " + Integer.toString(thuongTruTile ) + "% " + "\n"


                    + "%Nhân khẩu tạm trú : " + Integer.toString(tamTruTile) + "% " + "\n"


                    + "%Nhân khẩu tạm vắng : " + Integer.toString(tamVangTile) + "% " + "\n"

                    + "%Nhân khẩu không xác định : " + Integer.toString(khongXacDinhTile) + "% " + "\n"


                    + "%Nhân khẩu đã mất : " + Integer.toString(daMatTile) + "% " + "\n"


                    + "%Nhân khẩu đã chuyển di : " + Integer.toString(daChuyenDiTile) + "% " + "\n"


            );
        }
        else{
            double tongNhanKhauThuongTru_truoc = (double) nhanKhauRepository.tongNhanKhauThuongTru(year_int-1);
            double tongNhanKhauTamTru_truoc  = (double) nhanKhauRepository.tongNhanKhauTamTru(year_int-1);
            double tongNhanKhauTamVang_truoc  = (double) nhanKhauRepository.tongNhanKhauTamVang(year_int-1);
            double tongNhanKhauKhongXacDinh_truoc  = (double) nhanKhauRepository.tongNhanKhauKhongXacDinh(year_int-1);
            double tongNhanKhauDaMat_truoc  = (double) nhanKhauRepository.tongNhanKhauDaMat(year_int-1);
            double tongNhanKhauDaChuyenDi_truoc  = (double)  nhanKhauRepository.tongNhanKhauDaChuyenDi(year_int-1);
            double tong_truoc  = tongNhanKhauThuongTru_truoc  + tongNhanKhauTamTru_truoc  + tongNhanKhauTamVang_truoc  + tongNhanKhauKhongXacDinh_truoc  + tongNhanKhauDaMat_truoc  + tongNhanKhauDaChuyenDi_truoc ;

            int thuongTruTile_truoc  = (int) (tongNhanKhauThuongTru_truoc /tong_truoc  * 100);
            int tamTruTile_truoc  = (int) (tongNhanKhauTamTru_truoc /tong_truoc  * 100);
            int tamVangTile_truoc  = (int) (tongNhanKhauTamVang_truoc /tong_truoc  * 100);
            int khongXacDinhTile_truoc  = (int) (tongNhanKhauKhongXacDinh_truoc /tong_truoc  * 100);
            int daMatTile_truoc  = (int) (tongNhanKhauDaMat_truoc /tong_truoc  * 100);
            int daChuyenDiTile_truoc  = 100 - thuongTruTile_truoc-tamTruTile_truoc-tamVangTile_truoc - khongXacDinhTile_truoc-daMatTile_truoc;

            String thuongTruChange, tamTruChange, tamVangChange, khongXacDinhChange,daMatChange,daChuyenDiChange;
            int change1 = thuongTruTile - thuongTruTile_truoc ;
            int change2 = tamTruTile - tamTruTile_truoc;
            int change3 = tamVangTile - tamVangTile_truoc;
            int change4 = khongXacDinhTile- khongXacDinhTile_truoc;
            int change5 = daMatTile - daMatTile_truoc;
            int change6 = daChuyenDiTile - daChuyenDiTile_truoc;



            if(change1 >= 0 ) thuongTruChange = "Tăng";
            else  thuongTruChange = "Giảm";
            if(change2 >= 0 ) tamTruChange = "Tăng";
            else  tamTruChange = "Giảm";
            if(change3 >= 0 ) tamVangChange = "Tăng";
            else  tamVangChange = "Giảm";
            if(change4 >= 0 )  khongXacDinhChange = "Tăng";
            else   khongXacDinhChange = "Giảm";
            if(change5 >= 0 ) daMatChange = "Tăng";
            else  daMatChange = "Giảm";
            if(change6 >= 0 ) daChuyenDiChange = "Tăng";
            else  daChuyenDiChange = "Giảm";

            nhanXetTextArea.setText(
                    "Nhận xét: " + "\n"
                            + "%Nhân khẩu thường trú : " + Integer.toString(thuongTruTile ) + "% " + "\n"
                            + thuongTruChange + " " + Integer.toString(abs(change1)) + "% so với năm trước\n"

                            + "%Nhân khẩu tạm trú : " + Integer.toString(tamTruTile) + "% " + "\n"
                            + tamTruChange + " " + Integer.toString(abs(change2)) + "% so với năm trước\n"

                            + "%Nhân khẩu tạm vắng : " + Integer.toString(tamVangTile) + "% " + "\n"
                            + tamVangChange + " " + Integer.toString(abs(change3)) + "% so với năm trước\n"

                            + "%Nhân khẩu không xác định : " + Integer.toString(khongXacDinhTile) + "% " + "\n"
                            + khongXacDinhChange + " " + Integer.toString(abs(change4)) + "% so với năm trước\n"

                            + "%Nhân khẩu đã mất : " + Integer.toString(daMatTile) + "% " + "\n"
                            + daMatChange + " " + Integer.toString(abs(change5)) + "% so với năm trước\n"

                            + "%Nhân khẩu đã chuyển di : " + Integer.toString(daChuyenDiTile) + "% " + "\n"
                            + daChuyenDiChange + " " + Integer.toString(abs(change6)) + "% so với năm trước\n"

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
