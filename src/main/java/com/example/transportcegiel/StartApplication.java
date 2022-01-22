package com.example.transportcegiel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StartApplication {
    int ladownosc = 0;
    int udzwig = 0;
    int maxNaTasmie = 0;

    public void StartSimulation(HelloController helloController) {
        try (InputStream inputStream = StartApplication.class.getClassLoader().getResourceAsStream("data.properties")) {
            Properties properties = new Properties();
            if (inputStream == null) {
                System.out.println("Nie znaleziono pliku");
            } else {
                properties.load(inputStream);
                ladownosc = Integer.parseInt(properties.getProperty("ladownosc"));
                udzwig = Integer.parseInt(properties.getProperty("udzwig"));
                maxNaTasmie = Integer.parseInt(properties.getProperty("maxNaTasmie"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String StringLadownosc = helloController.ladownoscTF.getText();
        String StringUdzwig = helloController.udzwigTF.getText();
        String StringMaxNaTasmie = helloController.maxIloscTF.getText();

        int ladownoscTemp;
        int udzwigTemp;
        int maxNaTasmieTemp;

        if (!StringLadownosc.isEmpty()) {
            ladownoscTemp = Integer.parseInt(StringLadownosc);
        } else {
            ladownoscTemp = ladownosc;
            helloController.ladownoscTF.setText(ladownoscTemp + "");
        }

        if (!StringLadownosc.isEmpty()) {
            udzwigTemp = Integer.parseInt(StringUdzwig);

        } else {
            udzwigTemp = udzwig;
            helloController.udzwigTF.setText(Integer.toString(udzwigTemp));
        }
        if (!StringLadownosc.isEmpty()) {
            maxNaTasmieTemp = Integer.parseInt(StringMaxNaTasmie);

        } else {
            maxNaTasmieTemp = maxNaTasmie;
            helloController.maxIloscTF.setText(Integer.toString(maxNaTasmieTemp));
        }

        int iloscPracownikow = 3;
        Pracownik[] pracownicy = new Pracownik[iloscPracownikow];
        Pomocnicza pomocnicza = new Pomocnicza(0, 0);
        Buffer buf = new Buffer(maxNaTasmieTemp, 0, udzwigTemp, pomocnicza, helloController);
        Ciezarowka ciezarowka = new Ciezarowka(ladownoscTemp, buf, pomocnicza, helloController);

        for (int i = 0; i < iloscPracownikow; i++) {
            Pracownik pracownik = new Pracownik(i, ladownoscTemp, buf, pomocnicza, helloController, this);
            pracownicy[i] = pracownik;
        }

        ciezarowka.start();

        for (int i = 0; i < iloscPracownikow; i++) {
            pracownicy[i].start();
        }
        this.ladownosc = ladownoscTemp;
    }

    public int getLadownosc() {
        return ladownosc;
    }
}
