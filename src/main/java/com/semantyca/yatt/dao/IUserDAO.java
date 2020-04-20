package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.system.User;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface IUserDAO extends IDAO<User> {

    @SqlQuery
    @RegisterColumnMapper(UserMapper.class)
    User findById(@Bind("id") int id);

    @SqlQuery
    @RegisterColumnMapper(UserMapper.class)
    List<User> findAllUnrestricted(@Bind("limit") int limit, @Bind("offset") int offset);

    @SqlQuery
    long getCountOfAll(@Bind("reader") long reader);

    @SqlUpdate
    @GetGeneratedKeys("id")
    int bareInsert(@BindBean User user);

    @SqlUpdate
    @GetGeneratedKeys
    int update(@BindMethods User user);

    @SqlUpdate
    int dropTable();

    @SqlQuery
    @RegisterColumnMapper(UserMapper.class)
    User findByLogin(String login);
}



