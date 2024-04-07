package com.example.library.model.db.entity;

import com.example.library.model.enums.Function;
import com.example.library.model.enums.Shift;
import com.example.library.model.enums.Status;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="employees")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Employee {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String fio;
    String phone;
    @Enumerated(EnumType.STRING)
    Function function;
    @Enumerated(EnumType.STRING)
    Shift shift;
    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    Status status;

}
