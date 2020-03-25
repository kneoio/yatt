package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.Task;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper  extends AbstractMapper<Task> {

    @Override
    public Task map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setLastModifiedDate(getDateTime(rs.getTimestamp("last_mod_date")));
        task.setLastModifier(rs.getLong("last_mod_user"));
        task.setRegDate(getDateTime(rs.getTimestamp("reg_date")));
        task.setTitle(rs.getString("title"));
        task.setAuthor(rs.getLong("author"));
        task.setEditable(rs.getInt("is_editable"));
       return task;
    }



}
