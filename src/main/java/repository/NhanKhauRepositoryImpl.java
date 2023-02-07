package repository;

import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauRepositoryImpl implements  NhanKhauRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    //trangthaicutru - cho trang chủ
    @Override
    public int tongNhanKhauThuongTru() {
        int tongNhanKhauThuongTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_THUONG_TRU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauThuongTru =  rs.getInt(1);
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

        return tongNhanKhauThuongTru;
    }

    @Override
    public int tongNhanKhauDaChuyenDi() {
        int tongNhanKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauDaChuyenDi = rs.getInt(1);
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

        return tongNhanKhauDaChuyenDi;
    }

    @Override
    public int tongNhanKhauDaMat() {
        int tongNhanKhauDaMat = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_MAT);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauDaMat =  rs.getInt(1);
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

        return tongNhanKhauDaMat;
    }

    @Override
    public int tongNhanKhauKhongXacDinh() {
        int tongNhanKhauKhongXacDinh = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauKhongXacDinh = rs.getInt(1);
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

        return tongNhanKhauKhongXacDinh;
    }

    @Override
    public int tongNhanKhauTamTru() {
        int tongNhanKhauTamTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_TRU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauTamTru = rs.getInt(1);
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

        return tongNhanKhauTamTru;
    }

    @Override
    public int tongNhanKhauTamVang() {
        int tongNhanKhauTamVang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_VANG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                    tongNhanKhauTamVang = rs.getInt(1);
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

        return tongNhanKhauTamVang;
    }


    //trangthaicutru - cho thống kê
    @Override
    public int tongNhanKhauThuongTru(int year) {
        int tongNhanKhauThuongTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_THUONG_TRU_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauThuongTru ++;
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

        return tongNhanKhauThuongTru;
    }

    @Override
    public int tongNhanKhauDaChuyenDi(int year) {
        int tongNhanKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauDaChuyenDi ++;
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

        return tongNhanKhauDaChuyenDi;
    }

    @Override
    public int tongNhanKhauDaMat(int year) {
        int tongNhanKhauDaMat = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_MAT_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauDaMat ++;
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

        return tongNhanKhauDaMat;
    }

    @Override
    public int tongNhanKhauKhongXacDinh(int year) {
        int tongNhanKhauKhongXacDinh = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauKhongXacDinh ++;
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

        return tongNhanKhauKhongXacDinh;
    }

    @Override
    public int tongNhanKhauTamTru(int year) {
        int tongNhanKhauTamTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_TRU_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauTamTru++;
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

        return tongNhanKhauTamTru;
    }

    @Override
    public int tongNhanKhauTamVang(int year) {
        int tongNhanKhauTamVang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_VANG_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                tongNhanKhauTamVang++;
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

        return tongNhanKhauTamVang;
    }

    @Override
    public int tongNam(int year) {
        int tongNam = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NAM_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                   tongNam ++ ;
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

        return tongNam;
    }

    //gioitinh
    @Override
    public int tongNu(int year) {
        int tongNu = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NAM_NU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                    tongNu ++ ;
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

        return tongNu;
    }


    //dotuoi

    @Override
    public int tongDuoiLD(int year) {
        int tongDoTuoiHSSV = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_DUOI_LD_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                    tongDoTuoiHSSV++;
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

        return tongDoTuoiHSSV;
    }

    @Override
    public int tongLD(int year) {
        int tongDoTuoiLaoDong = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_lD_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                  tongDoTuoiLaoDong++;
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

        return tongDoTuoiLaoDong;
    }

    @Override
    public int tongTrenLD(int year) {
        int tongNghiHuu = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TREN_LD_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                if( Integer.parseInt(rs.getString(1)) <= year)
                   tongNghiHuu++;
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

        return tongNghiHuu;
    }

    @Override
    public ArrayList<String> listYear(){
        ArrayList<String> listYear = new ArrayList<String>() ;
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_YEAR);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                listYear.add(rs.getString(1));//+=
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
        return listYear;
    }

}
