package com.semantyca.yatt.configuration;

import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.dao.system.IRLSEntryDAO;
import com.semantyca.yatt.model.IUser;
import com.semantyca.yatt.security.SessionUser;
import com.semantyca.yatt.shell.DatabaseService;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@DependsOn("dataEnginService")
public class DbConfiguration {

    @Bean
    @Autowired
    public IUserDAO userDao(Jdbi jdbi) {
        return jdbi.onDemand(IUserDAO.class);
    }


    @Bean
    @Autowired
    Map<Integer, IUser> allUsers (IUserDAO userDAO) {
        Map<Integer, IUser> users = new HashMap<>();
        for (IUser user : userDAO.findAllUnrestricted(999, 0)) {
            users.put(user.getId(), user);
        }
        return users;
    }

    @Bean
    @Autowired
    Map<String, SessionUser> allUsersMap(IUserDAO userDAO) {
        Map<String, SessionUser> users = new HashMap<>();
        for (IUser user : userDAO.findAllUnrestricted(999, 0)) {
            users.put(user.getLogin(), new SessionUser(user.getId(), user.getLogin()));
        }
        return users;
    }

    @Bean
    @Autowired
    public IRLSEntryDAO getRlsEntryDao(Jdbi jdbi) {
        return jdbi.onDemand(IRLSEntryDAO.class);
    }

    @Bean
    @Autowired
    public ITaskDAO taskDao(Jdbi jdbi) {
        return jdbi.onDemand(ITaskDAO.class);
    }

    @Bean
    @Autowired
    public IAssigneeDAO assigneeDAO(Jdbi jdbi) {
        return jdbi.onDemand(IAssigneeDAO.class);
    }

    @Bean
    public DatabaseService dbService( ITaskDAO taskDao){
        return new DatabaseService();
    }

}
