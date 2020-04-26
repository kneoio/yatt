package com.semantyca.yatt.model.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.semantyca.yatt.model.IUser;

import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"readerName", "readingTime", "editAllowed"})
public class RLSEntry {
    public static int EDIT_IS_NOT_ALLOWED = 0;
    public static int EDIT_IS_ALLOWED = 1;
    public static int EDIT_AND_DELETE_ARE_ALLOWED = 2;

    private ZonedDateTime readingTime;
    private int reader;
    private String readerName;
    private int editAllowed;

    @JsonIgnore
    public int getReader() {
        return reader;
    }

    public RLSEntry setReader(int reader) {
        this.reader = reader;
        return this;
    }

    public int getEditAllowed() {
        return editAllowed;
    }

    public RLSEntry setEditAllowed(int editAllowed) {
        this.editAllowed = editAllowed;
        return this;
    }

    public RLSEntry setReadingTime(ZonedDateTime readingTime) {
        this.readingTime = readingTime;
        return this;

    }

    public RLSEntry setReadRightNow(boolean wasRead) {
        if (wasRead) {
            readingTime = ZonedDateTime.now();
        } else {
            readingTime = null;
        }
        return this;
    }

    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    public ZonedDateTime getReadingTime() {
        return readingTime;
    }

    public RLSEntry allowEdit() {
        editAllowed = 1;
        return this;
    }


    public RLSEntry revokeEdit() {
        editAllowed = 0;
        return this;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(IUser user) {
        this.readerName = user.getName();
    }
}