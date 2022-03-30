package com.example.transportcegiel;

public class Parameters {
    private int truckLoad;
    private int currentCapacity;
    private boolean isFree;

    public Parameters(int truckLoad, int currentCapacity) {
        this.truckLoad = truckLoad;
        this.currentCapacity = currentCapacity;
        this.isFree = true;
    }

    public synchronized boolean isFree() {
        return isFree;
    }

    public synchronized void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public synchronized void setTruckLoad(int truckLoad) {
        this.truckLoad = truckLoad;
    }

    public synchronized void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public synchronized int getTruckLoad() {
        return truckLoad;
    }

    public synchronized int getCurrentCapacity() {
        return currentCapacity;
    }
}
