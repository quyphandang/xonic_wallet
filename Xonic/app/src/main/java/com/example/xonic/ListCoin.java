package com.example.xonic;


public class ListCoin {
    private String name;
    private String balance;
    private int iconCoin;

    public ListCoin(String name, String balance, int iconCoin) {
        this.name = name;
        this.balance = balance;
        this.iconCoin = iconCoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getIconCoin() {
        return iconCoin;
    }

    public void setIconCoin(int iconCoin) {
        this.iconCoin = iconCoin;
    }
}
