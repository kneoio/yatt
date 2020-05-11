UPDATE tasks SET
title = :title,
last_mod_date = :lastModifiedDate,
last_mod_user = :lastModifier,
type = :typeCode,
description = :description,
status = :statusCode,
deadline = :deadline,
priority = :priorityCode,
assignee = :assigneeId
 WHERE ID = :id;

