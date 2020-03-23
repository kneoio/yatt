package com.semantyca.yatt.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.ZonedDateTime;
import java.util.UUID;

public abstract class AppEntity<K> implements IAppEntity<K> {

    protected K id;

    protected long author;

    private ZonedDateTime regDate;

    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    private Long lastModifier;

    private boolean editable = true;

    private String title = "";

    public void setId(String id) {
        this.id = (K)UUID.fromString(id);
    }

    public void setId(K id) {
        this.id = id;
    }

    public K getId() {
        return id;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    @Override
    public long getAuthor() {
        return author;
    }

    @Override
    public ZonedDateTime getRegDate() {
        return regDate;
    }

    @JsonSetter("reg_date")
    public void setRegDate(ZonedDateTime regDate) {
        this.regDate = regDate;
    }

    @JsonSetter("last_mod_date")
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Long getLastModifier() {
        return lastModifier;
    }

    @JsonSetter("last_mod_user")
    public void setLastModifier(Long lastModifier) {
        this.lastModifier = lastModifier;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    public void setEditable(int isEditable) {
        if (isEditable == 1) {
            editable = true;
        }
        editable = false;
    }

    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        @SuppressWarnings("unchecked")
        AppEntity<UUID> tmp = (AppEntity<UUID>) obj;

        return id != null && id.equals(tmp.id);
    }

}
