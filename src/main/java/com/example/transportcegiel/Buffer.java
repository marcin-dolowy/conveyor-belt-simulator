package com.example.transportcegiel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    public int count = 0;
    public int currentWeight;
    public int conveyorBeltCapacity;
    int N;
    Parameters parameters;
    final Lock access = new ReentrantLock();
    final Condition full = access.newCondition();
    final Condition overloaded = access.newCondition();

    HelloController helloController;

    public Buffer(int N, int currentWeight, int conveyorBeltCapacity, Parameters parameters, HelloController helloController) {
        this.N = N;
        this.currentWeight = currentWeight;
        this.conveyorBeltCapacity = conveyorBeltCapacity;
        this.parameters = parameters;
        this.helloController = helloController;
    }

    public void insertToTruck(int elem) {
        access.lock();
        try {
            if (count == N) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (parameters.getCurrentCapacity() + elem > conveyorBeltCapacity) {
                try {
                    overloaded.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            parameters.setCurrentCapacity(parameters.getCurrentCapacity() + elem);
        } finally {
            access.unlock();
        }
    }

    public void load(int elem) {
        access.lock();
        try {
            parameters.setCurrentCapacity(parameters.getCurrentCapacity() - elem);
            parameters.setTruckLoad(parameters.getTruckLoad() + elem);
            count--;
            full.signal();
            overloaded.signal();
            System.out.println("[T" + elem + "] >> " + parameters.getTruckLoad() + ", " + parameters.getCurrentCapacity());
        } finally {
            access.unlock();
        }
    }

    public void truckDeparture() {
        access.lock();
        try {
            parameters.setTruckLoad(0);
            System.out.println("Departure\n");
        } finally {
            access.unlock();
        }
    }
}

