package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Company> findAll() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Company(rs.getInt("id"), rs.getString("name"), rs.getString("registration_address"), rs.getString("inn"));
        });
    }

    public Company findById(Long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Company(rs.getInt("id"), rs.getString("name"), rs.getString("registration_address"), rs.getString("inn"));
        }, id);
    }

    public int save(Company item) {
        String sql = "INSERT INTO items (name) VALUES (?)";
        return jdbcTemplate.update(sql, item.name());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
