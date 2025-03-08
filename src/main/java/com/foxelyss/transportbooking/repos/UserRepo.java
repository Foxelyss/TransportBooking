package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transporting> findAll() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Item item = new Item();
//            item.setId(rs.getLong("id"));
//            item.setName(rs.getString("name"));
            return null;
        });
    }

    public Optional<User> findByUsername(String username) {
        return null;
    }


    public Transporting findById(Long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
//            Transporting item = new Transporting();
//            item.setId(rs.getLong("id"));
//            item.setName(rs.getString("name"));
            return null;
        });
    }

    public int save(Transporting item) {
        String sql = "INSERT INTO items (name) VALUES (?)";
        return jdbcTemplate.update(sql, item.name());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        return jdbcTemplate.update(sql, id);


    }


}
