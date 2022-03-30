package com.example.transportcegiel;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Worker extends Thread {
    private final int number;
    public static volatile boolean[] selection = new boolean[]{false, false, false};
    public static volatile Integer[] numberArray = new Integer[]{0, 0, 0};
    public int weight;
    public int truckCapacity;
    boolean quit = false;
    Random random = new Random();
    Buffer buffer;
    Parameters parameters;
    ConveyorBelt conveyorBelt;

    TranslateTransition translateTransition;
    HelloController helloController;
    StartApplication startApplication;

    public Worker(int number, int truckCapacity, Buffer buffer, Parameters parameters, HelloController helloController, StartApplication startApplication) {
        super("P" + number + 1);
        this.number = number;
        this.truckCapacity = truckCapacity;
        this.buffer = buffer;
        this.parameters = parameters;
        this.helloController = helloController;
        this.startApplication = startApplication;
    }

    public void animation(int elem) {
        translateTransition = new TranslateTransition();
        if (elem == 1) {
            translateTransition.setToX(91);
            translateTransition.setToY(124);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.worker1);
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

            buffer.insertToTruck(elem);

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.worker1);
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
        }

        if (elem == 2) {
            translateTransition.setToX(91);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.worker2);
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

            buffer.insertToTruck(elem);

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.worker2);
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
        }

        if (elem == 3) {
            translateTransition.setToX(91);
            translateTransition.setToY(-124);
            translateTransition.setDuration(Duration.millis(550));

            buffer.insertToTruck(elem);

            translateTransition.setNode(helloController.worker3);
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

            translateTransition.setDelay(Duration.millis(100));
            translateTransition.setToX(0);
            translateTransition.setToY(0);
            translateTransition.setDuration(Duration.millis(550));
            translateTransition.setNode(helloController.worker3);
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
        }
    }

    public void run() {
        while (!quit) {
            translateTransition = new TranslateTransition();
            if (number == 0) {
                translateTransition.setToX(-91);
                translateTransition.setToY(124);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));
                translateTransition.setNode(helloController.worker1);
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

                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));
                translateTransition.setNode(helloController.worker1);
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
            }

            if (number == 1) {
                translateTransition.setToX(-91);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));
                translateTransition.setNode(helloController.worker2);
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


                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));

                translateTransition.setNode(helloController.worker2);
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
            }

            if (number == 2) {
                translateTransition.setToX(-91);
                translateTransition.setToY(-124);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));


                translateTransition.setNode(helloController.worker3);
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
                translateTransition.setDelay(Duration.millis(1000));
                translateTransition.setToX(0);
                translateTransition.setToY(0);
                translateTransition.setDuration(Duration.millis(random.nextInt(1000) + 500));
                translateTransition.setNode(helloController.worker3);
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
            }

            if (parameters.isFree()) {

                selection[number] = true;
                numberArray[number] = Collections.max(Arrays.asList(numberArray)) + 1;
                selection[number] = false;

                int j;
                for (j = 0; j < numberArray.length; ++j) {
                    while (selection[j]) {
                    }

                    while (numberArray[j] != 0 && numberArray[j] < numberArray[number]) {
                    }

                    if (Objects.equals(numberArray[j], numberArray[number])) {
                        while (numberArray[j] != 0 && j < number) {
                        }
                    }
                }
                weight = number + 1;
                if (truckCapacity - (parameters.getTruckLoad() + parameters.getCurrentCapacity()) < 3) {
                    if (truckCapacity - (parameters.getTruckLoad() + parameters.getCurrentCapacity()) == 2) {
                        if (weight == 2) {
                            animation(2);
                            conveyorBelt = new ConveyorBelt(weight, buffer, parameters, helloController, startApplication);
                            conveyorBelt.start();
                        }
                    }
                    if (truckCapacity - (parameters.getTruckLoad() + parameters.getCurrentCapacity()) == 1) {
                        if (weight == 1) {
                            animation(1);
                            conveyorBelt = new ConveyorBelt(weight, buffer, parameters, helloController, startApplication);
                            conveyorBelt.start();
                        }
                    }
                } else {
                    animation(weight);
                    conveyorBelt = new ConveyorBelt(weight, buffer, parameters, helloController, startApplication);
                    conveyorBelt.start();
                }
                numberArray[number] = 0;
            }
        }
    }
}