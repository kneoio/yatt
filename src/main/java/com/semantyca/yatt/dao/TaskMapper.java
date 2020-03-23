package com.semantyca.yatt.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.semantyca.yatt.model.Task;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

public class TaskMapper implements ColumnMapper<Task> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Task map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        mapper.registerModule(new JavaTimeModule());
        Task task = new Task();
        task.setId(rs.getObject("id", UUID.class));
        task.setLastModifiedDate(getDateTime(rs.getTimestamp("last_mod_date")));
        task.setLastModifier(rs.getLong("last_mod_user"));
        task.setRegDate(getDateTime(rs.getTimestamp("reg_date")));
        task.setTitle(rs.getString("title"));
        task.setAuthor(rs.getLong("author"));
        task.setEditable(rs.getInt("is_editable"));
       return task;
    }


    private static ZonedDateTime getDateTime(Timestamp timestamp) {
        return timestamp != null ? ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.getTime()), ZoneOffset.UTC) : null;
    }

}
