package com.semantyca.yatt.service;

import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.system.IRLSEntryDAO;
import com.semantyca.yatt.model.IUser;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.embedded.RLSEntry;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    ITaskDAO taskDAO;

    @Autowired
    IRLSEntryDAO RLSEntryDAO;

    @Inject
    Map<Integer,IUser> allUsers;

    public long getCountOfAll(int reader) {
        return taskDAO.getCountAll(reader);
    }

    public List<Task> findAll(int pageSize, int calcStartEntry, int i) {
        return taskDAO.findAll(pageSize, calcStartEntry, i);
    }


    public Task findById(UUID id, int userId, boolean extendedRepresentation) throws DocumentNotFoundException {
         Task task = taskDAO.findById(id, userId);
         if (task != null) {
             if (extendedRepresentation) {
                 task.setAuthorName(allUsers.get(task.getAuthor()));
                 task.setLastModifierName(allUsers.get(task.getLastModifier()));
                 List<RLSEntry> rls = RLSEntryDAO.findByDocumentId(task.getId());
                 for (RLSEntry rlsEntry : rls) {
                     rlsEntry.setReaderName(allUsers.get(rlsEntry.getReader()));
                     task.addReader(rlsEntry);
                 }
             }
             return task;
         } else {
             throw new DocumentNotFoundException(id);
         }
    }

    public int post(Task task, int userId) {
        if (task.getId() == null) {
            task.setRegDate(ZonedDateTime.now());
            task.setAuthor(userId);
            task.setLastModifiedDate(task.getRegDate());
            task.setLastModifier(userId);
            taskDAO.bareInsert(task);
            return 1;
        } else {
            return put(task, userId);
        }
    }

    public int put(Task task, int userId) {
        task.setLastModifiedDate(ZonedDateTime.now());
        task.setLastModifier(userId);
        return taskDAO.bareUpdate(task);
    }

    public int delete(Task task, int reader) {
        return taskDAO.delete(task.getId());
    }
}
