package com.semantyca.yatt.dao.system;

import com.semantyca.yatt.dao.AbstractMapper;
import com.semantyca.yatt.model.embedded.RLSEntry;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RLSMapper extends AbstractMapper<RLSEntry> {


    @Override
    public RLSEntry map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        RLSEntry entity = new RLSEntry();
        entity.setAccessLevel(rs.getInt("is_edit_allowed"));
        entity.setReader(rs.getInt("reader"));
        entity.setReadingTime(getDateTime(rs.getTimestamp("reading_time")));
       return entity;
    }




}
