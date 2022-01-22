package com.example.transportcegiel;

public class Pomocnicza {
    private int zaladowanie;
    private int obecnaMasa;
    private boolean czy_wolne;


    //private int maxIlosc;
    public Pomocnicza(int zaladowanie, int obecnaMasa) {
        this.zaladowanie = zaladowanie;
        this.obecnaMasa = obecnaMasa;
        this.czy_wolne = true;
    }
    public synchronized boolean isCzy_wolne() {
        return czy_wolne;
    }

    public synchronized void setCzy_wolne(boolean c) {
        czy_wolne = c;
    }
    public synchronized void setZaladowanie(int z) {
        zaladowanie = z;
    }

    public synchronized void setObecnaMasa(int o) {
        obecnaMasa = o;
    }

    public synchronized int getZaladowanie() {
        return zaladowanie;
    }

    public synchronized int getObecnaMasa() {
        return obecnaMasa;
    }
}
