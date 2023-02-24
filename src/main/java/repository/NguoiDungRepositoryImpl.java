package repository;

import entity.NguoiDung;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.util.List;

public class NguoiDungRepositoryImpl implements NguoiDungRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    public static    NguoiDung nguoiDung = new NguoiDung();
    @Override
    public boolean dangNhap(String taiKhoan, String matKhau) {
        Boolean kiemTra;
        int dem=0;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NGUOI_DUNG_QUERY_DANG_NHAP);
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                dem++;
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTaiKhoan(rs.getString("taiKhoan"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                nguoiDung.setNgaySinh(rs.getString("ngaySinh"));
                nguoiDung.setEmail(rs.getString("email"));
                nguoiDung.setGioiTinh(rs.getString("gioiTinh"));
                nguoiDung.setChucVu(rs.getString("chucVu"));
                nguoiDung.setDonViCongTac(rs.getString("donViCongTac"));
                nguoiDung.setanhDaiDien(rs.getString("anhDaiDien"));
                System.out.println(nguoiDung.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (nguoiDung.getId() != null && dem>0) {
            kiemTra = true;
        } else {
            kiemTra = false;
        }

        return kiemTra;
    }

}
