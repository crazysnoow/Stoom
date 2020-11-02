package com.example.stoom.service;

import com.example.stoom.entity.Address;
import com.example.stoom.model.GeoLocalizationResponse;
import com.example.stoom.model.googleapi.GeoCodeStatusEnum;
import com.example.stoom.model.googleapi.GeocodeLocation;
import com.example.stoom.model.googleapi.GeocodeObject;
import com.example.stoom.model.googleapi.GeocodeResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

@Service
public class GeolocalizationService implements IGeolocalizationService {

    private final String APIKEY = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

    public GeolocalizationService() {
    }

    public GeoLocalizationResponse get(Address address) {
        GeoLocalizationResponse response = new GeoLocalizationResponse();

        String addressString = generateAddressString(address);

        final String uri = "https://maps.googleapis.com/maps/api/geocode/json?address=" + addressString + "&key=" + APIKEY;

        RestTemplate restTemplate = new RestTemplate();
        GeocodeResult result = restTemplate.getForObject(uri, GeocodeResult.class);

        response.setStatusCode(result.getStatus());

        if (result.getStatus().equalsIgnoreCase(GeoCodeStatusEnum.OK.name())) {
            Optional<GeocodeObject> geocodeObject = result.getResults().stream().findFirst();

            GeocodeLocation geocodeLocation = geocodeObject.map(m -> m.getGeometry()).map(m -> m.getGeocodeLocation()).orElse(null);

            if (geocodeLocation != null) {
                response.setLatitude(geocodeLocation.getLatitude());
                response.setLongitude(geocodeLocation.getLongitude());
            }
        }

        return response;
    }

    private String generateAddressString(Address address) {
        String encodedAddressString;

        String addressString = String.format("%s %s %s %s %s %s %s", address.getNumber(), address.getStreetName(), address.getNeighbourhood(),
                address.getCity(), address.getState(), address.getCountry(), address.getZipCode());

        try {
            encodedAddressString = URLEncoder.encode(addressString, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            encodedAddressString = null;
        }

        return encodedAddressString;
    }

}
