package com.foxelyss.transportbooking;

import com.foxelyss.transportbooking.model.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/register_company")
    public String searchForPoint(@RequestParam(value = "point", defaultValue = "Томск") String point) {
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


        return "";
    }

    @GetMapping("/register")
    public String p(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/validate_token")
    public String p3(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/login")
    public String p23(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
;