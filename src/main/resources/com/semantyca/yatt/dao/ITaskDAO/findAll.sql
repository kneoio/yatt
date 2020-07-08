SELECT * FROM tasks AS t, task_rls AS rls
WHERE t.id = rls.entity_id AND rls.reader = :reader ORDER BY reg_date DESC LIMIT :limit OFFSET :offset;