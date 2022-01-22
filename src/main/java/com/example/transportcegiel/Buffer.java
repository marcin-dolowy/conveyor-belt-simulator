package com.example.transportcegiel;

import javafx.animation.TranslateTransition;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    public Random rand = new Random();
    public int wej = 0;
    public int wyj = 0;
    public int licz = 0;
    public int obecnaMasa;
    public int udzwig;
    public int elem;
    int N;
    Pomocnicza pomocnicza;
    final Lock dostep = new ReentrantLock();
    final Condition pusty = dostep.newCondition();
    final Condition pelny = dostep.newCondition();
    final Condition przeciazony = dostep.newCondition();


    TranslateTransition translateTransition = new TranslateTransition();

    HelloController helloController;

    public Buffer(int N, int obecnaMasa, int udzwig, Pomocnicza pomocnicza, HelloController helloController) {
        this.N = N;
        this.obecnaMasa = obecnaMasa;
        this.udzwig = udzwig;
        this.pomocnicza = pomocnicza;
        this.helloController = helloController;
    }

    public void wstaw(int elem) {
        dostep.lock();
        try {
            if (licz == N) {
                try {
                    pelny.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (pomocnicza.getObecnaMasa() + elem > udzwig) {
                try {
                    przeciazony.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            licz++;
            pomocnicza.setObecnaMasa(pomocnicza.getObecnaMasa() + elem);
            //pusty.signal();
        } finally {
            dostep.unlock();
        }
    }

    public void pobierz(int elem) {
        dostep.lock();
        try {
            /*if (licz == 0) {
                try {
                    pusty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            pomocnicza.setObecnaMasa(pomocnicza.getObecnaMasa() - elem);
            pomocnicza.setZaladowanie(pomocnicza.getZaladowanie() + elem);
            licz--;
            pelny.signal();
            przeciazony.signal();

            //Thread.sleep(rand.nextInt(1000) + 700);


            System.out.println("[T" + elem + "] >> " + pomocnicza.getZaladowanie() + ", " + pomocnicza.getObecnaMasa());
/*        } catch (InterruptedException e) {
            e.printStackTrace();*/
        } finally {
            dostep.unlock();
        }
    }

    public void odjazd() {
        dostep.lock();
        try {
            pomocnicza.setZaladowanie(0);
            //Thread.sleep((rand.nextInt(50) + 1));
            System.out.println("odjazd\n");
/*        } catch (InterruptedException e) {
            e.printStackTrace();*/
        } finally {
            dostep.unlock();
        }
    }
}