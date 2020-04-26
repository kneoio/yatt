package com.semantyca.yatt.dao;

import com.semantyca.yatt.configuration.ApplicationContextKeeper;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.constant.StageType;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.TaskType;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TaskMapper  extends AbstractMapper<Task> {

    private IAssigneeDAO assigneeDAO;

    public TaskMapper() {
        super();
        assigneeDAO = ApplicationContextKeeper.getContext().getBean(IAssigneeDAO.class);
    }


    @Override
    public Task map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Task entity = new Task();
        transferIdUUID(entity, rs);
        transferCommonData(entity, rs);
        //RLS rls = new RLS();
        //rls.setReader(rs.getInt("reader"));
        //rls.setEditAllowed(rs.getInt("is_edit_allowed"));
        //rls.setReadingTime(getDateTime(rs.getTimestamp("reading_time")));


        //entity.setReaders(rls);
        entity.setType(TaskType.getType(rs.getInt("type")));
        entity.setStage(StageType.getType(rs.getInt("stage")));
        entity.setStatus(StatusType.getType(rs.getInt("status")));
        entity.setDescription(rs.getString("description"));
        entity.setAssignee(assigneeDAO.findById(rs.getObject("assignee", UUID.class)));
        entity.setDeadline(getDateTime(rs.getTimestamp("deadline")));
       return entity;
    }




}
