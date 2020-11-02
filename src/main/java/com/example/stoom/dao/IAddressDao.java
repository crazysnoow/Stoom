package com.example.stoom.dao;

import com.example.stoom.entity.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressDao {

    List<Address> get();
    Optional<Address> get(Long id);

    Optional<Integer> insert(Address address);

    void update(Address address);

    void delete(Long id);
}
