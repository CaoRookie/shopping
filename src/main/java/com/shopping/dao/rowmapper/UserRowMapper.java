package com.shopping.dao.rowmapper;

import com.shopping.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUser_id(resultSet.getInt("user_id"));
        user.setUser_name(resultSet.getString("user_name"));
        user.setUser_email(resultSet.getString("user_email"));
        user.setUser_phone(resultSet.getString("user_phone"));
        user.setUser_password(resultSet.getString("user_password"));
        user.setUser_access(resultSet.getInt("user_access"));
        return user;
    }
}
