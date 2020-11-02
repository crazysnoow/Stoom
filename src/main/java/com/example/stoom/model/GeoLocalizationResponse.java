package com.example.stoom.model;

import com.example.stoom.model.googleapi.GeoCodeStatusEnum;

public class GeoLocalizationResponse {
    private String latitude;
    private String longitude;
    private Boolean success;
    private String statusCode;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Boolean getSuccess() {

        if (statusCode.equalsIgnoreCase(GeoCodeStatusEnum.OK.name())) {
            success = true;
        } else {
            success = false;
        }

        return success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
