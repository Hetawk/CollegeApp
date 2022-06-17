package com.ekd.college.control.DBHandlers;

public class RefferenceMaterialsModel {
    private String id;
    private String url;
    private String name;
    public RefferenceMaterialsModel(){

    }
    public RefferenceMaterialsModel(String id, String name,String url) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
