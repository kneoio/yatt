package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper<User> {


    @Override
    public User map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        User entity = new User();
        entity.setId(rs.getInt("id"));
        entity.setLastModifiedDate(getDateTime(rs.getTimestamp("last_mod_date")));
        entity.setLastModifier(rs.getLong("last_mod_user"));
        entity.setRegDate(getDateTime(rs.getTimestamp("reg_date")));
        entity.setTitle(rs.getString("title"));
        entity.setAuthor(rs.getLong("author"));
        entity.setEmail(rs.getString("email"));
        entity.setLogin(rs.getString("login"));
       return entity;
    }
}
