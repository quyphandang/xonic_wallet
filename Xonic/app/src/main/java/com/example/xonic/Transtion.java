package com.example.xonic;


public class Transtion {
    private String day;
    private int iconTranstion;
    private String status;
    private String transtion;
    private String amount;

    public Transtion(String day,int iconTranstion, String status, String transtion, String amount) {
        this.day = day;
        this.iconTranstion = iconTranstion;
        this.status = status;
        this.transtion = transtion;
        this.amount = amount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public int getIconTranstion() {
        return iconTranstion;
    }

    public void setIconTranstion(int iconTranstion) {
        this.iconTranstion = iconTranstion;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getTranstion() {
        return transtion;
    }

    public void setTranstion(String transtion) {
        this.transtion = transtion;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
