package com.semantyca.yatt.dao;

import com.semantyca.yatt.configuration.ApplicationContextKeeper;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.User;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssigneeMapper extends AbstractMapper<Assignee> {

    private IUserDAO userDAO;

    public AssigneeMapper() {
        super();
        userDAO = ApplicationContextKeeper.getContext().getBean(IUserDAO.class);
    }

    @Override
    public Assignee map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Assignee entity = new Assignee();
        transferCommonData(entity, rs);
        entity.setName(rs.getString("name"));
        entity.setRank(rs.getInt("rank"));
        User user = userDAO.findById(rs.getInt("user_id"));
        if (user != null) {
            entity.setUser(user);
        }
        return entity;
    }

}
