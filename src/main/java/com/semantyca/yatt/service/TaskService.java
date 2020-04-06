package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    ITaskDAO taskDAO;

    public long getCountOfAll(long reader) {
        return taskDAO.getCountAll();
    }

    public List<Task> findAll(int pageSize, int calcStartEntry, int i) {
        return taskDAO.findAll(pageSize, calcStartEntry);
    }


    public Task findById(int id, int userId) {
         return taskDAO.findById(id, userId);
    }

    public long post(Task task, int userId) {
        return taskDAO.insert(task);
    }

    public int put(Task task, int reader) {
        return taskDAO.update(task);
    }

    public int delete(Task task, int reader) {
        return taskDAO.delete(task.getId());
    }
}
