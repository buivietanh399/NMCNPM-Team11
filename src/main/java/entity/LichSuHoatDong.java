package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LichSuHoatDong {
    private String tenHD;

    private int idHD;

    private String noiDungHD;

    private LocalDate thoiGianHD;

}
