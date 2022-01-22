package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Tasma extends Thread {
    int elem;
    Rectangle cegla;
    TranslateTransition translateTransition;
    Buffer bufor;
    Pomocnicza pomocnicza;
    HelloController helloController;
    StartApplication startApplication;

    Tasma(int elem, Buffer bufor, Pomocnicza pomocnicza, HelloController helloController, StartApplication startApplication) {
        this.elem = elem;
        this.bufor = bufor;
        this.pomocnicza = pomocnicza;
        this.helloController = helloController;
        this.startApplication = startApplication;
    }

    private void etap(int x, Rectangle cegla, int ktory_etap, int masa) {
        if (ktory_etap == 1)
            pomocnicza.setCzy_wolne(true);
        translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setByX(x);
        translateTransition.setNode(cegla);
        translateTransition.setOnFinished(e -> { synchronized (this) { notify(); } });
        Platform.runLater(() -> { translateTransition.play(); });
        synchronized (this) { try { wait(); } catch (InterruptedException e) {} }
        if (ktory_etap == 3) {
            Platform.runLater(() -> {
                HelloApplication.root.getChildren().remove(cegla);
            });
            bufor.pobierz(masa);
        }

        else
            etap(100, cegla, ktory_etap+1, masa);
    }

    public void run() {
        cegla = new Rectangle();
        if(elem == 1) {
            cegla.setHeight(15);
            cegla.setWidth(20);
            cegla.setLayoutX(280);
            cegla.setLayoutY(318);
        }
        if(elem == 2) {
            cegla.setHeight(30);
            cegla.setWidth(20);
            cegla.setLayoutX(280);
            cegla.setLayoutY(311);
        }
        if(elem == 3) {
            cegla.setHeight(40);
            cegla.setWidth(25);
            cegla.setLayoutX(280);
            cegla.setLayoutY(306);
        }
        Color orange = Color.ORANGE;
        Color black = Color.BLACK;
        cegla.setFill(orange);
        cegla.setStroke(black);
        while (!pomocnicza.isCzy_wolne());
        Platform.runLater(() -> { HelloApplication.root.getChildren().add(cegla); });
        pomocnicza.setCzy_wolne(false);
        etap(20, cegla, 0, elem);

        helloController.pasekLadowania.setProgress((double) pomocnicza.getZaladowanie() / ((double) startApplication.getLadownosc()));

    }
}
