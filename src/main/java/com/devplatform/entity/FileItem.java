package com.devplatform.entity;

public class FileItem {

    private String filename;

    private boolean isfile;

    private long updateDate;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public boolean isIsfile() {
        return isfile;
    }

    public void setIsfile(boolean isfile) {
        this.isfile = isfile;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
