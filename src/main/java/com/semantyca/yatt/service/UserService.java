package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.IUserDAO;
import com.semantyca.yatt.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    IUserDAO userDAO;


    public long getCountOfAll(long reader) {
        return 0;
    }

    public List<User> findAll(int pageSize, int calcStartEntry, int i) {
        return userDAO.findAll(pageSize, calcStartEntry);
    }
}
