package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Mean;
import com.foxelyss.transportbooking.model.Transporting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransportRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Mean> findAllTransportingMeans() {
        String sql = "SELECT * FROM transportingmeans";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Mean(rs.getInt("id"), rs.getString("name"));
        });
    }

    public TransportingResult findById(Long id) {
        String sql = """
                select transportation.id,
                transportation.name,
                transportation.arrival,
                transportation.price,
                transportation.place_count,
                transportation.free_place_count,
                transportation.arrival_point,
                transportation.departure_point,
                a1.region||'|'|| a1.city AS start_point,
                transportation.departure ,
                a2.region||'|'|| a2.city AS end_point,
                company.name as company_name,
                transportingmeans.name as mean
                from transportation
                inner join company on transportation.company = company.id
                inner join point as a1 on transportation.departure_point  = a1.id
                inner join point as a2 on transportation.arrival_point  = a2.id
                inner join transportingmeans on transportation.transporting_mean = transportingmeans.id
                where transportation.id = ?
                """;
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new TransportingResult(rs.getInt("id"), rs.getString("name"),
                    new Timestamp(rs.getLong("departure") * 1000), new Timestamp(rs.getLong("arrival") * 1000),
                    rs.getString("start_point"), rs.getString("end_point"), rs.getInt("arrival_point"), rs.getInt("departure_point"),
                    rs.getFloat("price"), rs.getString("mean"),
                    rs.getString("company_name"), rs.getInt("place_count"), rs.getInt("free_place_count"));
        }, id);
    }

    public int save(Transporting item) {
        String sql = "INSERT INTO items (name) VALUES (?)";
        return jdbcTemplate.update(sql, item.name());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM items WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time, int page) {
        String sequel = """
                select transportation.id,
                transportation.name,
                transportation.arrival,
                transportation.price,
                transportation.place_count,
                transportation.free_place_count,
                a1.region||'|'|| a1.city AS start_point,
                transportation.departure ,
                a2.region||'|'|| a2.city AS end_point,
                company.name as company_name,
                transportingmeans.name as mean
                from transportation
                inner join company on transportation.company = company.id
                inner join point as a1 on transportation.departure_point  = a1.id
                inner join point as a2 on transportation.arrival_point  = a2.id
                inner join transportingmeans on transportation.transporting_mean = transportingmeans.id
                where a1.id = ? and a2.id = ? and departure > unixepoch()
                ORDER BY ABS(departure - ?)
                limit ? offset ?
                """;
        if (quantity < 1 || quantity > 15) {
            quantity = 15;
        }

        return jdbcTemplate.query(sequel,
                (rs, rowNum) -> {
                    return new TransportingResult(rs.getInt("id"), rs.getString("name"),
                            new Timestamp(rs.getLong("departure") * 1000), new Timestamp(rs.getLong("arrival") * 1000),
                            rs.getString("start_point"), rs.getString("end_point"), arr_point, dep_point,
                            rs.getFloat("price"), rs.getString("mean"),
                            rs.getString("company_name"), rs.getInt("place_count"), rs.getInt("free_place_count"));
                }, dep_point, arr_point, wanted_time, quantity, page);
    }

    public List<TransportingResult> findByDest(int dep_point, int arr_point, int quantity, long wanted_time, int mean, int page) {
        String sequel = """
                select transportation.id,
                transportation.name,
                transportation.arrival,
                transportation.price,
                transportation.place_count,
                transportation.free_place_count,
                a1.region||'|'|| a1.city AS start_point,
                transportation.departure ,
                a2.region||'|'|| a2.city AS end_point,
                company.name as company_name,
                transportingmeans.name as mean
                from transportation
                inner join company on transportation.company = company.id
                inner join point as a1 on transportation.departure_point  = a1.id
                inner join point as a2 on transportation.arrival_point  = a2.id
                inner join transportingmeans on transportation.transporting_mean = transportingmeans.id
                where a1.id = ? and a2.id = ? and departure > unixepoch() and transporting_mean = ?
                ORDER BY ABS(departure - ?)
                limit ? offset ?
                """;
        if (quantity < 1 || quantity > 15) {
            quantity = 15;
        }

        return jdbcTemplate.query(sequel,
                (rs, rowNum) -> {
                    return new TransportingResult(rs.getInt("id"), rs.getString("name"),
                            new Timestamp(rs.getLong("departure") * 1000), new Timestamp(rs.getLong("arrival") * 1000),
                            rs.getString("start_point"), rs.getString("end_point"), arr_point, dep_point,
                            rs.getFloat("price"), rs.getString("mean"),
                            rs.getString("company_name"), rs.getInt("place_count"), rs.getInt("free_place_count"));
                }, dep_point, arr_point, mean, wanted_time, quantity, page);
    }
}
