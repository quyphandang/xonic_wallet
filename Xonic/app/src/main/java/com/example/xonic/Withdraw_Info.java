package com.example.xonic;


public class Withdraw_Info {
    private String item;
    private String subitem;

    public Withdraw_Info(String item, String subitem) {
        this.item = item;
        this.subitem = subitem;
    }

    public String getName() {
        return item;
    }

    public void setName(String name) {
        this.item = item;
    }

    public String getBalance() {
        return subitem;
    }

    public void setBalance(String balance) {
        this.subitem = subitem;
    }

}
