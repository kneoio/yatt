package com.semantyca.yatt.model.embedded;

import java.util.Date;

public class RLS {

    private boolean wasRead;

    private Date readingTime;

    private int reader;

    private int editAllowed;

    public int getReader() {
        return reader;
    }

    public RLS setReader(Integer reader) {
        this.reader = reader;
        return this;
    }

    public int getEditAllowed() {
        return editAllowed;
    }

    public void setEditAllowed(int editAllowed) {
        this.editAllowed = editAllowed;
    }

    public boolean isWasRead() {
        return wasRead;
    }


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

    public RLS allowEdit() {
        editAllowed = 1;
        return this;
    }

    public RLS revokeEdit() {
        editAllowed = 0;
        return this;
    }
}