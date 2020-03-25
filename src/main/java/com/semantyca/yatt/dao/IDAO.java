package com.semantyca.yatt.dao;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IDAO<T> {


    @SqlUpdate
    @GetGeneratedKeys("id")
    int insert(@BindBean T entity);

    @SqlUpdate
    void createTable();

    @SqlUpdate
    int dropTable();


}
