package utility;

import entity.LichSuHoatDong;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
public class History {
    public static  ObservableList<LichSuHoatDong> lishSuHD_HoKhau = FXCollections.observableArrayList();
    public static  ObservableList<LichSuHoatDong> lishSuHD_NhanKhau = FXCollections.observableArrayList();
    public static  ObservableList<LichSuHoatDong> lishSuHD_HocSinhGioi = FXCollections.observableArrayList();
    public static  ObservableList<LichSuHoatDong> lishSuHD_DipDacBiet = FXCollections.observableArrayList();

}
