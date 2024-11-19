package com.example.library.model.dto.extra;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {
        private String bookName;
        private String dateBorrow;
        private String dateReturn;
}

