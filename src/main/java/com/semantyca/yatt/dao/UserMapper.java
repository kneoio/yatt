package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.system.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends AbstractMapper<User> {


    @Override
    public User map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        User entity = new User();
        transferCommonData(entity, rs);
        entity.setEmail(rs.getString("email"));
        entity.setLogin(rs.getString("login"));
       return entity;
    }
}
