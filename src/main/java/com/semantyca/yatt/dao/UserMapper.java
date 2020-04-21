package com.semantyca.yatt.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.semantyca.yatt.model.system.User;
import org.jdbi.v3.core.statement.StatementContext;
import org.postgresql.util.PGobject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserMapper extends AbstractMapper<User> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public User map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        User entity = new User();
        transferIdInteger(entity, rs);
        transferCommonData(entity, rs);
        entity.setEmail(rs.getString("email"));
        entity.setLogin(rs.getString("login"));
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            ArrayType arrayType = typeFactory.constructArrayType(String.class);
            PGobject po = rs.getObject("loc_name", PGobject.class);
            entity.setRoles(mapper.readValue(po.getValue(), arrayType));
        } catch (Exception e) {
            entity.setRoles(new ArrayList<>());
        }
       return entity;
    }
}
