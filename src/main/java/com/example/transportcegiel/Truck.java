package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Truck extends Thread {
    public int truckCapacity;
    public boolean quit = false;
    Buffer buffer;
    Parameters parameters;
    HelloController helloController;

    public Truck(int truckCapacity, Buffer buffer, Parameters parameters, HelloController helloController) {
        this.truckCapacity = truckCapacity;
        this.buffer = buffer;
        this.parameters = parameters;
        this.helloController = helloController;
    }

    public void run() {
        while (!quit) {
            if (parameters.getTruckLoad() == truckCapacity) {

                buffer.truckDeparture();

                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setToX(350);
                translateTransition.setToY(250);
                translateTransition.setDuration(Duration.millis(1000));
                translateTransition.setNode(helloController.truck);
                translateTransition.setOnFinished(e -> {
                    synchronized (this) {
                        notify();
                    }
                });
                Platform.runLater(translateTransition::play);
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ignored) {
                    }
                }

                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(0.1));
                translateTransition.setNode(helloController.truck);
                translateTransition.setOnFinished(e -> {
                    synchronized (this) {
                        notify();
                    }
                });
                Platform.runLater(translateTransition::play);
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ignored) {
                    }
                }
                helloController.loadingBar.setProgress(0);
            }
        }

    }
}