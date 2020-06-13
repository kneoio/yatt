package com.semantyca.yatt.configuration;

import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;


public interface DataEngineService {

    @SqlQuery("SELECT table_name FROM information_schema.tables")
    List<String> findAllTables();

    @SqlQuery("SELECT extname FROM pg_extension WHERE extname = :name")
    String getPlugin(String name);


}
