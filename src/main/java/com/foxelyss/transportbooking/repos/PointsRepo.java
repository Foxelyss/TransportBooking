package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointsRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Point> findAll() {
        String sql = "SELECT * FROM point";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Point(rs.getInt("id"), rs.getString("name"), rs.getString("region"), rs.getString("city"));
        });
    }

    public Point findById(Long id) {
        String sql = "SELECT * FROM point WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Point(rs.getInt("id"), rs.getString("name"), rs.getString("region"), rs.getString("city"));
        }, id);
    }

    public int save(Point item) {
        String sql = "INSERT INTO point VALUES (null,?,?,?)";
        return jdbcTemplate.update(sql, item.name(), item.region(), item.town());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM point WHERE id = ?";
        return jdbcTemplate.update(sql, id);

    }

    public Point findByName(String name) {
        String sql = "select * from point where point.city like ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return new Point(rs.getInt("id"), rs.getString("name"), rs.getString("region"), rs.getString("city"));
        }, name);
    }

    public List<Point> findManyByName(String name) {
        String sql = "select * from point where point.city like ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Point(rs.getInt("id"), rs.getString("name"), rs.getString("region"), rs.getString("city"));
        }, "%" + name + "%");
    }
}
