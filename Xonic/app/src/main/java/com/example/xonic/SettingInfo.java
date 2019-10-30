package com.example.xonic;


public class SettingInfo {
    private String nameid;
    private String infoid;

    public SettingInfo(String nameid, String infoid) {
        this.nameid = nameid;
        this.infoid = infoid;
    }

    public String getBalance() {
        return nameid;
    }

    public void setBalance(String nameid) {
        this.nameid = nameid;
    }


    public String getIcon() {
        return infoid;
    }

    public void setIcon(String infoid) {
        this.infoid = infoid;
    }

}
