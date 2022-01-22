package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class Ciezarowka extends Thread {
    public int ladownosc;
    public boolean koniec = false;
    Buffer bufor;
    Pomocnicza pomocnicza;
    HelloController helloController;

    public Ciezarowka(int ladownosc, Buffer bufor, Pomocnicza pomocnicza, HelloController helloController) {
        this.ladownosc = ladownosc;
        this.bufor = bufor;
        this.pomocnicza = pomocnicza;
        this.helloController = helloController;
    }

    public void run() {
        while (!koniec) {
            if (pomocnicza.getZaladowanie() == ladownosc) {

                bufor.odjazd();

                TranslateTransition translateTransition = new TranslateTransition();
                translateTransition.setToX(350);
                translateTransition.setToY(250);
                translateTransition.setDuration(Duration.millis(1000));
                translateTransition.setNode(helloController.ciezarowka);
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
                    } catch (InterruptedException e) {
                    }
                }

                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(0.1));
                translateTransition.setNode(helloController.ciezarowka);
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
                    } catch (InterruptedException e) {
                    }
                }
                helloController.pasekLadowania.setProgress(0);
            }
        }

    }
}