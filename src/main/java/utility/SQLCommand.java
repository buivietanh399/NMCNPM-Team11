package utility;

public class SQLCommand {
    // nguoi_dung
    public static String NGUOI_DUNG_QUERY_DANG_NHAP = "SELECT * FROM nguoi_dung WHERE taiKhoan = ? AND matKhau = ?";

    public static String NGUOI_DUNG_QUERY_LAY_THONG_TIN = "SELECT * FROM nguoi_dung";

    public static String NGUOI_DUNG_SUA_ANH_DAI_DIEN= "UPDATE nguoi_dung SET\n" + "anhDaiDien = ?" ;


    // nhan_khau
    //1
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU_NAM = "SELECT YEAR(chuyenDenNgay)  FROM nhan_khau WHERE trangThai = N'Thường trú'";
    //2
    public static String NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI_NAM = "SELECT YEAR(chuyenDenNgay)  FROM nhan_khau WHERE trangThai = N'Đã chuyển đi'";
    //3
    public static String NHAN_KHAU_QUERY_TONG_DA_MAT_NAM= "SELECT YEAR(chuyenDenNgay)  FROM nhan_khau WHERE trangThai = N'Đã mất'";
    //4
    public static String NHAN_KHAU_DUOI_18_TUOI_NAM = "SELECT idNhanKhau, DATEDIFF(CURRENT_DATE, ngaySinh)/365 as tuoi FROM nhan_khau WHERE DATEDIFF(CURRENT_DATE, ngaySinh)/365 < 18";
    //5
    public static String NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH_NAM = "SELECT YEAR(chuyenDenNgay)  FROM nhan_khau WHERE trangThai = N''";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_tru tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm trú'\n" +
//            "AND tt.denNgay > GETDATE()";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_vang tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm vắng'\n" +
//            "AND tt.denNgay > GETDATE()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";
    //6
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU_NAM = "SELECT YEAR(chuyenDenNgay) \n" +
            "FROM nhan_khau nk, tam_tru tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm trú'\n" +
            "AND tt.denNgay > NOW()";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG_NAM = "SELECT YEAR(chuyenDenNgay) \n" +
            "FROM nhan_khau nk, tam_vang tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm vắng'\n" +
            "AND tt.denNgay > NOW()";

    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT Count(*)  FROM nhan_khau WHERE trangThai = N'Thường trú'";
    public static String NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT Count(*)   FROM nhan_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String NHAN_KHAU_QUERY_TONG_DA_MAT= "SELECT Count(*)   FROM nhan_khau WHERE trangThai = N'Đã mất'";
    public static String NHAN_KHAU_DUOI_18_TUOI = "SELECT idNhanKhau, DATEDIFF(CURRENT_DATE, ngaySinh)/365 as tuoi FROM nhan_khau WHERE DATEDIFF(CURRENT_DATE, ngaySinh)/365 < 18";
    public static String NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH = "SELECT Count(*)   FROM nhan_khau WHERE trangThai = N''";
    //    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_tru tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm trú'\n" +
//            "AND tt.denNgay > GETDATE()";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_vang tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm vắng'\n" +
//            "AND tt.denNgay > GETDATE()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT Count(*)  \n" +
            "FROM nhan_khau nk, tam_tru tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm trú'\n" +
            "AND tt.denNgay > NOW()";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT Count(*)  \n" +
            "FROM nhan_khau nk, tam_vang tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm vắng'\n" +
            "AND tt.denNgay > NOW()";
    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";

    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN_THEO_ID = "SELECT * FROM nhan_khau where idNhanKhau = ?";


    //cho thống kê
    public static String NHAN_KHAU_QUERY_NAM = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nam'";

    public static String NHAN_KHAU_QUERY_NAM_NAM = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE gioiTinh = N'Nam'";
    public static String NHAN_KHAU_QUERY_NU = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nữ'";

    public static String NHAN_KHAU_QUERY_NAM_NU = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE gioiTinh = N'Nữ'";
    public static String NHAN_KHAU_QUERY_UPDATE =  "UPDATE `nhan_khau` SET " +
            "`hoTen`=?," +
            "`biDanh`=?," +
            "`ngaySinh`=?," +
            "`noiSinh`=?," +
            "`gioiTinh`=?," +
            "`nguyenQuan`=?," +
            "`danToc`=?," +
            "`tonGiao`=?," +
            "`quocTich`=?," +
            "`ngheNghiep`=?," +
            "`noiLamViec`=?," +
            "`cmnd`=?," +
            "`ngayCap`=?," +
            "`chuyenDenNgay`=?," +
            "`noiThuongTruTruoc`=?  WHERE idNhanKhau  = ";
    public static String NHAN_KHAU_QUERY_UPDATE_TRANGTHAI    ="UPDATE `nhan_khau` SET " +

            "`trangThai`=?  WHERE idNhanKhau  =";
    public static String NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU="INSERT INTO `chuyen_nhan_khau`( `idNhanKhau`, `ngayChuyenDi`, `noiChuyenDen`, `ghiChu`) VALUES (?,?,?,?)";
    public static String NHAN_KHAU_QUERY_HOTEN="SELECT * FROM `nhan_khau` WHERE hoTen like '%";
    public static String NHAN_KHAU_QUERY_CMND="SELECT * FROM `nhan_khau` WHERE cmnd like '%";
    public static String NHAN_KHAU_QUERY_TRANGTHAI="SELECT * FROM `nhan_khau` WHERE trangThai like '%";
    public static String NHAN_KHAU_QUERY_NGAYSINH="SELECT * FROM `nhan_khau` WHERE ngaySinh like '%";
    public static String NHAN_KHAU_QUERY_INSERT_KHAITU="INSERT INTO `khai_tu`(`idNguoiMat`, `idNguoiKhai`, `ngayKhai`, `ngayMat`, `liDoMat`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMVANG="INSERT INTO `tam_vang`( `idNhanKhau`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_TAMTRU="INSERT INTO `tam_tru`( `idNhanKhau`, `noiThuongTru`, `noiTamTru`, `tuNgay`,`denNgay`, `lyDo`) VALUES (?,?,?,?,?,?)";
    public static String NHAN_KHAU_QUERY_INSERT_NHANKHAU="INSERT INTO `nhan_khau`( `hoTen`, `biDanh`, `ngaySinh`, `noiSinh`, `gioiTinh`, `nguyenQuan`, `danToc`, `tonGiao`, `quocTich`, `ngheNghiep`, `noiLamViec`, `cmnd`, `ngayCap`, `chuyenDenNgay`, `noiThuongTruTruoc`, `trangThai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) <= 5";
//    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 6 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 10";
//    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 11 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 14";
//    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 15 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 17";
//    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 18 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 64";
//    public static String NHAN_KHAU_QUERY_NGHI_HUU = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 65";
    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) <= 5";
    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 6 AND YEAR(NOW()) - YEAR(ngaySinh) <= 10";
    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 11 AND YEAR(NOW()) - YEAR(ngaySinh) <= 14";
    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 15 AND YEAR(NOW()) - YEAR(ngaySinh) <= 17";

    public static String NHAN_KHAU_QUERY_DUOI_LD_NAM= "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE  (YEAR(NOW()) - YEAR(ngaySinh)) < 15";
    public static String NHAN_KHAU_QUERY_lD_NAM = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE (YEAR(NOW()) - YEAR(ngaySinh)) >= 15 AND (YEAR(NOW()) - YEAR(ngaySinh)) <= 64";
    public static String NHAN_KHAU_QUERY_TREN_LD_NAM = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE (YEAR(NOW()) - YEAR(ngaySinh)) >= 65";

    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG_NAM = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 23 AND YEAR(NOW()) - YEAR(ngaySinh) <= 64";
    public static String NHAN_KHAU_QUERY_NGHI_HUU_NAM = "SELECT YEAR(chuyenDenNgay) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 65";

    public static String NHAN_KHAU_QUERY_YEAR = "SELECT DISTINCT YEAR(chuyenDenNgay) FROM nhan_khau  ";
    // ho_khau

    //trangthaicutru
    //cho trang chủ
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Thường trú'";
    public static String HO_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Đã chuyển đi'";

    //cho thống kế
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU_NAM = "SELECT YEAR(ngayTao) FROM ho_khau WHERE trangThai = N'Thường trú'";
    public static String HO_KHAU_QUERY_TONG_DA_CHUYEN_DI_NAM = "SELECT YEAR(ngayTao) FROM ho_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String HO_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM ho_khau";
//    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
//            "FROM (SELECT hk.idHoKhau, ISNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
//            "\t\tFROM ho_khau hk\n" +
//            "\t\tLEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
//            "\t\tGROUP BY(hk.idHoKhau)) temp\n" +
//            "GROUP BY soLuong";
    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
            "FROM (SELECT hk.idHoKhau, IFNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
            "      FROM ho_khau hk\n" +
            "               LEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
            "      GROUP BY(hk.idHoKhau)) temp\n" +
            "GROUP BY soLuong";

    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN_NAM = "SELECT year, soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
            "FROM (SELECT YEAR(hk.ngayTao) AS year, hk.idHoKhau, IFNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
            "      FROM ho_khau hk\n" +
            "               LEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
            "      GROUP BY(hk.idHoKhau) ) temp\n" +
            "GROUP BY soLuong";

    public static String HO_KHAU_QUERY_DELETE_HK = "DELETE FROM `ho_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATAHOKHAUCONTROLLER = "SELECT hk.*, nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuHo = nk.idNhanKhau";
    public static String HO_KHAU_QUERY_CHECK_CHU_HO = "SELECT * FROM `ho_khau`";
    public static String HO_KHAU_QUERY_XAC_NHAN_BUTTON = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES (?,?,?,?,?,?,?)";
    public static String HO_KHAU_QUERY_IDHOKHAU_MOI_NHAT = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_ADD = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_ADD = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATATHEMHOKHAUCONTROLLER = "SELECT * FROM `nhan_khau`";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO = "SELECT nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADDATAXEMHOKHAUCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.hoTen, nk.ngaySinh, nk.cmnd FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHANGE_INF_HK = "UPDATE `ho_khau` SET idChuHo = ? WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_BEFORE_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_NK_AFTER_CHANGE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_BEFORE_DELETE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_AFTER_CHANGE = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_HK = "SELECT * FROM `ho_khau` WHERE idHoKhau != ?";
    public static String HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.cmnd, nk.hoTen, nk.ngaySinh FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADNKSUAHKCONTROLLER = "SELECT * FROM `nhan_khau`";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_INT = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_STRING = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK = "SELECT * FROM `ho_khau_nhan_khau`";
    public static String HO_KHAU_QUERY_THEMNHANKHAU = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_CHANGE_INF_HKNK = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK_1 = "SELECT * FROM `ho_khau_nhan_khau` WHERE idHoKhau != ?";
    public static String HO_KHAU_QUERY_CLEAR_HKNK = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_UPDATE_NK_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_ID_CH_HIEN_TAI = "UPDATE `ho_khau` SET idChuHo = ? WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_DELETE_ALL_NK_FROM_HK_HIEN_TAI = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";
    public static String HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_HIEN_TAI = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_ALL_NK_FROM_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_CH_FROM_HK_HIEN_TAI = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_CREATE_NEW_HK = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES (?,?,?,?,?,?,?)";
    public static String HO_KHAU_QUERY_ID_NEW_HOKHAU = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";
    public static String HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_MOI = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_NEW_CH = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_UPDATE_NEW_NK = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
    public static String HO_KHAU_QUERY_LOADDATANKTACHHKCONTROLLER = "SELECT a.idNhanKhau,a.quanHeChuHo,b.hoTen,b.ngaySinh FROM `ho_khau_nhan_khau` a, `nhan_khau` b WHERE a.idNhanKhau = b.idNhanKhau and a.idHoKhau = ?";
    public static String HO_KHAU_QUERY_LOADDATACHTACHHKCONTROLLER = "SELECT b.idNhanKhau,b.hoTen,b.ngaySinh FROM `ho_khau` a, `nhan_khau` b WHERE a.idChuHo = b.idNhanKhau and a.idHoKhau = ?";
    public static String HO_KHAU_QUERY_CHUYEN_HO_KHAU = "INSERT INTO `chuyen_ho_khau`(idHoKhau, ngayChuyenDi, noiChuyenDen, ghiChu) VALUES (?,?,?,?)";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_HOKHAU = "UPDATE `ho_khau` SET diaChi = ?, tinhThanhPho = ?, quanHuyen = ?, phuongXa = ?,trangThai = ? WHERE idHoKhau = ? ";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_NHANKHAU = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?) ";
    public static String HO_KHAU_QUERY_UPDATE_TRANGTHAI_CHUHO = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuho FROM `ho_khau` WHERE idHoKhau = ?) ";
    public static String HO_KHAU_QUERY_LOADDATA = "SELECT * FROM `chuyen_ho_khau` WHERE idHoKhau = ?";

    // phan thuong
    public static String PHAN_THUONG_NAM_SO_DIP = "select temp.nam, count(distinct ddb.idDip) + count(distinct dhsg.idDip) as soDip\n" +
            "from (select distinct nam\n" +
            "\t\tfrom dip_dac_biet \n" +
            "\t\tunion\n" +
            "\t\tselect distinct nam\n" +
            "\t\tfrom dip_hoc_sinh_gioi) temp\n" +
            "\tleft join dip_dac_biet ddb on temp.nam = ddb.nam\n" +
            "\tleft join dip_hoc_sinh_gioi dhsg on temp.nam = dhsg.nam\n" +
            "group by temp.nam";
    public static String PHAN_THUONG_NAM_SO_DIP_TIM = "select temp.nam, count(distinct ddb.idDip) + count(distinct dhsg.idDip) as soDip\n" +
            "from (select distinct nam\n" +
            "\t\tfrom dip_dac_biet \n" +
            "\t\tunion\n" +
            "\t\tselect distinct nam\n" +
            "\t\tfrom dip_hoc_sinh_gioi) temp\n" +
            "\tleft join dip_dac_biet ddb on temp.nam = ddb.nam\n" +
            "\tleft join dip_hoc_sinh_gioi dhsg on temp.nam = dhsg.nam\n" +
            "where temp.nam = ?\n" +
            "group by temp.nam";
    public static String PHAN_THUONG_TEN_DIP_NAM = "select ten\n" +
            "from dip_dac_biet\n" +
            "where nam = ?";
    public static String PHAN_THUONG_KIEM_TRA_HSG = "select count(idDip)\n" +
            "from dip_hoc_sinh_gioi\n" +
            "where nam = ?";

    // dip_dac_biet
    public static String DIP_DAC_BIET_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_dac_biet d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_dac_biet d LEFT JOIN chi_tiet_dip_dac_biet c\n" +
            "ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            "GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";

    public static String DIP_DAC_BIET_TRA_CUU_DIP = "SELECT d.*, a.soNguoi\n" +
            "FROM dip_dac_biet d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_dac_biet d LEFT JOIN chi_tiet_dip_dac_biet c\n" +
            "ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            "GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip and ten LIKE ? and nam = ?";

    public static String DIP_DAC_BIET_TAO_MOI_DIP = "INSERT INTO dip_dac_biet(ten, nam, moTa, phanQua05, phanQua614, phanQua1517, tien05, tien614, tien1517) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String DIP_DAC_BIET_TRA_CUU_BY_TEN_NAM = "SELECT * FROM dip_dac_biet WHERE ten = ? and nam = ?";

    public static String DIP_DAC_BIET_XOA_DIP_TRAO_THUONG = "DELETE FROM dip_dac_biet WHERE idDip = ?";

    public static String DIP_DAC_BIET_CHINH_SUA = "UPDATE dip_dac_biet SET ten = ?,\n" +
            " phanQua05 = ?,\n" +
            " phanQua614 = ?,\n" +
            " phanQua1517 = ?,\n" +
            " tien05 = ?,\n" +
            " tien614 = ?,\n" +
            " tien1517 = ?,\n" +
            " moTa = ?\n" +
            " WHERE idDip = ?";

    public static String DIP_DAC_BIET_QUERY_TIM_NAM = "select *\n" +
            "from dip_dac_biet\n" +
            "where nam = ?";
    public static String DIP_DAC_BIET_QUERY_TEN = "select *\n" +
            "from dip_dac_biet\n" +
            "where ten = ?";
    public static String DIP_DAC_BIET_QUERY_NAM_TEN = "select *\n" +
            "from dip_dac_biet\n" +
            "where nam = ?\n" +
            "and ten = ?";

    // dip_hoc_sinh_gioi
    public static String HOC_SINH_GIOI_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_hoc_sinh_gioi d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_hoc_sinh_gioi d LEFT JOIN chi_tiet_dip_hoc_sinh_gioi c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";

    public static String HOC_SINH_GIOI_TRA_CUU_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_hoc_sinh_gioi d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_hoc_sinh_gioi d LEFT JOIN chi_tiet_dip_hoc_sinh_gioi c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip and d.nam = ?";

    public static String HOC_SINH_GIOI_XOA_DIP_TRAO_THUONG = "DELETE FROM dip_hoc_sinh_gioi WHERE idDip = ?";

    public static String HOC_SINH_GIOI_TAO_MOI_DIP = "INSERT INTO dip_hoc_sinh_gioi(nam, moTa, phanQuaDacBiet, phanQuaGioi, phanQuaKha, tienDacBiet, tienGioi, tienKha) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    public static String HOC_SINH_GIOI_CHINH_SUA = "UPDATE dip_hoc_sinh_gioi SET\n" +
            " phanQuaDacBiet = ?,\n" +
            " phanQuaGioi = ?,\n" +
            " phanQuaKha = ?,\n" +
            " tienDacBiet = ?,\n" +
            " tienGioi = ?,\n" +
            " tienKha = ?,\n" +
            " moTa = ?\n" +
            " WHERE idDip = ?";
    public static String HOC_SINH_GIOI_TRA_CUU_BY_NAM = "SELECT * FROM dip_hoc_sinh_gioi WHERE nam = ?";




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //NEW
    //1. Cho thong ke hs giỏi

    //tim cac nam
    public static String HOC_SINH_GIOI_QUERY_NAM = "SELECT distinct nam FROM dip_hoc_sinh_gioi";

    //tim ds Ho da trao theo idDip
    public static String HOC_SINH_GIOI_QUERY_HODATRAO_DIP = "SELECT hk.idHoKhau, count(nk.idNhanKhau) as soNhanKhau, \n" +
            "       sum(case when ct.nhom = 1 then d.tienDacBiet \n" +
            "                 when ct.nhom = 2 then d.tienGioi \n" +
            "                 else d.tienKha end) as tongTien\n" +
            "from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "where hk.idHoKhau = hknk.idHoKhau and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "and d.idDip = ct.idDip\n" +
            "and nk.idNhanKhau = ct.idNhanKhau\n" +
            "and ct.kiemtra = 1\n" +
            "and d.idDip = ?\n" +
            "group by hk.idHoKhau ";

    //tim ds Ho da chưa theo idDip
    public static String HOC_SINH_GIOI_QUERY_HOCHUARAO_DIP = "SELECT hk.idHoKhau, count(nk.idNhanKhau) as soNhanKhau, \n" +
            "       sum(case when ct.nhom = 1 then d.tienDacBiet \n" +
            "                 when ct.nhom = 2 then d.tienGioi \n" +
            "                 else d.tienKha end) as tongTien\n" +
            "from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "where hk.idHoKhau = hknk.idHoKhau and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "and d.idDip = ct.idDip\n" +
            "and nk.idNhanKhau = ct.idNhanKhau\n" +
            "and ct.kiemtra = 0\n" +
            "and d.idDip = ?\n" +
            "group by hk.idHoKhau ";

    //tim idDip theo năm
    public static String HOC_SINH_GIOI_QUERY_TIM_ID_NAM = "SELECT d.idDip\n" +
            "    from dip_hoc_sinh_gioi d\n" +
            "    WHERE d.nam = ?";

    //tìm tổng tiền đã trao đã trao của 1 dịp theo idDip
    public static String HOC_SINH_GIOI_QUERY_TIM_TONGTIENDATRAO_ID ="SELECT  sum(case when ct.nhom = 1 then d.tienDacBiet\n" +
            "            when ct.nhom = 2 then d.tienGioi\n" +
            "            else d.tienKha end) as TongTien\n" +
            "    from nhan_khau nk,  dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "    WHERE d.idDip = ct.idDip\n" +
            "    and nk.idNhanKhau = ct.idNhanKhau\n" +
            "    and ct.kiemtra = 1\n" +
            "    and d.idDip = ?";

    //tìm tổng tiền đã trao đã chưa của 1 dịp theo idDip
    public static String HOC_SINH_GIOI_QUERY_TIM_TONGTIENCHUATRAO_ID ="SELECT  sum(case when ct.nhom = 1 then d.tienDacBiet\n" +
            "            when ct.nhom = 2 then d.tienGioi\n" +
            "            else d.tienKha end) as TongTien\n" +
            "    from nhan_khau nk,  dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "    WHERE d.idDip = ct.idDip\n" +
            "    and nk.idNhanKhau = ct.idNhanKhau\n" +
            "    and ct.kiemtra = 0\n" +
            "    and d.idDip = ?";


    //tìm ds nhân khẩu đã đuược trao thưởng của 1 hộ ( theo idHo) trong 1 dịp HSG (theo idDip)
    public static String HOC_SINH_GIOI_QUERY_CHITIET_DATRAOTHUONG = "SELECT nk.idNhanKhau, nk.hoTen, \n" +
            "(CASE WHEN ct.nhom = 1 THEN N'Học sinh xuất sắc'\n" +
            "      WHEN ct.nhom = 2 THEN N'Học sinh giỏi' \n" +
            "      ELSE N'Học sinh khá' END) AS danhHieu,\n" +
            "(CASE WHEN ct.nhom = 1 THEN d.phanQuaDacBiet\n" +
            "      WHEN ct.nhom = 2 THEN d.phanQuaGioi\n" +
            "      ELSE d.phanQuaKha END) AS phanQua,\n" +
            "(CASE WHEN ct.nhom = 1 THEN d.tienDacBiet\n" +
            "      WHEN ct.nhom = 2 THEN d.tienGioi\n" +
            "      ELSE d.tienKha END) AS soTien\n" +
            "from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "where hk.idHoKhau = hknk.idHoKhau \n" +
            "and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "and nk.idNhanKhau = ct.idNhanKhau\n" +
            "and d.idDip = ct.idDip\n" +
            "and ct.kiemtra = 1\n" +
            "and hk.idHoKhau = ?\n" +
            "and d.idDip = ?";

    //tìm ds nhân khẩu đã chưa trao thưởng của 1 hộ ( theo idHo) trong 1 dịp HSG (theo idDip)
    public static String HOC_SINH_GIOI_QUERY_CHITIET_CHUATRAOTHUONG = "SELECT nk.idNhanKhau, nk.hoTen, \n" +
            "(CASE WHEN ct.nhom = 1 THEN N'Học sinh xuất sắc'\n" +
            "      WHEN ct.nhom = 2 THEN N'Học sinh giỏi' \n" +
            "      ELSE N'Học sinh khá' END) AS danhHieu,\n" +
            "(CASE WHEN ct.nhom = 1 THEN d.phanQuaDacBiet\n" +
            "      WHEN ct.nhom = 2 THEN d.phanQuaGioi\n" +
            "      ELSE d.phanQuaKha END) AS phanQua,\n" +
            "(CASE WHEN ct.nhom = 1 THEN d.tienDacBiet\n" +
            "      WHEN ct.nhom = 2 THEN d.tienGioi\n" +
            "      ELSE d.tienKha END) AS soTien\n" +
            "from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_hoc_sinh_gioi d, chi_tiet_dip_hoc_sinh_gioi ct\n" +
            "where hk.idHoKhau = hknk.idHoKhau \n" +
            "and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "and nk.idNhanKhau = ct.idNhanKhau\n" +
            "and d.idDip = ct.idDip\n" +
            "and ct.kiemtra = 0\n" +
            "and hk.idHoKhau = ?\n" +
            "and d.idDip = ?";





    //2. cho thong ke dip db


    //tim cac nam
    public static String DIP_DAC_BIET_QUERY_CAC_NAM = "SELECT distinct nam FROM dip_dac_biet";

    //tim ds Ho da trao theo idDip
    public static String DIP_DAC_BIET_QUERY_HODATRAO_DIP = "SELECT hk.idHoKhau, count(nk.idNhanKhau) as soNhanKhau,\n" +
            "                   sum(case when ct.nhom = 1 then d.tien05 \n" +
            "                             when ct.nhom = 2 then d.tien614\n" +
            "                             else d.tien1517 end) as tongTien\n" +
            "            from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "            where hk.idHoKhau = hknk.idHoKhau and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "            and d.idDip = ct.idDip\n" +
            "            and nk.idNhanKhau = ct.idNhanKhau\n" +
            "            and ct.kiemtra = 1\n" +
            "            and d.idDip = ? \n" +
            "            group by hk.idHoKhau ;";

    //tim ds Ho da chua trao theo idDip
    public static String DIP_DAC_BIET_QUERY_HOCHUARAO_DIP = "SELECT hk.idHoKhau, count(nk.idNhanKhau) as soNhanKhau,\n" +
            "                   sum(case when ct.nhom = 1 then d.tien05 \n" +
            "                             when ct.nhom = 2 then d.tien614\n" +
            "                             else d.tien1517 end) as tongTien\n" +
            "            from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "            where hk.idHoKhau = hknk.idHoKhau and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "            and d.idDip = ct.idDip\n" +
            "            and nk.idNhanKhau = ct.idNhanKhau\n" +
            "            and ct.kiemtra = 0\n" +
            "            and d.idDip = ? \n" +
            "            group by hk.idHoKhau ;";

    //tim idDip theo năm
    public static String DIP_DAC_BIET_QUERY_TIM_ID_NAM = "SELECT d.idDip\n" +
            "    from dip_dac_biet d\n" +
            "    WHERE d.nam = ?";

    //tìm tổng tiền đã trao đã trao của 1 dịp theo idDip
    public static String DIP_DAC_BIET_QUERY_TIM_TONGTIENDATRAO_ID ="SELECT  sum(case when ct.nhom = 1 then d.tien05\n" +
            "                       when ct.nhom = 2 then d.tien614\n" +
            "                       else d.tien1517 end) as TongTien\n" +
            "               from nhan_khau nk,  dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "               WHERE d.idDip = ct.idDip\n" +
            "               and nk.idNhanKhau = ct.idNhanKhau\n" +
            "               and ct.kiemtra = 1\n" +
            "               and d.idDip = ?";

    //tìm tổng tiền chưa trao  của 1 dịp theo idDip
    public static String DIP_DAC_BIET_QUERY_TIM_TONGTIENCHUATRAO_ID ="SELECT  sum(case when ct.nhom = 1 then d.tien05\n" +
            "                       when ct.nhom = 2 then d.tien614\n" +
            "                       else d.tien1517 end) as TongTien\n" +
            "               from nhan_khau nk,  dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "               WHERE d.idDip = ct.idDip\n" +
            "               and nk.idNhanKhau = ct.idNhanKhau\n" +
            "               and ct.kiemtra = 0\n" +
            "               and d.idDip = ?";


    //tìm ds nhân khẩu đã đuược trao thưởng của 1 hộ ( theo idHo) trong 1 dịp ĐB (theo idDip)
    public static String DIP_DAC_BIET_QUERY_CHITIET_DATRAOTHUONG = "SELECT nk.idNhanKhau, nk.hoTen, \n" +
            "            (year(now()) - year(nk.ngaySinh)) AS doTuoi,\n" +
            "            (CASE WHEN ct.nhom = 1 THEN d.phanQua05\n" +
            "                  WHEN ct.nhom = 2 THEN d.phanQua614\n" +
            "                  ELSE d.phanQua1517 END) AS phanQua,\n" +
            "            (CASE WHEN ct.nhom = 1 THEN d.tien05\n" +
            "                  WHEN ct.nhom = 2 THEN d.tien614\n" +
            "                  ELSE d.tien1517 END) AS soTien\n" +
            "            from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "            where hk.idHoKhau = hknk.idHoKhau \n" +
            "            and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "            and nk.idNhanKhau = ct.idNhanKhau\n" +
            "            and d.idDip = ct.idDip\n" +
            "            and ct.kiemtra = 1\n" +
            "            and hk.idHoKhau = ?\n" +
            "            and d.idDip = ?";

    //tìm ds nhân khẩu đã chưa trao thưởng của 1 hộ ( theo idHo) trong 1 dịp ĐB (theo idDip)
    public static String DIP_DAC_BIET_QUERY_CHITIET_CHUATRAOTHUONG = "SELECT nk.idNhanKhau, nk.hoTen, \n" +
            "            (year(now()) - year(nk.ngaySinh)) AS doTuoi,\n" +
            "            (CASE WHEN ct.nhom = 1 THEN d.phanQua05\n" +
            "                  WHEN ct.nhom = 2 THEN d.phanQua614\n" +
            "                  ELSE d.phanQua1517 END) AS phanQua,\n" +
            "            (CASE WHEN ct.nhom = 1 THEN d.tien05\n" +
            "                  WHEN ct.nhom = 2 THEN d.tien614\n" +
            "                  ELSE d.tien1517 END) AS soTien\n" +
            "            from ho_khau hk, nhan_khau nk, ho_khau_nhan_khau hknk, dip_dac_biet d, chi_tiet_dip_dac_biet ct\n" +
            "            where hk.idHoKhau = hknk.idHoKhau \n" +
            "            and nk.idNhanKhau = hknk.idNhanKhau\n" +
            "            and nk.idNhanKhau = ct.idNhanKhau\n" +
            "            and d.idDip = ct.idDip\n" +
            "            and ct.kiemtra = 0\n" +
            "            and hk.idHoKhau = ?\n" +
            "            and d.idDip = ?";


    //3.Cho lịch sử hoạt động
    //3.1 Cho chỉnh sửa nhân khẩu

    //tìm các trường theo id
    public static String LICH_SU_NHAN_KHAU_QUERY_CAC_TRUONG_THEO_ID = "Select  * from nhan_khau where idNhanKhau = ?";

    //tìm số nhân khẩu hiện có
    public static String LICH_SU_NHAN_KHAU_QUERY_SO_NHAN_KHAU = "SELECT max(idNhanKhau)\n" +
            "    from nhan_khau";

    //3.2 Cho chỉnh sửa hộ khẩu

    // chuyển chủ hộ



    // b) xoá qhe của thanh viên lên chủ hộ

    public static String HO_KHAU_DELETE_XOA_CHU_HO_CU= "DELETE FROM ho_khau_nhan_khau WHERE idNhanKhau = ?";

    //3.3 Cho xóa hộ khẩu
    public static String HO_KHAU_QUERY_DANH_SACH_THANH_VIEN = "Select idNhanKhau, quanHeChuHo from ho_khau_nhan_khau where idHoKhau = ?";


    //3.4 Cho chỉnh sửa dịp HSG
    public static String LICH_SU_HOC_SINH_GIOI_QUERY_CAC_TRUONG_THEO_ID= "SELECT * from dip_hoc_sinh_gioi Where idDip = ?";

    //3.5 Cho tạo dịp HSG

    public static String LICH_SU_HOC_SINH_GIOI_QUERY_MAX_ID= "SELECT max(idDip) from dip_hoc_sinh_gioi ";

    //3.5 Cho tạo dịp DB

    public static String LICH_SU_DAC_BIET_QUERY_MAX_ID= "SELECT max(idDip) from dip_dac_biet ";

    //3.6 Cho chỉnh sửa dịp ĐB

    public static String LICH_SU_DAC_BIET_QUERY_CAC_TRUONG_THEO_ID= "SELECT * from dip_dac_biet Where idDip = ?";


    // id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    //                       nhom INT NOT NULL,
    //                       idHD1 INT ,
    //                       idHD2 INT ,
    //                       idHD3 INT ,
    //                       idHD4 INT ,
    //                       noidungHD VARCHAR(255),
    //                       tenHD VARCHAR(255) ,
    //3.7 Cho add LSHD
    public static String LICH_SU_ADD_NHAN_KHAU = "INSERT INTO lich_su_hoat_dong(nhom, idHD1, tenHD ,noidungHD, thoiGianHD ) VALUES (1, ?, ?, ?,?)";
    public static String LICH_SU_ADD_HO_KHAU = "INSERT INTO lich_su_hoat_dong(nhom, idHD2, tenHD ,noidungHD, thoiGianHD ) VALUES (2, ?, ?, ?,?)";
    public static String LICH_SU_ADD_HSG = "INSERT INTO lich_su_hoat_dong(nhom, idHD3, tenHD ,noidungHD , thoiGianHD) VALUES (3, ?, ?, ?, ?)";
    public static String LICH_SU_ADD_DB = "INSERT INTO lich_su_hoat_dong(nhom, idHD4, tenHD ,noidungHD , thoiGianHD) VALUES (4, ?, ?, ?, ?)";

    public static String LICH_SU_ADD_NHAN_KHAU_XOA = "INSERT INTO lich_su_hoat_dong_xoa(nhom, idHD ,noidungHDXoa, thoiGianHD,hoTen, cmnd ) VALUES (1, ?, ?, ?,?,?)";
    public static String LICH_SU_ADD_HO_KHAU_XOA = "INSERT INTO lich_su_hoat_dong_xoa(nhom, idHD ,noidungHDXoa, thoiGianHD,hoTen, cmnd ) VALUES (2, ?, ?, ?,?,?)";




    //3.8 Lấy LSHD theo nhóm
    public static String LICH_SU_QUERY= "Select * from lich_su_hoat_dong where nhom = ?";

    public static String LICH_SU_QUERY_XOA= "Select * from lich_su_hoat_dong_xoa where nhom = ?";

    //Cho xóa - khôi phục lịch sử xóa

    //xóa vĩnh viễn
    public static String LICH_SU_DELETE_NHAN_KHAU_XOA = "Delete from lich_su_hoat_dong_xoa where cmnd = ? and nhom = 1";

    //xóa toàn bộ
    public static String LICH_SU_DELETE_ALL_NHAN_KHAU_XOA = "Delete from lich_su_hoat_dong_xoa where nhom = 1";

    //lưu lại chủ hộ
    public static String LICH_SU_QUERY_XOA_CMND_CHU_HO= "Select nk.cmnd from nhan_khau nk, ho_khau hk\n" +
            "            where hk.idChuHo = nk.idNhanKhau\n" +
            "            and hk.idHoKhau = ?";

    //Hoàn tác ( thêm lại ) hộ khẩu, hộ khẩu - nhân khẩu
    //them HK
    public static String LICH_SU_KHOI_PHUC_HO_KHAU ="INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES\n" +
                                                                                                           "(?, ?, ?, ?, ?, ?, ?)";
    //Lấy ID hộ khẩu vừa tạo để thêm hk_nk
    public static String HO_KHAU_MAX = " select max(idHoKhau) from ho_khau ";

    //ktra thành viên đã bị xóa chưa
    public static String LICH_SU_CHECK_THANH_VIEN_DA_XOA_CHUA = "SELECT idNhanKhau from nhan_khau WHERE idNhanKhau = ?";

    //xóa vĩnh viễn
    public static String LICH_SU_DELETE_HO_KHAU_XOA = "Delete from lich_su_hoat_dong_xoa where cmnd = ? and nhom = 2";

    //xóa toàn bộ
    public static String LICH_SU_DELETE_ALL_HO_KHAU_XOA = "Delete from lich_su_hoat_dong_xoa where nhom = 2";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // chi_tiet_dip_dac_biet
    public static String CHI_TIET_DIP_DAC_BIET_THEM_NHAN_KHAU = "INSERT INTO chi_tiet_dip_dac_biet(idDip, idNhanKhau, nhom, kiemtra) VALUES (?, ?, ?, ?)";

    public static String CHI_TIET_DIP_DAC_BIET_BANG_NHAN_THUONG = "SELECT n.idNhanKhau, n.hoTen, c.*\n" +
            "FROM nhan_khau n, chi_tiet_dip_dac_biet c\n" +
            "WHERE n.idNhanKhau = c.idNhanKhau\n" +
            "and c.idDip = ?";

    public static String CHI_TIET_DIP_DAC_BIET_TRA_CUU = "SELECT n.idNhanKhau, n.hoTen, c.*\n" +
            "FROM nhan_khau n, chi_tiet_dip_dac_biet c\n" +
            "WHERE n.idNhanKhau = c.idNhanKhau\n" +
            "and c.idDip = ? and n.hoTen LIKE ? and c.nhom = ?";

    public static String CHI_TIET_DIP_DAC_BIET_KIEM_TRA = "UPDATE chi_tiet_dip_dac_biet SET kiemtra = ? WHERE idDip = ? and idNhanKhau = ?";

    public static String DIP_DAC_BIET_QUERY_T05_NGUOI = "select count(idNhanKhau) as t05Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 1\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_T614_NGUOI = "select count(idNhanKhau) as t614Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 2\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_T1517_NGUOI = "select count(idNhanKhau) as t1517Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 3\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_NGUOI_DA_TRAO = "select count(ctddb.idNhanKhau) as nguoiDaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 1";
    public static String DIP_DAC_BIET_QUERY_NGUOI_CHUA_TRAO = "select count(ctddb.idNhanKhau) as nguoiChuaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 0";
    public static String DIP_DAC_BIET_QUERY_HO_DA_TRAO = "select count(distinct hknk.idHoKhau) as hoDaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 1";
    public static String DIP_DAC_BIET_QUERY_HO_CHUA_TRAO = "select count(distinct hknk.idHoKhau) as hoChuaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 0";

    // chi_tiet_dip_hoc_sinh_gioi
    public static String CHI_TIET_HOC_SINH_GIOI_BANG_THEM_MINH_CHUNG = "SELECT n.idNhanKhau, n.hoTen, n.ngaySinh, h.diaChi\n" +
            "FROM nhan_khau n, ho_khau h, ho_khau_nhan_khau nh\n" +
            "WHERE n.idNhanKhau = nh.idNhanKhau and h.idHoKhau = nh.idHoKhau\n" +
            "and n.idNhanKhau NOT IN (SELECT idNhanKhau FROM chi_tiet_dip_hoc_sinh_gioi WHERE idDip = ?)";

    public static String CHI_TIET_HOC_SINH_GIOI_THEM_MINH_CHUNG = "INSERT INTO chi_tiet_dip_hoc_sinh_gioi(idDip, idNhanKhau, truong, lop, nhom, minhChung, kiemtra) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static String CHI_TIET_HOC_SINH_GIOI_SUA_MINH_CHUNG = "UPDATE chi_tiet_dip_hoc_sinh_gioi SET\n" +
            "truong = ?,\n" +
            "lop = ?,\n" +
            "nhom = ?,\n" +
            "minhChung = ?\n" +
            "WHERE idDip = ? and idNhanKhau = ?";

    public static String CHI_TIET_HOC_SINH_GIOI_XOA_MINH_CHUNG = "DELETE FROM chi_tiet_dip_hoc_sinh_gioi WHERE idDip = ? and idNhanKhau = ?";

    public static String CHI_TIET_HOC_SINH_GIOI_BANG_NHAN_THUONG = "SELECT n.idNhanKhau, n.hoTen, c.*\n" +
            "FROM nhan_khau n, chi_tiet_dip_hoc_sinh_gioi c\n" +
            "WHERE n.idNhanKhau = c.idNhanKhau\n" +
            "and c.idDip = ?";

    public static String CHI_TIET_HOC_SINH_GIOI_KIEM_TRA = "UPDATE chi_tiet_dip_hoc_sinh_gioi SET kiemtra = ? WHERE idDip = ? and idNhanKhau = ?";

    public static String HOC_SINH_GIOI_QUERY_DAC_BIET_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 1\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_GIOI_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 2\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_KHA_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 3\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_NGUOI_DA_TRAO = "select count(ctdhsg.idNhanKhau) as nguoiDaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 1";
    public static String HOC_SINH_GIOI_QUERY_NGUOI_CHUA_TRAO = "select count(ctdhsg.idNhanKhau) as nguoiChuaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 0";
    public static String HOC_SINH_GIOI_QUERY_HO_DA_TRAO = "select count(distinct hknk.idHoKhau) as hoDaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 1";
    public static String HOC_SINH_GIOI_QUERY_HO_CHUA_TRAO = "select count(distinct hknk.idHoKhau) as hoChuaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 0";
}
