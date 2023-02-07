package repository;

import java.sql.*;
import entity.NguoiDung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;
public class TaiKhoanRepositoryImpl implements  TaiKhoanRepository{

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @SneakyThrows
    @Override
    public void chinhSuaAnhDaiDien(String anhDaiDien){
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.NGUOI_DUNG_SUA_ANH_DAI_DIEN);
        pstmt.setString(1, anhDaiDien);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

}
