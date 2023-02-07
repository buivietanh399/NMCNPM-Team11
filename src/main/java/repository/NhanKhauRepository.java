package repository;

import java.util.ArrayList;
import java.util.List;

public interface NhanKhauRepository {
    //cho trang chủ
    public int tongNhanKhauThuongTru();
    public int tongNhanKhauDaChuyenDi();
    public int tongNhanKhauDaMat();
    public int tongNhanKhauKhongXacDinh();
    public int tongNhanKhauTamTru();
    public int tongNhanKhauTamVang();

    //cho thống kê
    public int tongNhanKhauThuongTru(int year);
    public int tongNhanKhauDaChuyenDi(int year);
    public int tongNhanKhauDaMat(int year);
    public int tongNhanKhauKhongXacDinh(int year);
    public int tongNhanKhauTamTru(int year);
    public int tongNhanKhauTamVang(int year);

    //gioitinh
    public int tongNam(int year);
    public int tongNu(int year);

    //dotuoi
    public int tongDuoiLD(int year);
    public int tongLD(int year);
    public int tongTrenLD(int year);

    public ArrayList<String> listYear();
}
