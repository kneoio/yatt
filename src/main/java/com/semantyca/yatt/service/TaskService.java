package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    ITaskDAO taskDAO;


    public long getCountOfAll(long reader) {
        return 0;
    }

    public List<Task> findAll(int pageSize, int calcStartEntry, int i) {
        return taskDAO.findAll(pageSize, calcStartEntry, i);
    }
}
