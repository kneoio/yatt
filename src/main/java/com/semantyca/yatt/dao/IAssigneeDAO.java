package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.Assignee;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface IAssigneeDAO extends IDAO<Assignee> {

    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    Assignee findById(@Bind("id") int id);

    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    List<Assignee> findAll(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlUpdate
    @GetGeneratedKeys("id")
    int insert(@BindBean Assignee assignee);

    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    Assignee findByLogin(String login);
}



