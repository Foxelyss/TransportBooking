package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Company;
import com.foxelyss.transportbooking.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class PassengerRepo {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertIntoUser;

    @Autowired
    public PassengerRepo(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
        insertIntoUser = new SimpleJdbcInsert(jdbcTemplate).withTableName("passenger").usingGeneratedKeyColumns("id");
    }

    public Number save(Passenger passenger) {
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", null);
        parameters.put("passport", passenger.passport());
        parameters.put("phone", passenger.phone());
        parameters.put("email", passenger.Email());
        parameters.put("surname", passenger.Surname());
        parameters.put("firstname", passenger.name());
        parameters.put("lastname", passenger.middleName());

        return insertIntoUser.executeAndReturnKey(parameters);
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM passenger WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
