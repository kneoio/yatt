package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.model.Assignee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssigneeService {

    @Autowired
    IAssigneeDAO assigneeDAO;

    public long getCountOfAll(long reader) {
        return assigneeDAO.getCountAll();
    }

    public List<Assignee> findAll(int pageSize, int calcStartEntry, int i) {
        return assigneeDAO.findAllUnrestricted(pageSize, calcStartEntry);
    }

    public Assignee findById(UUID id, int userId) {
         return assigneeDAO.findById(id);
    }

}
