package com.example.library.model.dto.request;

import com.example.library.model.enums.Function;
import com.example.library.model.enums.Shift;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeInfoRequest {
    String fio;
    String phone;
    Function function;
    Shift shift;
}
