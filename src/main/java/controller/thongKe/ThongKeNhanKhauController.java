package controller.thongKe;

import controller.TaiKhoanController;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;

import utility.SoundClick;

public class ThongKeNhanKhauController implements Initializable {

    @FXML
    private Pane mainPane;

    /*
    @FXML
    private Pane gioiTinh;

    @FXML
    private Pane trangThai;

    @FXML
    private Pane lopTuoi;
    */


    @FXML
    private ImageView gioiTinhImageView ;

    @FXML
    private ImageView  doTuoiImageView ;

    @FXML
    private ImageView  trangThaiCuTruImageView ;

    NhanKhauRepository nhanKhauRepository = new NhanKhauRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // gioiTinh
        /*ObservableList<PieChart.Data> dataGioiTinh = FXCollections.observableArrayList(
                new PieChart.Data("Nam", nhanKhauRepository.tongNam()),
                new PieChart.Data("Nữ", nhanKhauRepository.tongNu()));
        final PieChart chartGioiTinh = new PieChart(dataGioiTinh);
        dataGioiTinh.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                        )));
        chartGioiTinh.setPrefSize(300, 270);
        chartGioiTinh.setLegendVisible(false);

        gioiTinh.getChildren().add(chartGioiTinh);

        // trangThai
        ObservableList<PieChart.Data> dataTrangThai = FXCollections.observableArrayList(
                new PieChart.Data("Thường trú", nhanKhauRepository.tongNhanKhauThuongTru()),
                new PieChart.Data("Tạm trú", nhanKhauRepository.tongNhanKhauTamTru()),
                new PieChart.Data("Tạm vắng", nhanKhauRepository.tongNhanKhauTamVang()),
                new PieChart.Data("Không xác định", nhanKhauRepository.tongNhanKhauKhongXacDinh()),
                new PieChart.Data("Đã mất", nhanKhauRepository.tongNhanKhauDaMat()),
                new PieChart.Data("Đã chuyển đi", nhanKhauRepository.tongNhanKhauDaChuyenDi()));
        final PieChart chartTrangThai = new PieChart(dataTrangThai);
        dataTrangThai.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartTrangThai.setPrefSize(450, 500);
        chartTrangThai.setLegendVisible(false);

        trangThai.getChildren().add(chartTrangThai);

        // lopTuoi
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Lớp tuổi");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số người");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Lớp tuổi");
        dataSeries.getData().add(new XYChart.Data("Mầm non", nhanKhauRepository.tongMamNon()));
        dataSeries.getData().add(new XYChart.Data("Cấp 1"  , nhanKhauRepository.tongCap1()));
        dataSeries.getData().add(new XYChart.Data("Cấp 2"  , nhanKhauRepository.tongCap2()));
        dataSeries.getData().add(new XYChart.Data("Cấp 3"  , nhanKhauRepository.tongCap3()));
        dataSeries.getData().add(new XYChart.Data("Độ tuổi lao động"  , nhanKhauRepository.tongDoTuoiLaoDong()));
        dataSeries.getData().add(new XYChart.Data("Nghỉ hưu"  , nhanKhauRepository.tongNghiHuu()));
        barChart.getData().add(dataSeries);

        barChart.setPrefSize(500, 300);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lopTuoi.getChildren().add(barChart);
        */

    }

    SoundClick SoundClick = new SoundClick();

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        SoundClick.playSound();
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }


    public void gioiTinhImageViewMouseClicked(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongKe/thongKeNhanKhau_gioiTinh.fxml"));
        Parent thongKeNhanKhau_gioiTinh = loader.load();
        ThongKeNhanKhau_gioiTinhController controller= loader.getController();
        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thong ke theo gioi tinh");
        Scene scene = new Scene(thongKeNhanKhau_gioiTinh);
        stage.setScene(scene);
        stage.show();

    }
    public void doTuoiImageViewMouseClicked(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongKe/thongKeNhanKhau_doTuoi.fxml"));
        Parent thongKeNhanKhau_doTuoi = loader.load();
        ThongKeNhanKhau_doTuoiController controller= loader.getController();
        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thong ke theo do tuoi");
        Scene scene = new Scene(thongKeNhanKhau_doTuoi);
        stage.setScene(scene);
        stage.show();
    }
    public void trangThaiCuTruImageViewMouseClicked(MouseEvent mouseEvent) throws IOException {
        SoundClick.playSound();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/thongKe/thongKeNhanKhau_trangThaiCuTru.fxml"));
        Parent thongKeNhanKhau_trangThaiCuTru = loader.load();
        ThongKeNhanKhau_trangThaiCuTruController controller= loader.getController();
        Stage stage = new Stage();
//      stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thong ke theo trang thai cu tru");
        Scene scene = new Scene(thongKeNhanKhau_trangThaiCuTru);
        stage.setScene(scene);
        stage.show();
    }
}
