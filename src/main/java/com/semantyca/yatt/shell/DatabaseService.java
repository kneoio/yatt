package com.semantyca.yatt.shell;


import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.IDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.IDataEntity;
import com.semantyca.yatt.model.exception.RLSIsNotNormalized;
import com.semantyca.yatt.util.EntityGenerator;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;


public class DatabaseService {

    @Lazy
    @Autowired
    ITaskDAO taskDAO;

    @Lazy
    @Autowired
    IUserDAO userDAO;

    @Lazy
    @Autowired
    IAssigneeDAO assigneeDAO;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean init() {
        IDAO daos[] = {userDAO, taskDAO, assigneeDAO};
        for (IDAO dao : daos) {
            try {
                dao.createTable();
            } catch (UnableToExecuteStatementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }


    public boolean purge() {
        IDAO daos[] = {userDAO, taskDAO, assigneeDAO};
        for (IDAO dao : daos) {
            try {
               // dao.dropTable();
            } catch (UnableToExecuteStatementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;

    }

    public boolean populate() {
        try {
            EntityGenerator generator = new EntityGenerator(userDAO, assigneeDAO, passwordEncoder);

            generator.generateUsers().forEach(user -> {
                System.out.println(user.getLogin());
                userDAO.bareInsert(user);
            });

            generator.generateAssignees().forEach(assignee -> {
                System.out.println(assignee.getName());
                assigneeDAO.bareInsert(assignee);
            });

            generator.generateTasks(100).forEach(task -> {
                System.out.println(task.getTitle());
                try {
                    taskDAO.insertSecured(task);
                } catch (RLSIsNotNormalized e) {
                    e.printStackTrace();
                }
            });


        } catch (UnableToExecuteStatementException | IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean info() {
        IDAO daos[] = {userDAO, taskDAO, assigneeDAO};
        for (IDAO dao : daos) {
            System.out.println(dao);
            dao.findAllUnrestricted(999, 0).forEach(u -> System.out.printf(ShellCommands.format, ((IDataEntity)u).getId(),  ((IDataEntity)u).getTitle()));
        }
        return true;
    }
}
