package com.semantyca.yatt.dao;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public abstract class AbstractMapper<T> implements ColumnMapper<T> {

    @Override
    public abstract T map(ResultSet r, int columnNumber, StatementContext ctx) throws SQLException;


    @Override
    public T map(ResultSet r, String columnLabel, StatementContext ctx) throws SQLException{
        return null;
    }


    public static ZonedDateTime getDateTime(Timestamp timestamp) {
        return timestamp != null ? ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp.getTime()), ZoneOffset.UTC) : null;
    }
}
