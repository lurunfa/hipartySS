package com.beans;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/4/19.
 */
@Entity
@Table(name="loadfile")
public class LoadFile {
    private String Filename;
    private int id;
    private String FileUrl;

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

}
