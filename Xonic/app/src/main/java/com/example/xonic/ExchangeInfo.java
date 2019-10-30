package com.example.xonic;


public class ExchangeInfo {
    private String balance;
    private int iconCoin;
    private String icon;

    public ExchangeInfo( String balance, int iconCoin, String icon) {
        this.balance = balance;
        this.iconCoin = iconCoin;
        this.icon = icon;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
