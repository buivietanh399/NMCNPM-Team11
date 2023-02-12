CREATE DATABASE nmcnpm_team11;

USE nmcnpm_team11;

-- 1. Người dùng
CREATE TABLE nguoi_dung(
                           id INT NOT NULL AUTO_INCREMENT,
                           taiKhoan VARCHAR(255) UNIQUE NOT NULL,
                           matKhau VARCHAR(255) NOT NULL,
                           hoTen   NVARCHAR(255) NOT NULL,
                           ngaySinh date NOT NULL,
                           gioiTinh NVARCHAR(5) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           SDT VARCHAR(20) NOT NULL,
                           chucVu NVARCHAR(255) NOT NULL,
                           donViCongTac NVARCHAR(255) NOT NULL,
                           anhDaiDien VARCHAR(255),
                           CONSTRAINT PK_nguoi_dung PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE nguoi_dung
    ADD UNIQUE(taiKhoan);

INSERT INTO nguoi_dung(taiKhoan, matKhau, hoTen, ngaySinh, gioiTinh, email, SDT, chucVu, donViCongTac, anhDaiDien) VALUES
    ('admin', '1', N'Monalisa Nguyễn', '2000-1-1', N'Nữ', 'Lisa_cute@gmail.com', '0123456789', N'Tổ trưởng tổ 11 phường Ngọc Thụy',N'Tổ 11 phường Ngọc Thụy', 'https://stickerly.pstatic.net/sticker_pack/a5oCnZ0p9IhaDqiLssqmg/7SV26A/31/34320c7c-a04c-4ab2-9df2-84ec0b35d8c2.png');

-- 2. Nhân khẩu
CREATE TABLE nhan_khau(
                          idNhanKhau INT NOT NULL AUTO_INCREMENT,
                          hoTen NVARCHAR(255) NOT NULL,
                          biDanh NVARCHAR(255),
                          ngaySinh DATE NOT NULL,
                          noiSinh NVARCHAR(255) NOT NULL,
                          gioiTinh NVARCHAR(255) NOT NULL,
                          nguyenQuan NVARCHAR(255) NOT NULL,
                          danToc NVARCHAR(255) NOT NULL,
                          tonGiao NVARCHAR(255) NOT NULL,
                          quocTich NVARCHAR(255) NOT NULL,
                          ngheNghiep NVARCHAR(255),
                          noiLamViec VARCHAR(255),
                          cmnd VARCHAR(255),
                          ngayCap DATE,
                          chuyenDenNgay DATE,
                          noiThuongTruTruoc NVARCHAR(255),
                          trangThai NVARCHAR(255),
                          CONSTRAINT PK_nhan_khau PRIMARY KEY(idNhanKhau),
                          CONSTRAINT CHK_nhan_khau_gioi_tinh CHECK (gioiTinh IN (N'Nam', N'Nữ'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO nhan_khau(hoTen, biDanh, ngaySinh, noiSinh, gioiTinh, nguyenQuan, danToc, tonGiao, quocTich, ngheNghiep, noiLamViec, cmnd, ngayCap, chuyenDenNgay, noiThuongTruTruoc, trangThai) VALUES
                                                                                                                                                                                                (N'Trịnh Văn An', NULL, '1990-12-07', N'Bệnh viện Đại học Y 1', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Giáo Viên', N'Trường THCS Chu Văn An', 123456789, '2010-10-10', '2019-12-11', N'Số 1 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),
                                                                                                                                                                                                (N'Trần Thanh Duyên', NULL, '1997-12-23', N'Bệnh viện Đại học Y 2', N'Nữ', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam', N'Nhân viên văn phòng', N'Công ty ABC', 123456781, '2010-10-1', '2019-12-12', N'Số 2 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi'),
                                                                                                                                                                                                (N'Nguyễn Minh Quân', NULL, '1995-05-31', N'Bệnh viện Đại học Y 1', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Kỹ sư', N'Viettel', 123456782, '2010-10-2', '2019-12-13', N'Số 3 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm Trú'),
                                                                                                                                                                                                 (N'Nguyễn Tiến Dũng', NULL, '1964-06-03', N'Bệnh viện Đại học Y 1', N'Nam', N'Hải Dương', N'Kinh', N'Thiên chúa giáo', N'Việt Nam', N'Phó giám đốc', N'Công ty EN', 123456783, '2010-10-3', '2019-12-14', N'Số 4 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),
                                                                                                                                                                                                (N'Vũ Mỹ Linh', NULL, '1965-12-06', N'Bệnh viện Đại học Y 3', N'Nữ', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Nội trợ', N'Tại nhà', 123456784, '2010-10-4', '2019-12-15', N'Số 5 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Nguyễn Tiến Đạt', NULL, '1990-09-09', N'Bệnh viện Đại học Y 5', N'Nam', N'Hải Dương', N'Kinh', N'Thiên chúa giáo', N'Việt Nam', N'Kỹ sư điện', N'EVP', 123456785, '2010-10-5', '2019-12-11', N'Số 1 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Nguyễn Trà My', NULL, '1997-12-12', N'Bệnh viện Đại học Y 4', N'Nữ', N'Hải Dương', N'Kinh', N'Thiên chúa giáo', N'Việt Nam', N'Luật sư', N'Văn phòng luật sư 123', 123456786, '2010-10-6', '2019-12-11', N'Số 1 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Trần Văn Nam', NULL, '1980-07-09', N'Bệnh viện Đại học Y 1', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Giảng viên đại học', N'Đại học Bách khoa Hà Nội', 123456787, '2010-10-7', '2019-12-12', N'Số 2 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi'),

                                                                                                                                                                                                (N'Nguyễn Minh Tuyết', NULL, '2011-09-02', N'Bệnh viện Đại học Y 2', N'Nữ', N'Nam Định', N'Kinh', N'Không', N'Việt Nam', N'Học sinh', N'Trường THCS Chu Văn An', 123456788, '2010-10-8', '2019-12-13', N'Số 3 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),

                                                                                                                                                                                                (N'Trần Trung Kiên', NULL, '2007-12-25', N'Bệnh viện Đại học Y 2', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Học sinh', N'Trường THPT Chu Văn An', '', NULL, '2019-12-14', N'Số 4 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                                 (N'Trần Thúy Ngọc', NULL, '2013-01-15', N'Bệnh viện Đại học Y 5', N'Nữ', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Học sinh', N'Trường tiểu học Chu Văn An', '', NULL, '2019-12-16', N'Số 10 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm trú'),

                                                                                                                                                                                                 (N'Lý Văn Công', NULL, '1945-06-04', N'Bệnh viện Đại học Y 3', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Về hưu', N'Không', 123456780, '2010-10-9', '2019-12-15', N'Số 5 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã mất'),

                                                                                                                                                                                                 (N'Bùi Thị Hà', NULL, '1948-02-03', N'Bệnh viện Đại học Y 4', N'Nữ', N'Hải Phòng', N'Kinh', N'Không', N'Việt Nam', N'Nội trợ', N'Tại nhà', 123456790, '2010-10-11', '2019-12-15', N'Số 5 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi'),

                                                                                                                                                                                                 (N'Trần Quốc Việt', NULL, '1948-02-11', N'Bệnh viện Đại học Y 4', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Bác sĩ', N'Bệnh viện A', 123456791, '2010-10-11', '2019-12-15', N'Số 10 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                                 (N'Trịnh Hồng Phượng', NULL, '2010-03-23', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Học sinh', N'Trường THCS Chu Văn An', '', '2010-10-11', '2019-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Trần Văn Báu', NULL, '2002-02-25', N'Bệnh viện Đại học Y 3', N'Nam', N'Hà Nội', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Đinh Viết Tỵ', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Trần Tuân Hưng', NULL, '2002-02-02', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường trú'),

                                                                                                                                                                                                 (N'Đinh Viết Tỵ', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),

                                                                                                                                                                                                 (N'Bùi Việt Anh', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),

                                                                                                                                                                                                 (N'Nguyễn Tuấn Thành', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                                 (N'Trần Văn Thế', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm vắng'),

                                                                                                                                                                                                (N'Nguyễn Thuý Loan', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2020-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                               (N'Nguyễn Thị Khánh Linh', NULL, '2002-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2021-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                                (N'Lê Ngọc Khánh Huyền', NULL, '2007-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'THCS Aí Mộ', '', '2010-10-11', '2021-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Thường Trú'),

                                                                                                                                                                                               (N'Nguyễn Đan Diệu Linh', NULL, '2004-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'THPT Chu Văn An', '', '2010-10-11', '2021-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi'),

                                                                                                                                                                                               (N'Trịnh Xuân Anh', NULL, '2010-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Tiểu học Ngọc Thụy', '', '2010-10-11', '2022-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                               (N'Nguyễn Thị Thu', NULL, '1960-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2022-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                              (N'Nguyễn Thị Huyền', NULL, '1960-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2022-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi'),

                                                                                                                                                                                               (N'Nguyễn Thị Hoa', NULL, '2004-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2022-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm Trú'),

                                                                                                                                                                                             (N'Nguyễn Thị Ngọc', NULL, '1960-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2022-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Tạm Trú'),

                                                                                                                                                                                               (N'Nguyễn Thị Bích', NULL, '1990-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2023-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                               (N'Nguyễn Thị Phương', NULL, '1990-01-01', N'Bệnh viện Đại học Y 3', N'Nữ', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2023-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N''),

                                                                                                                                                                                         (N'Nguyễn Văn Tú', NULL, '2000-01-01', N'Bệnh viện Đại học Y 3', N'Nam', N'Thanh Hoá', N'Kinh', N'Không', N'Việt Nam', N'Sinh viên', N'Đại học Bách Khoa Hà Nội', '', '2010-10-11', '2023-12-15', N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Đã chuyển đi');
-- 7. Tạm trú
CREATE TABLE tam_tru(
                        id INT NOT NULL AUTO_INCREMENT,
                        idNhanKhau INT NOT NULL,
                        noiThuongTru NVARCHAR(255) NOT NULL,
                        noiTamTru NVARCHAR(255) NOT NULL,
                        tuNgay DATE NOT NULL,
                        denNgay DATE ,
                        lyDo NVARCHAR(255),
                        CONSTRAINT PK_tam_tru PRIMARY KEY(id),
                        CONSTRAINT FK_tam_tru_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO tam_tru(idNhanKhau, noiThuongTru, noiTamTru, tuNgay, denNgay, lyDo) VALUES
    (11, N'Số 10 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Số 6 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-12-16', '2023-10-10', NULL),
    (3, N'Số 11 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Số 7 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-12-16', '2023-10-10', NULL),
    (31, N'Số 12 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Số 8 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-12-16', '2023-11-10', NULL),
    (30, N'Số 13 Lê Thanh Nghị, Quận Hai Bà Trưng, Hà Nội', N'Số 9 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-12-16', '2023-12-10', NULL);
-- 8. Tạm vắng
CREATE TABLE tam_vang(
                         id INT NOT NULL AUTO_INCREMENT,
                         idNhanKhau INT NOT NULL,
                         noiTamTru NVARCHAR(255) NOT NULL,
                         tuNgay DATE NOT NULL,
                         denNgay DATE ,
                         lyDo NVARCHAR(255),
                         CONSTRAINT PK_tam_vang PRIMARY KEY(id),
                         CONSTRAINT FK_tam_vang_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
INSERT INTO tam_vang(idNhanKhau, noiTamTru, tuNgay, denNgay, lyDo) VALUES
                                                                       (4, N'Số 8 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-11-11', '2023-12-12', NULL),
                                                                       (9, N'Số 7 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-11-10', '2023-12-11', NULL),
                                                                        (19, N'Số 5 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-12-10', '2023-12-11', NULL),
                                                                        (20, N'Số 6 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-12-10', '2022-12-11', NULL),
                                                                        (22, N'Số 4 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', '2020-13-10', '2023-12-11', NULL);
-- 3. Hộ khẩu
CREATE TABLE ho_khau(
                        idHoKhau INT NOT NULL AUTO_INCREMENT,
                        idChuHo INT NOT NULL,
                        tinhThanhPho NVARCHAR(255) NOT NULL,
                        quanHuyen NVARCHAR(255) NOT NULL,
                        phuongXa NVARCHAR(255) NOT NULL,
                        diaChi NVARCHAR(255) NOT NULL,
                        ngayTao DATE NOT NULL,
                        trangThai NVARCHAR(255) NOT NULL,
                        CONSTRAINT PK_ho_khau PRIMARY KEY(idHoKhau),
                        CONSTRAINT FK_ho_khau_nhan_khau FOREIGN KEY(idChuHo) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES
                                                                                                (1, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 1 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-11-08', N'Thường trú'),
                                                                                                (2, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 2 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-11-07', N'Đã chuyển đi'),
                                                                                                (3, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 3 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-11-06', N'Thường trú'),
                                                                                                (4, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 4 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2019-11-05', N'Thường trú'),
                                                                                                (5, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2020-11-05', N'Đã chuyển đi'),
                                                                                                (6, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2020-11-05', N'Đã chuyển đi'),
                                                                                                (7, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2021-11-05', N'Thường trú'),
                                                                                                (8, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2021-11-05', N'Thường trú'),
                                                                                                (9, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2021-11-05', N'Thường trú'),
                                                                                                (10, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-05', N'Đã chuyển đi'),
                                                                                                (11, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2022-11-05', N'Thường trú'),
                                                                                                (12, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2023-11-05', N'Thường trú'),
                                                                                                (13, N'Hà Nội', N'Hai Bà Trưng', N'Bách Khoa', N'Số 5 Tạ Quang Bửu, Quận Hai Bà Trưng, Hà Nội', '2023-11-05', N'Thường trú');

-- 4. Hộ khẩu - nhân khẩu
CREATE TABLE ho_khau_nhan_khau(
                                  idHoKhau INT NOT NULL ,
                                  idNhanKhau INT NOT NULL ,
                                  quanHeChuHo NVARCHAR(255) NOT NULL,
                                  CONSTRAINT PK_ho_khau_nhan_khau PRIMARY KEY (idHoKhau, idNhanKhau),
                                  CONSTRAINT FK_ho_khau_nhan_khau_ho_khau FOREIGN KEY(idHoKhau) REFERENCES ho_khau(idHoKhau) ON DELETE CASCADE,
                                  CONSTRAINT FK_ho_khau_nhan_khau_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO ho_khau_nhan_khau(idHoKhau, idNhanKhau, quanHeChuHo) VALUES
                                                                     (1, 6, N'Anh trai'),
                                                                     (1, 7, N'Vợ'),
                                                                     (2, 8, N'Bố'),
                                                                     (3, 9, N'Bố'),
                                                                     (4, 10, N'Con trai'),
                                                                     (5, 12, N'Ông ngoại'),
                                                                     (5, 13, N'Bà ngoại'),
                                                                     (6, 1, N'Con trai'),
                                                                     (6, 27, N'Con trai'),
                                                                     (2, 2, N'Con gái'),
                                                                     (3, 25, N'Con gái'),
                                                                     (4, 26, N'Con gái');

-- 5. Chuyển nhân khẩu
CREATE TABLE chuyen_nhan_khau(
                                 id INT NOT NULL AUTO_INCREMENT,
                                 idNhanKhau INT NOT NULL,
                                 ngayChuyenDi DATE NOT NULL,
                                 noiChuyenDen NVARCHAR(255) NOT NULL,
                                 ghiChu NVARCHAR(255),
                                 CONSTRAINT PK_chuyen_nhan_khau PRIMARY KEY (id),
                                 CONSTRAINT FK_chuyen_nhan_khau_nhan_khau FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO chuyen_nhan_khau(idNhanKhau, ngayChuyenDi, noiChuyenDen, ghiChu) VALUES
    (13, '2020-10-10', N'Số 10 Đại Cồ Việt, Quận Hai Bà Trưng, Hà Nội', NULL);

-- 6. Chuyển hộ khẩu
CREATE TABLE chuyen_ho_khau(
                               id INT NOT NULL AUTO_INCREMENT,
                               idHoKhau INT NOT NULL,
                               ngayChuyenDi DATE NOT NULL,
                               noiChuyenDen NVARCHAR(255) NOT NULL,
                               ghiChu NVARCHAR(255),
                               CONSTRAINT PK_chuyen_ho_khau PRIMARY KEY(id),
                               CONSTRAINT FK_chuyen_ho_khau_ho_khau FOREIGN KEY(idHoKhau) REFERENCES ho_khau(idHoKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




-- 9. Dịp đặc biệt
CREATE TABLE dip_dac_biet(
                             idDip INT NOT NULL AUTO_INCREMENT,
                             ten NVARCHAR(255) NOT NULL,
                             nam INT NOT NULL,
                             moTa NVARCHAR(255),
                             phanQua05 NVARCHAR(255) NOT NULL,
                             phanQua614 NVARCHAR(255) NOT NULL,
                             phanQua1517 NVARCHAR(255) NOT NULL,
                             tien05 FLOAT NOT NULL,
                             tien614 FLOAT NOT NULL,
                             tien1517 FLOAT NOT NULL,
                             CONSTRAINT PK_dip_dac_biet PRIMARY KEY(idDip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO dip_dac_biet(ten, nam, moTa, phanQua05, phanQua614, phanQua1517, tien05, tien614, tien1517) VALUES
                                                                                                            (N'Tết Trung Thu', 2020, NULL, N'1 mũ len', N'1 khăn len', N'1 đôi tất', 20000, 23000, 15000),
                                                                                                            (N'Giáng Sinh', 2020, NULL, N'1 gấu bông', N'5 cây kẹo mút', N'1 bao tay', 30000, 20000, 25000);

-- 10. Dịp học sinh giỏi
CREATE TABLE dip_hoc_sinh_gioi(
                                  idDip INT NOT NULL AUTO_INCREMENT,
                                  nam INT NOT NULL,
                                  moTa NVARCHAR(255),
                                  phanQuaDacBiet NVARCHAR(255) NOT NULL,
                                  phanQuaGioi NVARCHAR(255) NOT NULL,
                                  phanQuaKha NVARCHAR(255) NOT NULL,
                                  tienDacBiet FLOAT NOT NULL,
                                  tienGioi FLOAT NOT NULL,
                                  tienKha FLOAT NOT NULL,
                                  CONSTRAINT PK_dip_hoc_sinh_gioi PRIMARY KEY(idDip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO dip_hoc_sinh_gioi(nam, moTa, phanQuaDacBiet, phanQuaGioi, phanQuaKha, tienDacBiet, tienGioi, tienKha) VALUES
                                                                                                                      (2020, NULL, N'10 quyển vở', N'7 quyển vở', N'5 quyển vở', 50000, 35000, 25000),
                                                                                                                      (2021, NULL, N'10 quyển vở', N'7 quyển vở', N'5 quyển vở', 50000, 35000, 25000),
                                                                                                                      (2022, NULL, N'10 quyển vở', N'7 quyển vở', N'5 quyển vở', 50000, 35000, 25000),
                                                                                                                      (2023, NULL, N'10 quyển vở', N'7 quyển vở', N'5 quyển vở', 50000, 35000, 25000);

-- 11. Chi tiết dịp đặc biệt
CREATE TABLE chi_tiet_dip_dac_biet(
                                      idDip INT NOT NULL,
                                      idNhanKhau INT NOT NULL,
                                      nhom INT NOT NULL,
                                      kiemtra BOOLEAN NOT NULL,
                                      CONSTRAINT PK_chi_tiet_dip_dac_biet PRIMARY KEY(idDip, idNhanKhau),
                                      CONSTRAINT FK_chi_tiet_dip_dac_biet_dip_dac_biet FOREIGN KEY(idDip) REFERENCES dip_dac_biet(idDip) ON DELETE CASCADE,
                                      CONSTRAINT FK_chi_tiet_dip_dac_biet FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO chi_tiet_dip_dac_biet(idDip, idNhanKhau, nhom, kiemtra) VALUES
                                                                        (1, 9, 2, 1),
                                                                        (1, 10, 3, 1),
                                                                        (2, 9, 2, 1),
                                                                        (2, 10, 3, 0);



-- 12. Chi tiết dịp học sinh giỏi
CREATE TABLE chi_tiet_dip_hoc_sinh_gioi(
                                           idDip INT NOT NULL,
                                           idNhanKhau INT NOT NULL,
                                           truong NVARCHAR(255) NOT NULL,
                                           lop VARCHAR(255) NOT NULL,
                                           nhom INT NOT NULL,
                                           minhChung NVARCHAR(255) NOT NULL,
                                           kiemtra BOOLEAN NOT NULL,
                                           CONSTRAINT PK_chi_tiet_dip_hoc_sinh_gioi PRIMARY KEY(idDip, idNhanKhau),
                                           CONSTRAINT FK_chi_tiet_dip_hoc_sinh_gioi_dip_hoc_sinh_gioi FOREIGN KEY(idDip) REFERENCES dip_hoc_sinh_gioi(idDip) ON DELETE CASCADE,
                                           CONSTRAINT FK_chi_tiet_dip_hoc_sinh_gioi FOREIGN KEY(idNhanKhau) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO chi_tiet_dip_hoc_sinh_gioi(idDip, idNhanKhau, truong, lop, nhom, minhChung, kiemtra) VALUES
                                                                                                     (1, 1, N'Trường THCS Chu Văn An', 6, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 1),
                                                                                                     (1, 2, N'Trường tiểu học Chu Văn An', 1, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 1),
                                                                                                     (2, 1, N'Trường THCS Chu Văn An', 7, 2, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 1),
                                                                                                     (2, 26, N'Trường THPT Chu Văn An', 12, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 0),
                                                                                                     (2, 25, N'Trường THCS Ái Mộ', 7, 3, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 0),
                                                                                                     (2, 27, N'Tiểu học Ngọc Thụy', 12, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 1),
                                                                                                     (3, 25, N'Trường THCS Ái Mộ', 8, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 0),
                                                                                                     (3, 26, N'Trường THPT Chu Văn An', 12, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 0),
                                                                                                     (4, 25, N'Trường THCS Ái Mộ', 9, 1, 'https://inhanoi.net/wp-content/uploads/2020/05/dich-vu-in-giay-khen-dep-chat-luong.jpg', 0);

-- 13. Khai tử
CREATE TABLE khai_tu(
                        idNguoiMat INT NOT NULL,
                        idNguoiKhai INT NOT NULL,
                        ngayKhai DATE NOT NULL,
                        ngayMat DATE NOT NULL,
                        liDoMat NVARCHAR(255),
                        CONSTRAINT  PK_khai_tu PRIMARY KEY(idNguoiMat),
                        CONSTRAINT FK_khai_tu_nguoi_mat_nhan_khau FOREIGN KEY(idNguoiMat) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE,
                        CONSTRAINT FK_khai_tu_nguoi_khai_nhan_khau FOREIGN KEY(idNguoiKhai) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO khai_tu(idNguoiMat, idNguoiKhai, ngayKhai, ngayMat, liDoMat) VALUES
    (12, 5, '2020-10-10', '2020-10-1', 'Bệnh tim');


-- 14. Lịch sử hoạt động, idHD: id của đối tượng
CREATE TABLE lich_su_hoat_dong(
                       id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       nhom INT NOT NULL,
                       idHD1 INT ,
                       idHD2 INT ,
                       idHD3 INT ,
                       idHD4 INT ,
                       noiDungHD VARCHAR(255),
                       tenHD VARCHAR(255) ,
                       thoiGianHD date not null,
                       CONSTRAINT FK_nhankhau FOREIGN KEY(idHD1) REFERENCES nhan_khau(idNhanKhau) ON DELETE CASCADE,
                       CONSTRAINT FK_hokhau FOREIGN KEY(idHD2) REFERENCES ho_khau(idHoKhau) ON DELETE CASCADE,
                       CONSTRAINT FK_HSG FOREIGN KEY(idHD3) REFERENCES dip_hoc_sinh_gioi(idDip) ON DELETE CASCADE,
                       CONSTRAINT FK_DB FOREIGN KEY(idHD4) REFERENCES dip_dac_biet(idDip) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- 15.Gioi han LSHD
DELIMITER $$
CREATE TRIGGER tr_limit_table_rows
BEFORE INSERT ON lich_su_hoat_dong
FOR EACH ROW
BEGIN
  DECLARE row_count INT;

  SELECT COUNT(*) INTO row_count
  FROM lich_su_hoat_dong;

  IF row_count >= 1000 THEN
    DELETE FROM lich_su_hoat_dong
    ORDER BY id LIMIT 1;
  END IF;
END$$
DELIMITER ;