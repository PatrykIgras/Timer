package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label TimeField;
    public Label StartTimeLabel;
    public Label TimeToEndLabel;
    public Label EndTimeLabel;
    public Button StartButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        TimeCounter service = new TimeCounter(TimeField, TimeToEndLabel);
        service.setPeriod(Duration.seconds(1));
        service.start();

        StartButton.setStyle("-fx-background-color: linear-gradient(rgb(54,54,54) 25%, rgb(30,30,30))");

        StartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                service.setStartTime(LocalTime.now());
                StartTimeLabel.setText(dateTimeFormatter.format(LocalDateTime.now()));
                EndTimeLabel.setText(dateTimeFormatter.format(LocalDateTime.now().plusHours(8)));
            }
        });
    }
}
