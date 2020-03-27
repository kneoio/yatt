SELECT
  id,
  reg_date,
  title,
  author,
  last_mod_date,
  last_mod_user,
  login,
  email,
  name
FROM
  users
WHERE
  id = :id;