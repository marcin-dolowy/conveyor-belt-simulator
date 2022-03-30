package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ConveyorBelt extends Thread {
    int element;
    Rectangle brick;
    TranslateTransition translateTransition;
    Buffer buffer;
    Parameters parameters;
    HelloController helloController;
    StartApplication startApplication;

    ConveyorBelt(int element, Buffer buffer, Parameters parameters, HelloController helloController, StartApplication startApplication) {
        this.element = element;
        this.buffer = buffer;
        this.parameters = parameters;
        this.helloController = helloController;
        this.startApplication = startApplication;
    }

    private void stage(int x, Rectangle brick, int stageNumber, int weight) {
        if (stageNumber == 1)
            parameters.setFree(true);
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setByX(x);
        translateTransition.setNode(brick);
        translateTransition.setOnFinished(e -> {
            synchronized (this) {
                notify();
            }
        });
        Platform.runLater(() -> {
            translateTransition.play();
        });
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        if (stageNumber == 3) {
            Platform.runLater(() -> {
                HelloApplication.root.getChildren().remove(brick);
            });
            buffer.load(weight);
        } else
            stage(100, brick, stageNumber + 1, weight);
    }

    public void run() {
        brick = new Rectangle();
        if (element == 1) {
            brick.setHeight(15);
            brick.setWidth(20);
            brick.setLayoutX(280);
            brick.setLayoutY(318);
        }
        if (element == 2) {
            brick.setHeight(30);
            brick.setWidth(20);
            brick.setLayoutX(280);
            brick.setLayoutY(311);
        }
        if (element == 3) {
            brick.setHeight(40);
            brick.setWidth(25);
            brick.setLayoutX(280);
            brick.setLayoutY(306);
        }
        Color orange = Color.ORANGE;
        Color black = Color.BLACK;
        brick.setFill(orange);
        brick.setStroke(black);
        while (!parameters.isFree()) ;
        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(brick);
        });

        parameters.setFree(false);
        stage(20, brick, 0, element);

        helloController.loadingBar.setProgress((double) parameters.getTruckLoad() / ((double) startApplication.getTruckCapacity()));
    }
}
