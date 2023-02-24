package repository;

import entity.ChiTiet_TraoThuong_HSG;
import entity.LichSuHoatDong;
import entity.LichSuHoatDongXoa;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface LichSuHoatDongRepository {
    public void addNK(LichSuHoatDong l);
    public void addHK(LichSuHoatDong l);
    public void addHSG(LichSuHoatDong l);
    public void addDB(LichSuHoatDong l);

    public void addNK_Xoa(LichSuHoatDongXoa l);
    public void addHK_Xoa(LichSuHoatDongXoa l);


    public ObservableList<LichSuHoatDong> bangLSHD(int nhom) throws SQLException;

    public ObservableList<LichSuHoatDongXoa> bangLSHD_Xoa(int nhom) throws SQLException;


}
