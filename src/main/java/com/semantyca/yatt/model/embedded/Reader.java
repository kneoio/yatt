package com.semantyca.yatt.model.embedded;

import java.util.Date;

public class Reader {

    private boolean wasRead;

    private Date readingTime;

    private int reader;

    public int getReader() {
        return reader;
    }

    public Reader setReader(Integer reader) {
        this.reader = reader;
        return this;
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
}