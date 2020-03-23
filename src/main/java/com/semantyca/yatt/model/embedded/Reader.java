package com.semantyca.yatt.model.embedded;

import java.util.Date;

public class Reader {

    private boolean wasRead;

    private Date readingTime;

    private Long reader;

    public Long getReader() {
        return reader;
    }

    public void setReader(Long reader) {
        this.reader = reader;
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