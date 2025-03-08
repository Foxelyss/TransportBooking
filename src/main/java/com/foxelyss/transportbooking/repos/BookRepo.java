package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Book;
import com.foxelyss.transportbooking.model.Passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> findAll() {
        String sql = "SELECT * FROM point";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new Book(rs.getInt("id"), rs.getInt("passenger"), rs.getInt("place"), rs.getString("payment"),
                    rs.getInt("price"), rs.getInt("transporting"));
        });
    }

    public Book findById(Long id) {
        String sql = "SELECT * FROM point WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> {
            return new Book(rs.getInt("id"), rs.getInt("passenger"), rs.getInt("place"), rs.getString("payment"),
                    rs.getInt("price"), rs.getInt("transporting"));
        });
    }

    public int save(Passenger passenger, Book item) {
        String sql_1 = """
                insert OR REPLACE into Passenger values (?,?,?,?,?,?)
                """;

        String sql_2 = """
                insert into Book values (null,?,?,?,?)
                """;

        String sql_3 = """
                    select price from Transportation
                    where id=?
                """;

        String sql_4 = """
                update Transportation
                set free_place_count = free_place_count+1
                where id = ?
                                """;

        float price = jdbcTemplate.queryForObject(sql_3, new Object[] { item.transporting() }, (rs, rowNum) -> {
            return rs.getFloat("price");
        });

        int rows = jdbcTemplate.update(sql_1,
                passenger.passport(), passenger.phone(),
                passenger.Email(), passenger.Surname(),
                passenger.name(), passenger.middleName());

        rows += jdbcTemplate.update(sql_2, passenger.passport(), "Картой", price, item.transporting());
        rows += jdbcTemplate.update(sql_4, item.transporting());

        return rows;
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM point WHERE id = ?";
        return jdbcTemplate.update(sql, id);

    }

    public Book findByName(String name) {
        String sql = "select * from point where point.city like ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { name }, (rs, rowNum) -> {
            return new Book(rs.getInt("id"), rs.getInt("passenger"), rs.getInt("place"), rs.getString("payment"),
                    rs.getInt("price"), rs.getInt("transporting"));
        });
    }

    public List<Book> findManyByName(String name) {
        String sql = "select * from point where point.city like ?";
        return jdbcTemplate.query(sql, new Object[] { "%" + name + "%" }, (rs, rowNum) -> {
            return new Book(rs.getInt("id"), rs.getInt("passenger"), rs.getInt("place"), rs.getString("payment"),
                    rs.getInt("price"), rs.getInt("transporting"));
        });
    }
}