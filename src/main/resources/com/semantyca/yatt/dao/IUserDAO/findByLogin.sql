SELECT
  id,
  reg_date,
  title,
  author,
  last_mod_date,
  last_mod_user,
  login,
  pwd,
  email,
  name
FROM users WHERE login = :login;