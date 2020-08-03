package com.example.ohmymoney;

public class Budget {
    private int id;
    private String store_name;
    private int store_price;

    public Budget(int id, String store_name, int store_price){
        this.id=id;
        this.store_name=store_name;
        this.store_price=store_price;
    }
    public int getId(){
        return id;
    }
    public String getStore_name(){
        return store_name;
    }
    public int getStore_price(){
        return store_price;
    }
}
