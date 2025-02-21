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


    public List<Transporting> findByDest(int dep_point, int arr_point) {
        String sequel = """
                select transportation.id,
                transportation.name,
                transportation.arrival,
                a1.region||' '|| a1.city AS start_point,
                transportation.departure ,
                a2.region||' '|| a2.city AS end_point,
                company.name as company_name
                from transportation
                 join company on transportation.company =company.id
                 join point as a1 on transportation.departurepoint  =a1.id
                 join point as a2 on transportation.arrivalpoint  =a2.id
                where a1.id = ? and a2.id = ?
                """;


        return jdbcTemplate.query(sequel, new Object[]{dep_point, arr_point}, (rs, rowNum) -> {
            int id;
            String name;
            Timestamp start;
            Timestamp end;
            String start_point;
            String end_point;
            id = rs.getInt("id");
            name = rs.getString("name");
            start = rs.getTimestamp("departure");
            end = rs.getTimestamp("arrival");
            start_point = rs.getString("start_point");
            end_point = rs.getString("end_point");
            return new Transporting(id, name, start, end, start_point, end_point, 1, 1);
        });
    }
}
