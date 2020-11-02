package com.example.stoom.service;

import com.example.stoom.entity.Address;
import com.example.stoom.model.GeoLocalizationResponse;

public interface IGeolocalizationService {
    GeoLocalizationResponse get(Address address);
}
