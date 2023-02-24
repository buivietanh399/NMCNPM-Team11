package repository;

import entity.ChiTiet_TraoThuong_HSG;
import entity.LichSuHoatDong;
import entity.LichSuHoatDongXoa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LichSuHoatDongRepositoryImpl implements  LichSuHoatDongRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    @SneakyThrows
    @Override
    public void addNK(LichSuHoatDong l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_NHAN_KHAU);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getTenHD());
        pstmt.setString(3, l.getNoiDungHD());
        pstmt.setDate(4,l.getThoiGianHD());
        pstmt.execute();
    }

    @SneakyThrows
    @Override
    public void addHK(LichSuHoatDong l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_HO_KHAU);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getTenHD());
        pstmt.setString(3, l.getNoiDungHD());
        pstmt.setDate(4,l.getThoiGianHD());
        pstmt.execute();

    }

    @SneakyThrows
    @Override
    public void addHSG(LichSuHoatDong l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_HSG);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getTenHD());
        pstmt.setString(3, l.getNoiDungHD());
        pstmt.setDate(4,l.getThoiGianHD());
        pstmt.execute();


    }

    @SneakyThrows
    @Override
    public void addDB(LichSuHoatDong l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_DB);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getTenHD());
        pstmt.setString(3, l.getNoiDungHD());
        pstmt.setDate(4,l.getThoiGianHD());
        pstmt.execute();

    }
    @Override
    public ObservableList<LichSuHoatDong> bangLSHD(int nhom) throws SQLException{
        ObservableList<LichSuHoatDong> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_QUERY );
        pstmt.setInt(1, nhom);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            LichSuHoatDong LSHD = new LichSuHoatDong();
            LSHD.setIdHD(rs.getInt("idHD" + Integer.toString(nhom)));
            LSHD.setTenHD(rs.getString("tenHD"));
            LSHD.setNoiDungHD(rs.getString("noiDungHD"));
            LSHD.setThoiGianHD(rs.getDate("thoiGianHD"));
            list.add(0,LSHD);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    public static ArrayList<String> listHD_NK = new ArrayList<String>();
    public static ArrayList<String> listHD_HK = new ArrayList<String>();
    public static ArrayList<String> listHD_HSG = new ArrayList<String>();
    public static ArrayList<String> listHD_DB = new ArrayList<String>();


    public void init(){
         listHD_NK.clear();
         listHD_NK.add("Tất cả");
         listHD_NK.add("Chỉnh sửa nhân khẩu");
         listHD_NK.add("Chuyển nhân khẩu");
         listHD_NK.add("Khai tử");
         listHD_NK.add("Xem chi tiết nhân khẩu");
         listHD_NK.add("Khai báo tạm vắng");
         listHD_NK.add("Khai báo tạm trú");
         listHD_NK.add("Thêm nhân khẩu");

        listHD_HK.clear();
         listHD_HK.add("Tất cả");
         listHD_HK.add("Chuyển hộ khẩu");
         listHD_HK.add("Xem lịch sử chuyển đi");
         listHD_HK.add("Xem chi tiết hộ khẩu");
         listHD_HK.add("Chỉnh sửa hộ khẩu");
         listHD_HK.add("Tách hộ khẩu");
         listHD_HK.add("Thêm hộ khẩu");

        listHD_HSG.clear();
         listHD_HSG.add("Tất cả");
         listHD_HSG.add("Chỉnh sửa dịp");
         listHD_HSG.add("Xem chi tiết");
         listHD_HSG.add("Xem danh sách");
         listHD_HSG.add("Xóa học sinh nhận thưởng");
         listHD_HSG.add("Sửa minh chứng");
         listHD_HSG.add("Thêm học sinh nhận thưởng");
         listHD_HSG.add("Tạo dịp");

         listHD_DB.clear();
         listHD_DB.add("Tất cả");
         listHD_DB.add("Chỉnh sửa dịp");
         listHD_DB.add("Xem chi tiết");
         listHD_DB.add("Xem danh sách");
         listHD_DB.add("Tạo dịp");
    }


    //cho hoàn tác xóa đối tượng
    @SneakyThrows
    @Override
    public ObservableList<LichSuHoatDongXoa> bangLSHD_Xoa(int nhom) throws SQLException{
        ObservableList<LichSuHoatDongXoa> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_QUERY_XOA);
        pstmt.setInt(1, nhom);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            LichSuHoatDongXoa LSHD_Xoa = new LichSuHoatDongXoa();
            LSHD_Xoa.setIdHD(rs.getInt("idHD"));
            LSHD_Xoa.setNoiDungHDXoa(rs.getString("noiDungHDXoa"));
            LSHD_Xoa.setThoiGianHD(rs.getDate("thoiGianHD"));
            LSHD_Xoa.setHoTen(rs.getString("hoTen"));
            LSHD_Xoa.setCMND(rs.getString("cmnd"));
            list.add(0,LSHD_Xoa);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;

    }

    @SneakyThrows
    public void addNK_Xoa(LichSuHoatDongXoa l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_NHAN_KHAU_XOA);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getNoiDungHDXoa());
        pstmt.setDate(3,l.getThoiGianHD());
        pstmt.setString(4,l.getHoTen());
        pstmt.setString(5,l.getCMND());
        pstmt.execute();

    }

    @SneakyThrows
    public void addHK_Xoa(LichSuHoatDongXoa l){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.LICH_SU_ADD_HO_KHAU_XOA);
        pstmt.setInt(1, l.getIdHD());
        pstmt.setString(2, l.getNoiDungHDXoa());
        pstmt.setDate(3,l.getThoiGianHD());
        pstmt.setString(4,l.getHoTen());
        pstmt.setString(5,l.getCMND());
        pstmt.execute();
    }

}
