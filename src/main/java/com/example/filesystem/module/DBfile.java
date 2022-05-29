package com.example.filesystem.module;

public class DBfile {

    private String filePath;
    private String fileName;
    private String fileType;
    private int id;

    public DBfile(String name, String path, String type) {

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
    public int getID(){
        return id;
    }
}
