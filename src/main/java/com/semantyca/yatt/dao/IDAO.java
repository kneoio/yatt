package com.semantyca.yatt.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface IDAO<T> {

    List<T> findAll(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery
    long getCountAll();

    @SqlQuery
    long getCountAll(int reader);

    @SqlUpdate
    @GetGeneratedKeys("id")
    int insert(@BindBean T entity);

    @SqlUpdate
    void createTable();

    @SqlUpdate
    int dropTable();



}
