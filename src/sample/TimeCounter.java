package sample;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class TimeCounter extends ScheduledService<Void> {
    private Label label;
    private Label timeToEndLabel;
    private LocalTime startTime;

    public TimeCounter(Label label, Label timeToEndLabel) {
        this.label = label;
        this.timeToEndLabel = timeToEndLabel;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    LocalDateTime eightHour = LocalDateTime.of(2019, 12, 19, 8, 0);
    LocalDateTime timeToPrint = eightHour;

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime time = LocalDateTime.now();
                    label.setText(dateTimeFormatter.format(time));

                    if (startTime != null) {
                        LocalDateTime timeZero = LocalDateTime.of(2019, 12, 19, 0, 0);

                        do {timeToPrint = timeToPrint.minusSeconds(1);
                        } while (timeToPrint.equals(timeZero));

                        timeToEndLabel.setText(dateTimeFormatter.format(timeToPrint));
                    }
                });
                return null;
            }
        };
    }
}
