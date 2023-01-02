package com.it.stopwatch.context;

import com.it.stopwatch.service.RunService;
import com.it.stopwatch.service.TimeService;
import com.it.stopwatch.service.converter.DurationConverter;
import com.it.stopwatch.service.exporter.ExcelExporter;
import com.it.stopwatch.service.validator.RunValidator;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ApplicationContext {

    private static final Label errorLabel;

    private static final RunService runService;
    private static final TimeService timeService;
    private static final DurationConverter durationConverter;
    private static final RunValidator runValidator;
    private static final ExcelExporter excelExporter;

    static {
        errorLabel = new Label();
        errorLabel.setFont(new Font(14));
        errorLabel.setTextFill(Color.RED);

        durationConverter = new DurationConverter();
        runValidator = new RunValidator();
        timeService = new TimeService(durationConverter);
        runService = new RunService(timeService, runValidator);
        excelExporter = new ExcelExporter();
    }

    public static Label getErrorLabel() {
        return errorLabel;
    }

    public static RunService getRunService() {
        return runService;
    }

    public static ExcelExporter getExcelExporter() {
        return excelExporter;
    }
}
