package com.semantyca.yatt.model;

import java.time.ZonedDateTime;

public interface IAppEntity<K>{

    void setId(K id);

    K getId();

    int getAuthor();

    ZonedDateTime getRegDate();

    boolean isEditable();

    String getTitle();

    void setTitle(String title);

    default String getEntityType() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();

    void setLastModifiedDate(ZonedDateTime last_mod_date);

    void setLastModifier(int lastModifier);



    void setRegDate(ZonedDateTime reg_date);

    void setAuthor(int author);
}
