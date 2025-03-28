package com.foxelyss.transportbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class TransportbookingApplication {
//    @Autowired
//    static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
//        String listString;
//
//        try {
//            Path filePath = Paths.get(TransportbookingApplication.class.getResource("schema.sql").toURI());
//            List<String> lines = Files.readAllLines(filePath);
//            listString = String.join(" ", lines);
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            int a = jdbcTemplate.queryForObject("select count(*) as columns from sqlite_master", (rs, rowNum) -> {
//                return rs.getInt("columns");
//            });
//            if (a != 6) {
//				ScriptUtils.executeSqlScript(jdbcTemplate.,TransportbookingApplication.class.getResource("schema.sql"));
//            }
//        } catch (Exception e) {
//
//        }
        SpringApplication.run(TransportbookingApplication.class, args);
    }

}
