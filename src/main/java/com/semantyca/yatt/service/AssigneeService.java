package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.model.Assignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssigneeService {

    @Autowired
    IAssigneeDAO assigneeDAO;

    public long getCountOfAll(long reader) {
        return assigneeDAO.getCountAll();
    }

    public List<Assignee> findAll(int pageSize, int calcStartEntry, int i) {
        return assigneeDAO.findAll(pageSize, calcStartEntry);
    }

    public Assignee findById(int id, int userId) {
         return assigneeDAO.findById(id);
    }

}
