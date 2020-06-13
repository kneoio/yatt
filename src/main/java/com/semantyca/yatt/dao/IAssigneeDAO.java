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
import java.util.UUID;

@UseClasspathSqlLocator
@RepositoryDAO("assignees")
public interface IAssigneeDAO extends IDAO<Assignee, UUID> {

    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    Assignee findById(@Bind("id") UUID id);

    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    List<Assignee> findAllUnrestricted(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlUpdate
    @GetGeneratedKeys("id")
    UUID bareInsert(@BindBean Assignee assignee);


    @SqlQuery
    @RegisterColumnMapper(AssigneeMapper.class)
    Assignee findByLogin(String login);
}



