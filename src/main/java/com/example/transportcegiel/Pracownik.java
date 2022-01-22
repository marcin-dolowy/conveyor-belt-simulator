package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Pracownik extends Thread {
    private int nr;
    public static volatile boolean[] wybieranie = new boolean[]{false, false, false};
    public static volatile Integer[] numer = new Integer[]{0, 0, 0};
    public int masa;
    public int ladownosc;
    boolean koniec = false;
    Random rand = new Random();
    Buffer bufor;
    Pomocnicza pomocnicza;
    Tasma tasma;

    TranslateTransition translateTransition;
    HelloController helloController;
    StartApplication startApplication;

    public Pracownik(int nr, int ladownosc, Buffer bufor, Pomocnicza pomocnicza, HelloController helloController, StartApplication startApplication) {
        super("P" + nr + 1);
        this.nr = nr;
        this.ladownosc = ladownosc;
        this.bufor = bufor;
        this.pomocnicza = pomocnicza;
        this.helloController = helloController;
        this.startApplication = startApplication;
    }

    public void animacja(int elem) {
        translateTransition = new TranslateTransition();
        if (elem == 1) {
            translateTransition.setToX(91);
            translateTransition.setToY(124);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.pracownik1);
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

            bufor.wstaw(elem);

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.pracownik1);
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
        }

        if (elem == 2) {
            translateTransition.setToX(91);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.pracownik2);
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

            bufor.wstaw(elem);

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.pracownik2);
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
        }

        if (elem == 3) {
            translateTransition.setToX(91);
            translateTransition.setToY(-124);
            translateTransition.setDuration(Duration.millis(550));

            bufor.wstaw(elem);

            translateTransition.setNode(helloController.pracownik3);
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

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.pracownik3);
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
        }
    }

    public void run() {
        while (!koniec) {
            translateTransition = new TranslateTransition();
            if (nr == 0) {
                translateTransition.setToX(-91);
                translateTransition.setToY(124);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));
                translateTransition.setNode(helloController.pracownik1);
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


                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));
                translateTransition.setNode(helloController.pracownik1);
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

            }

            if (nr == 1) {
                translateTransition.setToX(-91);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));
                translateTransition.setNode(helloController.pracownik2);
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


                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));

                translateTransition.setNode(helloController.pracownik2);
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
            }

            if (nr == 2) {
                translateTransition.setToX(-91);
                translateTransition.setToY(-124);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));


                translateTransition.setNode(helloController.pracownik3);
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
                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(rand.nextInt(1000) + 500));
                translateTransition.setNode(helloController.pracownik3);
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
            }

            if (pomocnicza.isCzy_wolne()) {

                wybieranie[nr] = true;
                numer[nr] = Collections.max(Arrays.asList(numer)) + 1;
                wybieranie[nr] = false;

                int j;
                for (j = 0; j < numer.length; ++j) {
                    while (wybieranie[j]) {
                    }

                    while (numer[j] != 0 && numer[j] < numer[nr]) {
                    }

                    if (Objects.equals(numer[j], numer[nr])) {
                        while (numer[j] != 0 && j < nr) {
                        }
                    }
                }
                masa = nr + 1;
                if (ladownosc - (pomocnicza.getZaladowanie() + pomocnicza.getObecnaMasa()) < 3) {
                    if (ladownosc - (pomocnicza.getZaladowanie() + pomocnicza.getObecnaMasa()) == 2) {
                        if (masa == 2) {
                            animacja(2);
                            tasma = new Tasma(masa, bufor, pomocnicza, helloController, startApplication);
                            tasma.start();
                        }
                    }
                    if (ladownosc - (pomocnicza.getZaladowanie() + pomocnicza.getObecnaMasa()) == 1) {
                        if (masa == 1) {
                            animacja(1);
                            tasma = new Tasma(masa, bufor, pomocnicza, helloController, startApplication);
                            tasma.start();
                        }
                    }
                } else {
                    animacja(masa);
                    tasma = new Tasma(masa, bufor, pomocnicza, helloController, startApplication);
                    tasma.start();
                }
                numer[nr] = 0;
            }
        }
    }
}

