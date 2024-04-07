package com.example.library.model.dto.request;

import com.example.library.model.enums.Rate;
import com.example.library.model.enums.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookInfoRequest {
    String nameBook;
    Type typeBook;
    String author;
    Integer year;
    Float cost;
    Rate rating;
    Integer quantity;
}
