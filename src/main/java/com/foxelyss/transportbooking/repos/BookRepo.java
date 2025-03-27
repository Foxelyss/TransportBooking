package com.foxelyss.transportbooking.repos;

import com.foxelyss.transportbooking.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
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
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            return new Book(rs.getInt("id"), rs.getInt("passenger"), rs.getInt("place"), rs.getString("payment"),
                    rs.getInt("price"), rs.getInt("transporting"));
        });
    }

    public int allocatePlace(int transporting) {
        String sql_4 = """
                update Transportation
                set free_place_count = free_place_count-1
                where id = ?
                """;
        return jdbcTemplate.update(sql_4, transporting);
    }

    public int save(int passengerId, int transporting) {
        String sql_1 = """
                insert into Book values (null,?,?,?,?)
                """;

        String sql_2 = """
                select price from Transportation
                where id=?
                """;

        Float price = jdbcTemplate.queryForObject(sql_2, (rs, rowNum) -> {
            return rs.getFloat("price");
        }, transporting);

        if (price == null) {
            return -1;
        }

        return jdbcTemplate.update(sql_1, passengerId, "Картой", price, transporting);
    }

    public int deleteById(String email, long passport, long id) {
        String sql = """
                delete from book
                WHERE book.ROWID IN (
                select book.ROWID from book
                inner join passenger on book.passenger = Passenger.id
                inner join transportation on Transportation.id = book.transportation
                where passport = ? and email = ? and departure > unixepoch()
                ) and book.ROWID = ?;
                """;

        return jdbcTemplate.update(sql, passport, email, id);
    }

    public Number getRecordForBook(String email, long passport, long id) {
        String sql = """
                select Passenger.id from book
                inner join passenger on book.passenger = Passenger.id
                inner join transportation on Transportation.id = book.transportation
                where passport = ? and email = ? and departure > unixepoch() and book.id = ?
                """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            return rs.getInt("id");
        }, passport, email, id);
    }

    public record Ticket(int id, String name, int transporting,
                         Timestamp arrival, String startPoint,
                         String endPoint, float price,
                         String mean, String companyName,
                         String payment
    ) {
    }

    public List<Ticket> findAllByDetails(String email, long passport) {
        String sql = """
                select book.id,
                transportation.id as transporting,
                transportation.name,
                transportation.arrival,
                transportation.price,
                transportation.place_count,
                transportation.free_place_count,
                a1.name AS start_point,
                transportation.departure ,
                a2.name AS end_point,
                company.name as company_name,
                transportingmeans.name as mean,
                book.price,
                book.payment
                from transportation
                inner join company on transportation.company = company.id
                inner join point as a1 on transportation.departure_point  = a1.id
                inner join point as a2 on transportation.arrival_point  = a2.id
                inner join transportingmeans on transportation.transporting_mean = transportingmeans.id
                inner join book on book.transportation =Transportation.id
                inner join passenger on book.passenger = Passenger.id
                where passport = ? and email = ?
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Ticket a = new Ticket(
                    rs.getInt("id")
                    , rs.getString("name")
                    , rs.getInt("transporting")
                    , new Timestamp(rs.getLong("arrival") * 1000)
                    , rs.getString("start_point")
                    , rs.getString("end_point")
                    , rs.getFloat("price")
                    , rs.getString("mean")
                    , rs.getString("company_name")
                    , rs.getString("payment"));
            return a;
        }, passport, email);
    }
}