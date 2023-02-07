package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NguoiDung {
    private Integer id;
    private String taiKhoan;
    private String matKhau;

    private String gioiTinh;

    private String ngaySinh;

    private String email;

    private String SDT;

    private String chucVu;

    private String donViCongTac;

    private String anhDaiDien;


    public String getTaiKhoan() {
        return this.taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public void setanhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }
}
