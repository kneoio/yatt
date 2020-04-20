package com.semantyca.yatt.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface IDAO<T> {

    List<T> findAllUnrestricted(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery
    long getCountAll();

    @SqlQuery
    long getCountAll(int reader);

    @SqlUpdate
    @GetGeneratedKeys("id")
    int bareInsert(@BindBean T entity);

    @SqlUpdate
    int bareUpdate(@BindBean T task);

    @SqlUpdate
    int delete(int id);

    @SqlUpdate
    void createTable();

    @Transaction
    @SqlUpdate
    int dropTable();



}
