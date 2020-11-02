package com.example.stoom.model;

import com.example.stoom.model.googleapi.GeoCodeStatusEnum;

public class ManageGeolocalizationResponse {
    private boolean wasNeedToSearch;
    private boolean success;
    private String statusCode;

    public boolean getWasNeedToSearch() {
        return wasNeedToSearch;
    }

    public void setWasNeedToSearch(boolean wasNeedToSearch) {
        this.wasNeedToSearch = wasNeedToSearch;
    }

    public boolean isSuccess() {
        if (!wasNeedToSearch || (statusCode.equalsIgnoreCase(GeoCodeStatusEnum.OK.name()))) {
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
