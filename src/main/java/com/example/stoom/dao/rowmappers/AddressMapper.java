package com.example.stoom.dao.rowmappers;

import com.example.stoom.entity.Address;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public final class AddressMapper implements RowMapper {

    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();

        address.setId(rs.getLong("id"));
        address.setStreetName(rs.getString("street_name"));
        address.setNumber(rs.getString("address_number"));
        address.setComplement(rs.getString("complement"));
        address.setNeighbourhood(rs.getString("neighbourhood"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setCountry(rs.getString("country"));
        address.setZipCode(rs.getString("zip_code"));
        address.setLatitude(rs.getString("latitude"));
        address.setLongitude(rs.getString("longitude"));

        return address;
    }
}

