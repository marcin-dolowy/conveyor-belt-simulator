package com.example.transportcegiel;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    public Group truck;

    @FXML
    public TextField truckCapacityTF;

    @FXML
    public TextField maxBrickAmountTF;

    @FXML
    public TextField conveyorBeltCapacity;

    @FXML
    public Group worker1;

    @FXML
    public Group worker2;

    @FXML
    public Group worker3;

    @FXML
    public ProgressBar loadingBar;

    StartApplication startApplication = new StartApplication();

    @FXML
    void startSimulation() {
        startApplication.StartSimulation(this);
    }

}
