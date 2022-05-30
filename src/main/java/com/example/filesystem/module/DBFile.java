package com.example.filesystem.module;

public class DBFile {

    private String filePath;
    private String fileName;
    private String fileType;
    private int id;

    public DBFile(String name, String path, String type) {

        this.fileName = name;
        this.fileType = type;
        this.filePath = path;

    }

    public String getName() {
        return fileName;
    }

    public String getType() {
        return fileType;
    }

    public String getPath() {
        return filePath;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
