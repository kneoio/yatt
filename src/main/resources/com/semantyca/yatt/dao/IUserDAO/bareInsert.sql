INSERT INTO users
(reg_date, title, author, last_mod_date, last_mod_user, login, pwd, name, email, roles)
VALUES
(:regDate, :title, :author, :lastModifiedDate, :lastModifier, :login, :pwd, :name, :email, :rolesAsJSON );

