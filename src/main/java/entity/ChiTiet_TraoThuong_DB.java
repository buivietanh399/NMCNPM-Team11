package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTiet_TraoThuong_DB {
    private int idNhanKhau;
    private String hoten;

    private int doTuoi;

    private String phanQua;

    private int soTien;
}
