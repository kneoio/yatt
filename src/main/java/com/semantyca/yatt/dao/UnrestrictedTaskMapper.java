package com.semantyca.yatt.dao;

import com.semantyca.yatt.configuration.ApplicationContextKeeper;
import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.constant.StatusType;
import com.semantyca.yatt.model.constant.PriorityType;
import com.semantyca.yatt.model.constant.TaskType;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UnrestrictedTaskMapper extends AbstractMapper<Task> {

    private IAssigneeDAO assigneeDAO;

    public UnrestrictedTaskMapper() {
        super();
        assigneeDAO = ApplicationContextKeeper.getContext().getBean(IAssigneeDAO.class);
    }


    @Override
    public Task map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        Task task = new Task();
        transferCommonData(task, rs);
        task.setType(TaskType.getType(rs.getInt("type")));
        task.setStatus(StatusType.getType(rs.getInt("priority")));
        task.setPriority(PriorityType.getType(rs.getInt("status")));
        task.setDescription(rs.getString("description"));
        task.setAssignee(assigneeDAO.findById(rs.getObject("assignee", UUID.class)));
        task.setDeadline(getDateTime(rs.getTimestamp("deadline")));
       return task;
    }



}
