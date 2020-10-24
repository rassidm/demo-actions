package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;
    private final UserService userService;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate, UserService userService) {
        this.jdbcTemplate = jdbcTemplate;
        this.userService = userService;
    }

    public void fill() {
        clearDatabase();
        User user = new User(1L, "autentia");
        userService.createRoom(user);
    }

    private void clearDatabase() {
        Stream<String> tablesScriptV1 = Stream.of("users");
        tablesScriptV1.forEach(table -> jdbcTemplate.execute("delete from " + table));
    }
}