package com.example.transportcegiel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    public Group ciezarowka;

    @FXML
    public TextField ladownoscTF;

    @FXML
    public TextField maxIloscTF;

    @FXML
    public TextField udzwigTF;

    @FXML
    public Group pracownik1;

    @FXML
    public Group pracownik2;

    @FXML
    public Group pracownik3;

    @FXML
    private Button startButton;

    @FXML
    public ProgressBar pasekLadowania;

    StartApplication startApplication = new StartApplication();

    @FXML
    void startSimulation(ActionEvent event) {
        startApplication.StartSimulation(this);
    }

}
