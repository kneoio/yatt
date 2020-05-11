INSERT INTO
tasks (reg_date, title, author, last_mod_date, last_mod_user, type, description, status, deadline, priority, assignee )
VALUES
(:regDateTimestamp, :title, :author, :lastModifiedDate, :lastModifier, :typeCode, :description, :statusCode, :deadline, :priorityCode, :assigneeId);

