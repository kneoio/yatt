package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.Task;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface ITaskDAO extends IDAO<Task> {
    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    Task findById(@Bind("id") int id, @Bind("reader") int reader);

    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    List<Task> findAll(@Bind("limit") int limit, @Bind("offset") int offset, @Bind("reader") long reader);

    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    List<Task> findAll(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlUpdate
    @GetGeneratedKeys("id")
    int insert(@BindBean Task task);

    @SqlUpdate
    @GetGeneratedKeys
    int update(@BindBean Task task);

}



