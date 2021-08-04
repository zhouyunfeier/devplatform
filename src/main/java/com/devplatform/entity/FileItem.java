package com.devplatform.entity;

public class FileItem {

    private String filename;

    private String path;

    private boolean isfile;

    private long updateDate;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
