package entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LichSuHoatDongXoa {
    private int nhom = 0;

    private int idHD;

    private String noiDungHDXoa;

    private Date thoiGianHD;

    private String hoTen;

    private String CMND;
}
