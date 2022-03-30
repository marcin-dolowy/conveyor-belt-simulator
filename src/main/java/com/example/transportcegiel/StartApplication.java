package com.example.transportcegiel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StartApplication {
    int truckCapacity = 0;
    int conveyorBeltCapacity = 0;
    int maxBrickAmount = 0;

    public int getTruckCapacity() {
        return truckCapacity;
    }

    public void StartSimulation(HelloController helloController) {
        try (InputStream inputStream = StartApplication.class.getClassLoader().getResourceAsStream("data.properties")) {
            Properties properties = new Properties();
            if (inputStream == null) {
                System.out.println("File not found.");
            } else {
                properties.load(inputStream);
                truckCapacity = Integer.parseInt(properties.getProperty("truckCapacity"));
                conveyorBeltCapacity = Integer.parseInt(properties.getProperty("conveyorBeltCapacity"));
                maxBrickAmount = Integer.parseInt(properties.getProperty("maxBrickAmount"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String stringTruckCapacity = helloController.truckCapacityTF.getText();
        String stringConveyorBeltCapacity = helloController.conveyorBeltCapacity.getText();
        String stringMaxBrickAmount = helloController.maxBrickAmountTF.getText();

        int tempTruckCapacity;
        int tempConveyorBeltCapacity;
        int tempMaxBrickAmount;

        if (!stringTruckCapacity.isEmpty()) {
            tempTruckCapacity = Integer.parseInt(stringTruckCapacity);
        } else {
            tempTruckCapacity = truckCapacity;
            helloController.truckCapacityTF.setText(tempTruckCapacity + "");
        }

        if (!stringTruckCapacity.isEmpty()) {
            tempConveyorBeltCapacity = Integer.parseInt(stringConveyorBeltCapacity);

        } else {
            tempConveyorBeltCapacity = conveyorBeltCapacity;
            helloController.conveyorBeltCapacity.setText(Integer.toString(tempConveyorBeltCapacity));
        }
        if (!stringTruckCapacity.isEmpty()) {
            tempMaxBrickAmount = Integer.parseInt(stringMaxBrickAmount);

        } else {
            tempMaxBrickAmount = maxBrickAmount;
            helloController.maxBrickAmountTF.setText(Integer.toString(tempMaxBrickAmount));
        }

        int numberOfWorkers = 3;
        Worker[] workers = new Worker[numberOfWorkers];
        Parameters parameters = new Parameters(0, 0);
        Buffer buffer = new Buffer(tempMaxBrickAmount, 0, tempConveyorBeltCapacity, parameters, helloController);
        Truck truck = new Truck(tempTruckCapacity, buffer, parameters, helloController);

        for (int i = 0; i < numberOfWorkers; i++) {
            Worker worker = new Worker(i, tempTruckCapacity, buffer, parameters, helloController, this);
            workers[i] = worker;
        }

        truck.start();

        for (int i = 0; i < numberOfWorkers; i++) {
            workers[i].start();
        }
        this.truckCapacity = tempTruckCapacity;
    }
}
