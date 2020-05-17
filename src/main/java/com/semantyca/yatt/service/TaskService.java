package com.semantyca.yatt.service;

import com.semantyca.yatt.EnvConst;
import com.semantyca.yatt.dao.IAssigneeDAO;
import com.semantyca.yatt.dao.ITaskDAO;
import com.semantyca.yatt.dao.system.IRLSEntryDAO;
import com.semantyca.yatt.dto.actions.Action;
import com.semantyca.yatt.dto.actions.ActionType;
import com.semantyca.yatt.dto.actions.ActionsBuilder;
import com.semantyca.yatt.dto.view.ViewPage;
import com.semantyca.yatt.model.Assignee;
import com.semantyca.yatt.model.IUser;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.constant.PriorityType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;
import com.semantyca.yatt.model.embedded.RLSEntry;
import com.semantyca.yatt.model.exception.RLSIsNotNormalized;
import com.semantyca.yatt.service.exception.DocumentAccessException;
import com.semantyca.yatt.service.exception.DocumentNotFoundException;
import com.semantyca.yatt.util.NumberUtil;
import com.semantyca.yatt.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
        Task task = new Task();
        task.setPriority(PriorityType.LOW);
        task.setStatus(StatusType.DRAFT);
        task.setType(TaskType.DEVELOPING);
        task.setTitle("New task");
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
                 normalizeRLS(task);
             }
             return task;
         } else {
             throw new DocumentNotFoundException(id);
         }
    }


    public List<Action> getActions(Task task, int userId) throws RLSIsNotNormalized {
        ActionsBuilder builder = new ActionsBuilder();
        builder.addAction(ActionType.CLOSE_FORM);
        if (task.isNew()){
            builder.addAction(ActionType.SAVE);
        } else {
            if (task.getRLS(userId).getAccessLevel() > RLSEntry.READ_ONLY) {
                builder.addAction(ActionType.SAVE);
            }
            if (task.getStatus() == StatusType.DRAFT) {
                builder.addCustomAction("send_to_implementation");
            }
        }
        return builder.build();
    }


    public Task save(Task task, int userId) throws DocumentNotFoundException, DocumentAccessException, RLSIsNotNormalized {
        if (task.getId() == null) {
            task.setRegDate(ZonedDateTime.now());
            task.setAuthor(userId);
            RLSEntry entry = new RLSEntry();
            entry.setReader(userId);
            task.addReader(entry);
            updateCommonFileds(task, userId);
            Assignee assignee = assigneeDAO.findById(task.getAssigneeId());
            if (assignee != null){
                task.setAssignee(assignee);
            }
            UUID uuid = taskDAO.insertSecured(task);
            Task updatedTask = taskDAO.findById(uuid, userId);
            if (updatedTask == null) {
                throw new DocumentNotFoundException(uuid);
            }
            normalizeRLS(updatedTask);
            return updatedTask;
        } else {
            return update(task, userId);
        }
    }

    public Task update(Task task, int userId) throws DocumentNotFoundException, DocumentAccessException, RLSIsNotNormalized {
        normalizeRLS(task);
        if (task.getRLS(userId).getAccessLevel() > RLSEntry.READ_ONLY) {
            updateCommonFileds(task, userId);
            Assignee assignee = assigneeDAO.findById(task.getAssigneeId());
            if (assignee != null) {
                task.setAssignee(assignee);
            }
            UUID uuid = taskDAO.bareUpdate(task);
            if (uuid != null) {
                Task updatedTask = taskDAO.findById(uuid, userId);
                if (updatedTask == null) {
                    throw new DocumentNotFoundException(uuid);
                }
                normalizeRLS(updatedTask);
                return updatedTask;
            }
            return null;
        } else {
            throw new DocumentAccessException(task.getId(), RLSEntry.EDIT_IS_ALLOWED);
        }
    }

    public int delete(Task task, int userId) throws RLSIsNotNormalized {
        normalizeRLS(task);
        if (task.getRLS(userId).getAccessLevel() == RLSEntry.EDIT_AND_DELETE_ARE_ALLOWED) {
            return taskDAO.delete(task.getId());
        }
        return 0;
    }

    private void updateCommonFileds(Task task, int userId) {
        task.setLastModifiedDate(ZonedDateTime.now());
        task.setLastModifier(userId);
        if (task.getTitle().trim().equals("")) {
            task.setTitle(StringUtils.abbreviate(StringUtil.cleanFromMarkdown(task.getDescription()), 140));
        }
    }

    private void normalizeRLS(Task task) {
        List<RLSEntry> rls = RLSEntryDAO.findByDocumentId(task.getId());
        for (RLSEntry rlsEntry : rls) {
            rlsEntry.setReaderName(allUsers.get(rlsEntry.getReader()));
            task.addReader(rlsEntry);
        }
    }
}
