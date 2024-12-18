package com.example.library.service;

import com.example.library.model.db.entity.Supply;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.example.library.model.dto.response.SupplyInfoResponse;

import java.util.List;

public interface SupplyService {

     SupplyInfoResponse createSupply(SupplyInfoRequest request);

     SupplyInfoResponse getSupply( Long id);

    Supply getSupplyDb(Long id);

    SupplyInfoResponse updateSupply(Long id, SupplyInfoRequest request);

     void deleteSupply(Long id);

    List<Supply> getAllSupplies();

    SupplyInfoRequest convertSupply(Supply s);
}
