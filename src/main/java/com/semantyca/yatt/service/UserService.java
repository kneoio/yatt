package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.system.User;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class UserService {
    IUserDAO userDAO;

    public long getCountOfAll(long reader) {
        return 0;
    }

    public User findById(int id) {
        return userDAO.findById(id);
    }

    public List<User> findAll(int pageSize, int calcStartEntry, int i) {
        return userDAO.findAllUnrestricted(pageSize, calcStartEntry);
    }

    public long post(User user, int userId) {
        if (user.getId() == null) {
            user.setRegDate(ZonedDateTime.now());
            user.setAuthor(userId);
            user.setLastModifiedDate(user.getRegDate());
            user.setLastModifier(userId);
            return userDAO.bareInsert(user);
        } else {
            return put(user, userId);
        }
    }

    public int put(User user, int userId) {
        user.setLastModifiedDate(ZonedDateTime.now());
        user.setLastModifier(userId);
        return userDAO.bareUpdate(user);
    }

    public int delete(User user, int reader) {
        return userDAO.delete(user.getId());
    }


}
