package com.semantyca.yatt.util;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.constant.PriorityType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;
import com.semantyca.yatt.model.embedded.RLSEntry;
import com.semantyca.yatt.model.system.AnonymousUser;
import com.semantyca.yatt.model.system.Role;
import com.semantyca.yatt.model.system.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class EntityGenerator {
    private static final String FIRST_NAME_SOURCE = "Roman.txt";
    private static final String LAST_NAME_SOURCE = "Simple.txt";
    private IUserDAO userDAO;
    private IAssigneeDAO assigneeDAO;

    private PasswordEncoder passwordEncoder;



    public EntityGenerator(IUserDAO userDAO, IAssigneeDAO assigneeDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.assigneeDAO = assigneeDAO;
        this.passwordEncoder = passwordEncoder;
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
                entity.setPwd(passwordEncoder.encode(EnvConst.INITIAL_PWD));
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                entity.setTitle(entity.getLogin());
                Role role = new Role();
                role.setName("fake");
                ArrayList roles = new ArrayList();
                roles.add(role.getName());
                roles.add(new Role().setName("super").getName());
                entity.setRoles(roles);
                users.add(entity);
            }
        }
        return users;
    }

    public List<Assignee> generateAssignees() throws IOException {
        NameGenerator nameGeneratorLastName = new NameGenerator(EntityGenerator.class.getClassLoader().getResource(LAST_NAME_SOURCE).getFile());
        NameGenerator nameGeneratorFirstName = new NameGenerator(EntityGenerator.class.getClassLoader().getResource(FIRST_NAME_SOURCE).getFile());
        List entities = new ArrayList();
        List<User> users = userDAO.findAllUnrestricted(100, 0);
        for (User user : users) {
            Assignee entity = assigneeDAO.findByLogin(user.getLogin());
            if (entity == null) {
                entity = new Assignee();
                ZonedDateTime currentMoment = ZonedDateTime.now();
                entity.setRegDate(currentMoment);
                entity.setLastModifiedDate(currentMoment);
                entity.setAuthor(AnonymousUser.ID);
                entity.setLastModifier(AnonymousUser.ID);
                entity.setRank(NumberUtil.getRandomNumber(1, 10));
                entity.setName(nameGeneratorLastName.compose(3) + " " + nameGeneratorFirstName.compose(3));
                entity.setTitle(entity.getName());
                entity.setUser(user);
                entities.add(entity);
            }
        }
        return entities;
    }

    public List<Task> generateTasks(int count) throws IOException {
        List entities = new ArrayList();
        List<User> users = userDAO.findAllUnrestricted(100, 0);
        Integer[] userIds =  users.stream().map((user) -> user.getId()).toArray(Integer[]::new);
        List<Assignee> assignees = assigneeDAO.findAllUnrestricted(100, 0);
        for (int i = 0; i < count; i++) {
            Task entity = new Task();
            ZonedDateTime currentMoment = ZonedDateTime.now();
            entity.setRegDate(currentMoment);
            entity.setLastModifiedDate(currentMoment);
            entity.setAuthor(ListUtil.getRndArrayElement(userIds));
            entity.setLastModifier(ListUtil.getRndArrayElement(userIds));
            entity.setPriority(EnumUtil.getRndElement(PriorityType.values()));
            entity.setStatus(EnumUtil.getRndElement(StatusType.values()));
            entity.setType(EnumUtil.getRndElement(TaskType.values()));
            entity.setAssignee((Assignee) ListUtil.getRndListElement(assignees));
            entity.setTitle(StringUtil.getRndArticle(10));
            entity.setDescription(StringUtil.getRndParagraph(1));
            entity.setDeadline(TimeUtil.getRndDateBetween(LocalDateTime.now(), LocalDateTime.now().plusDays(30)));
            for (int j = 0; j < NumberUtil.getRandomNumber(0,5) ; j++) {
                entity.addReader(new RLSEntry().setReader(ListUtil.getRndArrayElement(userIds))
                        .setAccessLevel(NumberUtil.getRandomNumber(0,2))
                        .setReadRightNow(BoolUtil.getRandomBoolean()));
            }
            entities.add(entity);

        }
        return entities;
    }


}
