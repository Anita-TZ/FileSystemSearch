package com.example.filesystem.service;

import com.example.filesystem.module.DBfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DBInitial {

    private static String pathe = "";
    private static File files;
    ArrayList<DBfile> fileList = new ArrayList<>();

    public DBInitial(String path) {

        this.pathe=path;
        files = new File(pathe);
        File[] data = files.listFiles();
        //there is no file
        if (data == null) {
            return;
        }
        //get all the information
        for (File a : data) {
            getFileInformation(a);
        }
    }

    private void getFileInformation(File file) {

        if (file.isDirectory()) {
            fileList.add(new DBfile(file.getName(), file.getAbsolutePath(), "Folder"));
            File[] sub = file.listFiles();
            if (sub != null) {
                for (File subFile : sub) {
                    getFileInformation(subFile);
                }
            }
        } else {
            fileList.add(new DBfile(file.getName(), file.getAbsolutePath(), "File"));
        }
    }

    public void createFileTxt() throws IOException {
        File structure = new File(pathe + File.separator + "fileStructure.txt");
        structure.createNewFile();
        StringBuilder writeIn= new StringBuilder();

        //Store all the information as a string
        for(DBfile file:fileList){
            writeIn.append(file.getPath());
            writeIn.append("\t");
            writeIn.append(file.getType());
            writeIn.append("\n");
        }

        //Write into txt file
        FileWriter fileWrite=new FileWriter(structure, false);
        fileWrite.write(writeIn.toString());
        fileWrite.flush();
        fileWrite.close();

    }

}
