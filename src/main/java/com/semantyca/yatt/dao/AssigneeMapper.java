package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.User;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssigneeMapper extends AbstractMapper<Assignee> {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public Assignee map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Assignee entity = new Assignee();
        entity.setId(rs.getInt("id"));
        entity.setLastModifiedDate(getDateTime(rs.getTimestamp("last_mod_date")));
        entity.setLastModifier(rs.getLong("last_mod_user"));
        entity.setRegDate(getDateTime(rs.getTimestamp("reg_date")));
        entity.setTitle(rs.getString("title"));
        entity.setAuthor(rs.getLong("author"));
        entity.setName(rs.getString("name"));
        entity.setRank(rs.getInt("rank"));
        User user = userDAO.findById(rs.getInt("user_id"));
        if (user != null) {
            entity.setUser(user);
        }
        return entity;
    }
}
