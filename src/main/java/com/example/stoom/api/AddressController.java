package com.example.stoom.api;

import com.example.stoom.entity.Address;
import com.example.stoom.model.AddressRequest;
import com.example.stoom.model.GenericAPIResponse;
import com.example.stoom.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/v1/address")
@RestController
public class AddressController {
    private final IAddressService _addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        _addressService = addressService;
    }

    @PostMapping
    public GenericAPIResponse insert(@Valid @RequestBody AddressRequest request) {
        return _addressService.insert(request);
    }

    @PutMapping(path = "{id}")
    public GenericAPIResponse update(@PathVariable("id") Long id, @Validated @NonNull @RequestBody AddressRequest request) {
        return _addressService.update(id, request);
    }

    @DeleteMapping(path = "{id}")
    public GenericAPIResponse delete(@PathVariable("id") Long id) {
        return _addressService.delete(id);
    }

    @GetMapping
    public List<Address> get() {
        return _addressService.get();
    }

    @GetMapping(path = "{id}")
    public Address get(@PathVariable("id") Long id) {
        return _addressService.get(id).orElse(null);
    }
}
