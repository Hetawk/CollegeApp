package com.ekd.college.control.DBHandlers;

//package com.example.studentmanagementsystem.DBHandlers;

public class NoticesModel  {

    private String id;
    private String content;
    private String title;



    public NoticesModel() {
    }

    public NoticesModel(String id,String title,String content) {

        this.id = id;
        this.title = title;
        this.content = content;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
