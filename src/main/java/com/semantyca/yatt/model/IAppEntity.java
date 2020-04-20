package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.ZonedDateTime;

@JsonPropertyOrder({"entityType", "id", "author", "regDate", "lastModifier", "lastModifiedDate", "title", "new"})
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

    void setLastModifier(int last_mod_user);

    void setRegDate(ZonedDateTime reg_date);

    void setAuthor(int author);
}
