package com.appdynamics.javacert.ma;

/**
 * Created by trader on 2/14/17.
 */
public class PurchaseRecord {

    private int totalPrice;
    private int itemCount;

    public PurchaseRecord() {
    }

    public int getTotalPurchasePriceInCents() {
        return totalPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    void purchase(int priceInCents) {
        itemCount++;
        totalPrice += priceInCents;
    }
}
