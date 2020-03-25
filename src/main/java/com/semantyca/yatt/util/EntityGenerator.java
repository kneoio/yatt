package com.semantyca.yatt.util;

import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.AnonymousUser;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.User;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {
    private static final String FIRST_NAME_SOURCE = "Roman.txt";
    private static final String LAST_NAME_SOURCE = "Simple.txt";
    private IUserDAO userDAO;
    private IAssigneeDAO assigneeDAO;

    public EntityGenerator(IUserDAO userDAO, IAssigneeDAO assigneeDAO) {
        this.userDAO = userDAO;
        this.assigneeDAO = assigneeDAO;
    }

    public List<User> generateUsers() {
        List users = new ArrayList();
        String[] data = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"};
        for (int i = 0; i < data.length; i++) {
            User entity = userDAO.findByLogin(data[i]);
            if (entity == null) {
                entity = new User();
                ZonedDateTime currentMoment = ZonedDateTime.now();
                entity.setRegDate(currentMoment);
                entity.setLogin(data[i]);
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                entity.setTitle(entity.getLogin());
                users.add(entity);
            }
        }
        return users;
    }

    public List<Assignee> generateAssignees() {
        List entities = new ArrayList();
        String[] data = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"};
        for (int i = 0; i < data.length; i++) {
            Assignee entity = assigneeDAO.findByLogin(data[i]);
            if (entity == null) {
                entity = new Assignee();
                ZonedDateTime currentMoment = ZonedDateTime.now();
                entity.setRegDate(currentMoment);
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                try {
                    entity.setName(new NameGenerator(LAST_NAME_SOURCE).compose(3) + " " + new NameGenerator(FIRST_NAME_SOURCE).compose(3));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                entities.add(entity);
            }
        }
        return entities;
    }

}
