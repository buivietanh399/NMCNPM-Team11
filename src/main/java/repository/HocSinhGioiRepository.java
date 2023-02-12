package repository;

import entity.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HocSinhGioiRepository {
    ObservableList<DipHocSinhGioi> bangDipHocSinhGioi();
    ObservableList<DipHocSinhGioi> traCuuDipHocSinhGioi(Integer namHoc);
    void xoaDipHocSinhGioi(int idDip);
    void taoDipHocSinhGioi(int namHoc, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha);
    ObservableList<NhanKhauHokhau> bangThemMinhChung(int idDip);
    void themMinhChung(ChiTietDipHocSinhGioi chiTietDipHocSinhGioi);
    ObservableList<NhanKhauHocSinhGioi> bangNhanThuong(int idDip);
    void kiemTraTraoThuong(int idDip, int idNhanKhau, boolean kiemTra);
    void chinhSuaThongTinDip(int idDip, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha);
    DipHocSinhGioi traCuuDipByNam(int nam);
    void chinhSuaMinhChung(int idDip, int idNhanKhau, String truong, String lop, int nhom, String minhChung);
    void xoaMinhChung(int idDip, int idNhanKhau);

    ObservableList<DipHocSinhGioi> timNam(Integer nam) throws SQLException;

    //cho thong ke
    public ArrayList<Integer> listYear();

    int dacBietNguoi(Integer idDip);

    int gioiNguoi(Integer idDip);

    int khaNguoi(Integer idDip);

    int nguoiDaTrao(Integer idDip);

    int nguoiChuaTrao(Integer idDip);

    int hoDaTrao(Integer idDip);

    int hoChuaTrao(Integer idDip);

    int idMax();


    //cho thống kê
    //1. cac ho da trao
    public ObservableList<HoDaTrao_HSG> bangHoDaTrao(int idDip) throws SQLException;

    public int tongTien_datrao(int idDip) throws SQLException;

    //2. các hộ chưa trao
    public ObservableList<HoChuaTrao_HSG> bangHoChuaTrao(int idDip) throws SQLException;

    public int tongTien_chuatrao(int idDip) throws SQLException;


    //3. Chi tiết
    public ObservableList<ChiTiet_TraoThuong_HSG> bangNhanKhauDaTrao(int idDip, int idHo) throws SQLException;
    public ObservableList<ChiTiet_TraoThuong_HSG> bangNhanKhauChuaTrao(int idDip, int idHo) throws SQLException;
}
