package com.springboot.hello01.dao;

import com.springboot.hello01.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserDao {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(User user) {
        return this.jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }

    public User findById(String id) {
        String sql = "select * from users where id = ?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"),
                        rs.getString(("name")), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
