package com.semantyca.yatt.service;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.system.IRLSEntryDAO;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.IUser;
import com.semantyca.yatt.model.NewTask;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.embedded.RLSEntry;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import com.semantyca.yatt.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    ITaskDAO taskDAO;

    @Inject
    IAssigneeDAO assigneeDAO;

    @Autowired
    IRLSEntryDAO RLSEntryDAO;

    @Inject
    Map<Integer,IUser> allUsers;

    public long getCountOfAll(int reader) {
        return taskDAO.getCountAll(reader);
    }

    public ViewPage findAll(String pageSizeAsString, String pageNumAsString, int reader) {
        int size = NumberUtil.stringToInt(pageSizeAsString, EnvConst.DEFAULT_PAGE_SIZE);
        int num = NumberUtil.stringToInt(pageNumAsString, 0);
        long count = getCountOfAll(reader);
        int startEntry = NumberUtil.calcStartEntry(num, size);
        List<Task>  result;
        if (size > -1) {
            result = taskDAO.findAll(size, startEntry, reader);
        } else {
            result = taskDAO.findAll(999, startEntry, 1);
        }
        return new ViewPage(result, count, startEntry, num, size);
    }

    public long getCountOfAllMyTasks(int reader, int author) {
        return taskDAO.getCountAllMyTasks(reader, author);
    }

    public List<Task> findAllMyTasks(int pageSize, int calcStartEntry, int i, int author) {
        return taskDAO.findAllMyTasks(pageSize, calcStartEntry, i, author);
    }

    public Task getNewTask(int userId){
        IUser user = allUsers.get(userId);
        Task task = new NewTask();
        task.setAuthorName(user);
        task.setLastModifierName(user);
        task.setRegDate(ZonedDateTime.now());
        RLSEntry rlsEntry = new RLSEntry();
        rlsEntry.setReaderName(user);
        task.addReader(rlsEntry);
        return task;
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

    public UUID post(@RequestBody @Valid Task task, int userId) {
        if (task.getId() == null) {
            task.setRegDate(ZonedDateTime.now());
            task.setAuthor(userId);
            task.setLastModifiedDate(task.getRegDate());
            task.setLastModifier(userId);
            Assignee assignee = assigneeDAO.findById(task.getAssigneeId());
            if (assignee != null){
                task.setAssignee(assignee);
            }
            return taskDAO.bareInsert(task);
        } else {
            return put(task, userId);
        }
    }

    public UUID put(Task task, int userId) {
        task.setLastModifiedDate(ZonedDateTime.now());
        task.setLastModifier(userId);
        Assignee assignee = assigneeDAO.findById(task.getAssigneeId());
        if (assignee != null){
            task.setAssignee(assignee);
        }
        return taskDAO.bareUpdate(task);
    }

    public int delete(Task task, int reader) {
        return taskDAO.delete(task.getId());
    }
}
