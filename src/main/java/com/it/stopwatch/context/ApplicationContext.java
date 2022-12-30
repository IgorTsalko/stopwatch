package com.it.stopwatch.context;

import com.it.stopwatch.service.RunService;
import com.it.stopwatch.service.TimeService;
import com.it.stopwatch.service.converter.DurationConverter;
import com.it.stopwatch.validator.RunValidator;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ApplicationContext {

    private final static Label errorLabel;

    private final static RunService runService;
    private final static TimeService timeService;
    private final static DurationConverter durationConverter;
    private final static RunValidator runValidator;

    static {
        errorLabel = new Label();
        errorLabel.setFont(new Font(14));
        errorLabel.setTextFill(Color.RED);

        durationConverter = new DurationConverter();
        runValidator = new RunValidator();
        timeService = new TimeService(durationConverter);
        runService = new RunService(timeService, runValidator);
    }

    public static Label getErrorLabel() {
        return errorLabel;
    }

    public static RunService getRunService() {
        return runService;
    }
}
