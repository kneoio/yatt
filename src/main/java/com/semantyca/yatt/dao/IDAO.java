package com.semantyca.yatt.dao;

import org.apache.commons.lang3.ArrayUtils;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public interface IDAO<T,I> {


    default String[] getInvolvedTables(){
        return ArrayUtils.EMPTY_STRING_ARRAY;
    }


    List<T> findAllUnrestricted(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery
    long getCountAll();

    @SqlQuery
    long getCountAll(@Bind("reader") long reader);

    @SqlUpdate
    @GetGeneratedKeys("id")
    I bareInsert(@BindBean T entity);

    @SqlUpdate
    @GetGeneratedKeys("id")
    I bareUpdate(@BindBean T entity);

    @SqlUpdate
    int delete(I id);

    @SqlUpdate
    void createTable();

    @Transaction
    @SqlUpdate
    int dropTable();





}
