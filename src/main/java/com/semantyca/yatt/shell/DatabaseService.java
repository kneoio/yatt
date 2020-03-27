package com.semantyca.yatt.shell;


import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.IDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.IAppEntity;
import com.semantyca.yatt.util.EntityGenerator;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class DatabaseService {

    @Autowired
    ITaskDAO taskDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IAssigneeDAO assigneeDAO;

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
                dao.dropTable();
            } catch (UnableToExecuteStatementException e) {
                System.out.println(e.getMessage());
            }
        }
        return true;

    }

    public boolean populate() {
        try {
            EntityGenerator generator = new EntityGenerator(userDAO, assigneeDAO);

            generator.generateUsers().forEach(user -> {
                System.out.println(user.getLogin());
                userDAO.insert(user);
            });

            generator.generateAssignees().forEach(assignee -> {
                System.out.println(assignee.getName());
                assigneeDAO.insert(assignee);
            });

            generator.generateTasks(100).forEach(task -> {
                System.out.println(task.getTitle());
                taskDAO.insert(task);
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
            dao.findAll(999, 0).forEach(u -> System.out.printf(ShellCommands.format, ((IAppEntity)u).getId(),  ((IAppEntity)u).getTitle()));
        }
        return true;
    }
}
