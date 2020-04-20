package com.semantyca.yatt.model.embedded;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class RLS {

    private boolean wasRead;

    private Date readingTime;

    private int reader;

    private int editAllowed;

    @JsonIgnore
    public int getReader() {
        return reader;
    }

    @JsonIgnore
    public RLS setReader(Integer reader) {
        this.reader = reader;
        return this;
    }

    public int getEditAllowed() {
        return editAllowed;
    }

    @JsonIgnore
    public void setEditAllowed(int editAllowed) {
        this.editAllowed = editAllowed;
    }

    public boolean isWasRead() {
        return wasRead;
    }

    @JsonIgnore
    public void setReadingTime(Date readingTime) {
        this.readingTime = readingTime;
    }

    @JsonIgnore
    public void setReader(int reader) {
        this.reader = reader;
    }

    @JsonIgnore
    public void setWasRead(boolean wasRead) {
        if (wasRead != this.wasRead) {
            if (wasRead) {
                readingTime = new Date();
                this.wasRead = true;
            } else {
                readingTime = null;
                this.wasRead = false;
            }
        }
    }

    public Date getReadingTime() {
        return readingTime;
    }

    @JsonIgnore
    public RLS allowEdit() {
        editAllowed = 1;
        return this;
    }
    @JsonIgnore
    public RLS revokeEdit() {
        editAllowed = 0;
        return this;
    }
}