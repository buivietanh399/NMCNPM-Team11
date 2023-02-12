package repository;

import entity.ChiTiet_TraoThuong_HSG;
import entity.LichSuHoatDong;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface LichSuHoatDongRepository {
    public void addNK(LichSuHoatDong l);
    public void addHK(LichSuHoatDong l);
    public void addHSG(LichSuHoatDong l);
    public void addDB(LichSuHoatDong l);

    public ObservableList<LichSuHoatDong> bangLSHD(int nhom) throws SQLException;


}
