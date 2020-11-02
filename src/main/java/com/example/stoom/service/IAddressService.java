package com.example.stoom.service;

import com.example.stoom.entity.Address;
import com.example.stoom.model.AddressRequest;
import com.example.stoom.model.GenericAPIResponse;

import java.util.List;
import java.util.Optional;

public interface IAddressService {
    List<Address> get();
    Optional<Address> get(Long id);

    GenericAPIResponse insert(AddressRequest request);

    GenericAPIResponse update(Long id, AddressRequest request);

    GenericAPIResponse delete(Long id);
}
