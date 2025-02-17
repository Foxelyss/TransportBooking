package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Point;
import com.foxelyss.transportbooking.model.Transporting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @GetMapping("/points")
    public ArrayList<Point> searchForPoint(@RequestParam(value = "point", defaultValue = "Томск") String point) {
        String sequel = """
                select * from point
                where point.city like ?;
                """;
        ArrayList<Point> m  =  new ArrayList<>();
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            PreparedStatement preparedStatement = connection.prepareStatement(sequel);
            preparedStatement.setString(1, "%"+point+"%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                int id;String name_a;String region;String town;
                id = resultSet.getInt("id");
                town = resultSet.getString("city");
                region = resultSet.getString("city");
                m.add(new Point(id,"asd",region,town));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }


        return  m;
    }
    @GetMapping("/search")
    public ArrayList<Transporting> searchForTransport(@RequestParam(value = "point_a") int point_a, @RequestParam(value = "point_b") int point_b,
                                                      @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        ArrayList<Transporting> transportings = new ArrayList<>();
        int quan = 0;

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
                """;
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery(sequel);
            while(rs.next() && quan < quantity )
            {
                int id; String name;
                Timestamp start;
                Timestamp end; String start_point;
                String end_point;
id =rs.getInt("id");
name = rs.getString("name");
                start = rs.getTimestamp("departure");
                end = rs.getTimestamp("arrival");
                start_point= rs.getString("start_point");
                end_point= rs.getString("end_point");
transportings.add(new Transporting(id,name,start,end,start_point,end_point));
                quan++;
            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }

        return  transportings;
    }

    @GetMapping("/companies")
    public String p(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}

;