package com.example.silas.morsechat;

import java.util.Date;

/**
 * Created by silas on 1/12/2018.
 */

public class Message {
    private String content, username,uID, time;

    public Message(){

    }
    public Message(String content, String username, String uID){
        this.content = content;
        this.username = username;
        this.uID = uID;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuID() {
        return uID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
