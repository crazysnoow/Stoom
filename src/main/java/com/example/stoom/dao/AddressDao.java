package com.example.stoom.dao;

import com.example.stoom.dao.rowmappers.AddressMapper;
import com.example.stoom.entity.Address;
import com.example.stoom.enums.SqlActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository("postgre")
public class AddressDao implements IAddressDao {

    private final String FIELDS = "id, street_name, address_number, complement, neighbourhood, city, state, country, zip_code, latitude, longitude";

    private final JdbcTemplate _jdbcTemplate;

    @Autowired
    public AddressDao(JdbcTemplate jdbcTemplate) {
        _jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Address> get() {
        String sql = String.format("SELECT %s FROM address", FIELDS);

        return _jdbcTemplate.query(sql, new AddressMapper());
    }

    @Override
    public Optional<Address> get(Long id) {
        Address address;

        try {
            String sql = String.format("SELECT %s FROM address WHERE id = ?", FIELDS);
            address = (Address) _jdbcTemplate.queryForObject(sql, new Object[]{id}, new AddressMapper());
        } catch (EmptyResultDataAccessException ex) {
            address = null;
        }

        return Optional.ofNullable(address);
    }

    @Override
    public Optional<Integer> insert(Address address) {
        final String INSERT_SQL = "INSERT INTO address (street_name, address_number, complement, neighbourhood, city, state, country, zip_code, latitude, longitude) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        _jdbcTemplate.update(connection -> getAddressPreparedStatement(address, INSERT_SQL, connection, SqlActionEnum.INSERT), keyHolder);

        return Optional.ofNullable(keyHolder.getKey()).map(m -> m.intValue());
    }

    @Override
    public void update(Address address) {

        final String UPDATE_SQL = "UPDATE address SET street_name = ?, address_number = ?," +
                " complement = ?, neighbourhood = ?, city = ?, state = ?, country = ? , zip_code = ?, latitude = ?, longitude = ?" +
                " WHERE id = ?";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        _jdbcTemplate.update(connection -> getAddressPreparedStatement(address, UPDATE_SQL, connection, SqlActionEnum.UPDATE), keyHolder);
    }

    @Override
    public void delete(Long id) {

        _jdbcTemplate.update("DELETE FROM address where id = ?", id);

    }


    private PreparedStatement getAddressPreparedStatement(Address address, String SQL, Connection connection, SqlActionEnum sqlAction) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(SQL, new String[]{"id"});

        ps.setString(1, address.getStreetName());
        ps.setString(2, address.getNumber());
        ps.setString(3, address.getComplement());
        ps.setString(4, address.getNeighbourhood());
        ps.setString(5, address.getCity());
        ps.setString(6, address.getState());
        ps.setString(7, address.getCountry());
        ps.setString(8, address.getZipCode());
        ps.setString(9, address.getLatitude());
        ps.setString(10, address.getLongitude());

        if (sqlAction == SqlActionEnum.UPDATE) {
            ps.setLong(11, address.getId());
        }

        return ps;
    }
}
