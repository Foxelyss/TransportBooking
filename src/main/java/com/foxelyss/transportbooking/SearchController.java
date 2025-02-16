package com.foxelyss.transportbooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class SearchController {

    private void makeRequest(String sql){
        // NOTE: Connection and Statement are AutoCloseable.
        //       Don't forget to close them both in order to avoid leaks.
        try
                (
                        // create a database connection
                        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
                        Statement statement = connection.createStatement();
                )
        {
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }
    }
    @GetMapping("/points")
    public ArrayList<Point> hello(@RequestParam(value = "name", defaultValue = "Томск") String name) {
        String sequel = """
                select * from point
                where point.city like "%?%"
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
            preparedStatement.setString(1, name);
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
        Point points[];

        points = new Point[5];
        points[0] =new Point(1,"Томск","123","1231");
        points[1] =new Point(1,"Москва","123","1231");
        points[2] =new Point(1,"Омск","123","1231");
        points[3] =new Point(1,"Новосибирск","123","1231");
        points[4] =new Point(1,"Зеленоград","123","1231");

        return  m;
    }
    @GetMapping("/search")
    public ArrayList<Transporting> m(@RequestParam(value = "point_a") int point_a,@RequestParam(value = "point_b") int point_b,@RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        ArrayList<Transporting> m = new ArrayList<>();
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
            while(rs.next())
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
m.add(new Transporting(id,name,start,end,start_point,end_point));
                quan++;
            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            e.printStackTrace(System.err);
        }



//        m = new Transporting[quantity];
//        m[0] =  new Transporting(0, "M-315",
//                new Timestamp(112,1,2,5,2,3,0),
//                new Timestamp(112,5,2,12,2,3,0),"Томск-1","");

//        return m;
        return  m;
    }

    @GetMapping("/companies")
    public String p(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/company")
    public Company pa(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Company(1,"123","123","123");
    }
}
