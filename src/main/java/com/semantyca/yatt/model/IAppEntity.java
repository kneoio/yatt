package com.semantyca.yatt.model;

import java.time.ZonedDateTime;

public interface IAppEntity<K>{

    long getAuthor();

    ZonedDateTime getRegDate();

    boolean isEditable();

    String getTitle();

    void setTitle(String title);

    default String getEntityType() {
        return this.getClass().getSimpleName();
    }

    boolean isNew();
}
