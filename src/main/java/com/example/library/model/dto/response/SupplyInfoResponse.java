package com.example.library.model.dto.response;

import com.example.library.model.db.entity.Request;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplyInfoResponse extends SupplyInfoRequest {
    Long id;
}
