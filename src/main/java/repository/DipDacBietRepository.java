package repository;

import entity.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DipDacBietRepository {
    ObservableList<DipDacBiet> bangDipDacBiet();
    ObservableList<DipDacBiet> traCuuDipDacBiet(String ten, Integer nam);
    DipDacBiet traCuuDipByTenNam(int nam, String ten);
    void taoDipDacBiet(String ten, int nam, String moTa, String phanQua05, String phanQua614, String phanQua1517, float tien05, float tien614, float tien1517);
    void xoaDipDacBiet(int idDip);
    void chinhSuaThongTinDip(int idDip, String tenDip, String moTa, String phanQua05, String phanQua614, String phanQua1517, float tien05, float tien614, float tien1517);
    ObservableList<NhanKhauDipDacBiet> bangNhanThuong(int idDip);
    ObservableList<NhanKhauDipDacBiet> traCuuNhanThuong(String ten, int nhom);
    void kiemTraTraoThuong(int idDip, int idNhanKhau, boolean kiemTra);

    ObservableList<DipDacBiet> namDipDacBiet(Integer nam) throws SQLException;

    ObservableList<DipDacBiet> tenDipDacBiet(String ten) throws SQLException;

    ObservableList<DipDacBiet> namTenDipDacBiet(Integer nam, String ten) throws SQLException;

    int t05Nguoi(Integer idDip);

    int t614Nguoi(Integer idDip);

    int t1517Nguoi(Integer idDip);

    int nguoiDaTrao(Integer idDip);

    int nguoiChuaTrao(Integer idDip);

    int hoDaTrao(Integer idDip);

    int hoChuaTrao(Integer idDip);

    //new - cho thống kê
    ObservableList<DipDacBiet> timNam(Integer nam) throws SQLException;

    public ArrayList<Integer> listYear();

    int idMax();


    //cho thống kê
    //1. Các hộ đã trao
    public ObservableList<HoDaTrao_DB> bangHoDaTrao(int idDip) throws SQLException;

    public int tongTien_datrao(int idDip) throws SQLException;


    //2. Các hộ chưa trao
    public ObservableList<HoChuaTrao_DB> bangHoChuaTrao(int idDip) throws SQLException;

    public int tongTien_chuatrao(int idDip) throws SQLException;

    //3. Chi tiét
    public ObservableList<ChiTiet_TraoThuong_DB> bangNhanKhauDaTrao(int idDip, int idHo) throws SQLException;
    public ObservableList<ChiTiet_TraoThuong_DB> bangNhanKhauChuaTrao(int idDip, int idHo) throws SQLException;


}
