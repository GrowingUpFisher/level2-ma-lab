package com.appdynamics.javacert.ma;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trader on 2/14/17.
 */
public class MonitoredDataSource {

    /**
     * This class generates fake data at irregular intervals.  It is simulating transactions
     * from an online store.
     *
     * The customer wants to track the following data:
     * - average per-item cost (in cents)
     * - average number of items purchased
     * - revenue (in cents) per time interval
     * - number pf purchases per time interval
     *
     * The raw data given is the number of items purchased, and the total purchase amount (in cents).
     */

    private List<PurchaseRecord> purchases;

    public MonitoredDataSource() {
        purchases = new ArrayList<PurchaseRecord>();
    }

    public List<PurchaseRecord> getPurchaseRecords() {
        List<PurchaseRecord> result = new ArrayList<PurchaseRecord>();
        synchronized (purchases) {
            result.addAll(purchases);
            purchases.clear();
        }

        return result;
    }

    void addPurchaseRec(PurchaseRecord rec) {
        synchronized (purchases) {
            purchases.add(rec);
        }
    }

    private class DataGenerator extends Thread {

        private static final long MAX_SLEEP_TIME = 30000;
        private static final long MAX_PURCHASE_COUNT = 20;
        private static final long MAX_PRICE_PER_PURCHASE = 10000;

        DataGenerator() {
            super();
        }

        public void run() {
            while (true) {

                sleepRandomTime();
                PurchaseRecord rec = new PurchaseRecord();
                int nPurchases = (int) (Math.random() * MAX_PURCHASE_COUNT);

                for (int i = 0; i < nPurchases; ++i) {
                    int priceInCents = (int) (Math.random() * MAX_PRICE_PER_PURCHASE);
                    rec.purchase(priceInCents);
                }

                addPurchaseRec(rec);
            }
        }

        private void sleepRandomTime() {
            try {
                long sleepTime = (long) (Math.random() * MAX_SLEEP_TIME);
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {

            }
        }
    }
}
