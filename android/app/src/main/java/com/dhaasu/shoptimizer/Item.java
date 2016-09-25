package com.dhaasu.shoptimizer;

/**
 * Created by ramitsuri on 9/24/16.
 */
public class Item {
    private String itemName;
    private int itemQuantity;

    public Item(){

    }

    public Item(String name){
        this.itemName = name;
        //this.itemQuantity = itemQuantity;
    }

    public String getItemName(){
        return this.itemName;
    }

    public void setItemName(String name){
        this.itemName = name;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int quantity){
        this.itemQuantity = quantity;
    }
}
