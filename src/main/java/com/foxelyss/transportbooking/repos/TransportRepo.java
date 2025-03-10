package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Transporting;
import com.foxelyss.transportbooking.model.TransportingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransportRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TransportingResult> findAll() {
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            // Item item = new Item();
            // item.setId(rs.getLong("id"));
            // item.setName(rs.getString("name"));
            return null;
        });
    }

    public TransportingResult findById(Long id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            // Transporting item = new Transporting();
            // item.setId(rs.getLong("id"));
            // item.setName(rs.getString("name"));
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

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time) {
        String sequel = """
                select transportation.id,
                transportation.name,
                transportation.arrival,
                transportation.place_count,
                transportation.free_place_count,
                a1.region||' '|| a1.city AS start_point,
                transportation.departure ,
                a2.region||' '|| a2.city AS end_point,
                company.name as company_name,
                transportingmeans.name as mean
                from transportation
                inner join company on transportation.company = company.id
                inner join point as a1 on transportation.departure_point  = a1.id
                inner join point as a2 on transportation.arrival_point  = a2.id
                inner join transportingmeans on transportation.transporting_mean = transportingmeans.id
                where a1.id = ? and a2.id = ? and departure > unixepoch()
                ORDER BY ABS(arrival - ?)
                limit ?
                """;
        if (1 < quantity || quantity > 15) {
            quantity = 15;
        }

        return jdbcTemplate.query(sequel, new Object[] { dep_point, arr_point, quantity, wanted_time },
                (rs, rowNum) -> {
                    int id;
                    String name;
                    Timestamp start;
                    Timestamp end;
                    String start_point;
                    String end_point;
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    start = new Timestamp(rs.getLong("departure") * 1000);
                    end = new Timestamp(rs.getLong("arrival") * 1000);
                    start_point = rs.getString("start_point");
                    end_point = rs.getString("end_point");

                    return new TransportingResult(id, name, start, end, start_point, end_point, 1, 1, 12,
                            rs.getString("mean"),
                            rs.getString("company_name"), rs.getInt("place_count"), rs.getInt("free_place_count"));
                });
    }

    String sql = """
            select transportation.id,
            transportation.name,
            datetime(transportation.arrival,  'auto') ,
            transportation.place_count,
            transportation.free_place_count,
            a1.region||' '|| a1.city AS start_point,
            transportation.departure ,
            a2.region||' '|| a2.city AS end_point,
            company.name as company_name,
            transportingmeans.name as mean
            from transportation
            inner join company on transportation.company =company.id
            inner join point as a1 on transportation.departure_point  =a1.id
            inner join point as a2 on transportation.arrival_point  =a2.id
            inner join transportingmeans on transportation.transporting_mean=transportingmeans.id
            where a1.id = 2 and a2.id = 1 and  departure>unixepoch()
            ORDER BY ABS( arrival - 1745375150)
            """;
}
