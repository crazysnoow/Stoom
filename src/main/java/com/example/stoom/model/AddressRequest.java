package com.example.stoom.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AddressRequest {

    @Size(max= 100)
    @NotBlank(message = "Nome da rua obrigatório")
    private String streetName;

    @Size(max= 20)
    @NotBlank(message = "Número obrigatório")
    private String number;

    @Size(max= 30)
    private String complement;

    @Size(max= 100)
    @NotBlank(message = "Bairro obrigatório")
    private String neighbourhood;

    @Size(max= 100)
    @NotBlank(message = "Cidade obrigatório")
    private String city;

    @Size(max= 100)
    @NotBlank(message = "Estado obrigatório")
    private String state;

    @Size(max= 100)
    @NotBlank(message = "País obrigatório")
    private String country;

    @Size(max= 25)
    @NotBlank(message = "CEP obrigatório")
    private String zipCode;

    private String latitude;

    private String longitude;

    public AddressRequest(@Size(max = 100) @NotBlank(message = "Nome da rua obrigatório") String streetName, @Size(max = 20) @NotBlank(message = "Número obrigatório") String number, @Size(max = 30) String complement, @Size(max = 100) @NotBlank(message = "Bairro obrigatório") String neighbourhood, @Size(max = 100) @NotBlank(message = "Cidade obrigatório") String city, @Size(max = 100) @NotBlank(message = "Estado obrigatório") String state, @Size(max = 100) @NotBlank(message = "País obrigatório") String country, @Size(max = 25) @NotBlank(message = "CEP obrigatório") String zipCode, String latitude, String longitude) {
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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
}
