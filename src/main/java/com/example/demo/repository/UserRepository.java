package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper(User.class));

    }

    public void createUser(User user) {
        jdbcTemplate.update(
                "INSERT INTO users VALUES (?, ?)",
                user.getId(), user.getName()
        );
    }
}
