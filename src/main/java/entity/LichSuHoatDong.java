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
public class LichSuHoatDong {
    private int nhom = 0;
    private String tenHD;

    private int idHD;

    private String noiDungHD;

    private Date thoiGianHD;

}
