package com.semantyca.yatt.dao;

import com.semantyca.yatt.model.Task;
import com.semantyca.yatt.model.embedded.RLSEntry;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@UseClasspathSqlLocator
public interface ITaskDAO extends IDAO<Task,UUID> {
    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    Task findById(@Bind("id") UUID id, @Bind("reader") int reader);

    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    List<Task> findAll(@Bind("limit") int limit, @Bind("offset") int offset, @Bind("reader") long reader);

    @SqlQuery
    long getCountAllMyTasks(@Bind("reader") long reader,  long author);

    @SqlQuery
    @RegisterColumnMapper(TaskMapper.class)
    List<Task> findAllMyTasks(@Bind("limit") int limit, @Bind("offset") int offset, @Bind("reader") long reader,  long author);

    @SqlQuery
    @RegisterColumnMapper(UnrestrictedTaskMapper.class)
    List<Task> findAllUnrestricted(@Bind("limit") int limit, @Bind("offset") int offset);


    @SqlUpdate
    int addReader(UUID id, int user, ZonedDateTime readingTime, int editAllowed);

    @SqlUpdate
    @GetGeneratedKeys("id")
    UUID bareInsert(@BindBean Task task);

    @Transaction
    default UUID insertSecured(Task task) {
        UUID documentId = bareInsert(task);
        for (RLSEntry rlsEntry: task.getReaders()) {
            if (task.getAuthor() == rlsEntry.getReader()){
                addReader(documentId, rlsEntry.getReader(), ZonedDateTime.now(), RLSEntry.EDIT_AND_DELETE_ARE_ALLOWED);
            } else {
                addReader(documentId, rlsEntry.getReader(), null, rlsEntry.getEditAllowed());
            }
        }
        return documentId;
    }

}



