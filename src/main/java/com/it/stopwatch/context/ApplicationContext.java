package com.it.stopwatch.context;

import com.it.stopwatch.service.RunService;
import com.it.stopwatch.service.StorageService;
import com.it.stopwatch.service.TimeService;
import com.it.stopwatch.service.converter.DurationConverter;
import com.it.stopwatch.service.exporter.ExcelExporter;
import com.it.stopwatch.service.validator.RunValidator;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ApplicationContext {

    private static final Label errorLabel;

    private static final String desktopPath;

    private static final RunService runService;
    private static final TimeService timeService;
    private static final DurationConverter durationConverter;
    private static final RunValidator runValidator;
    private static final ExcelExporter excelExporter;
    private static final StorageService storageService;

    static {
        errorLabel = new Label();
        errorLabel.setFont(new Font(14));
        errorLabel.setTextFill(Color.RED);

        desktopPath = System.getProperty("user.home") + "/Desktop";

        durationConverter = new DurationConverter();
        runValidator = new RunValidator();
        timeService = new TimeService(durationConverter);
        runService = new RunService(timeService, runValidator);
        excelExporter = new ExcelExporter();
        storageService = new StorageService();
    }

    public static Label getErrorLabel() {
        return errorLabel;
    }

    public static String getDesktopPath() {
        return desktopPath;
    }

    public static RunService getRunService() {
        return runService;
    }

    public static ExcelExporter getExcelExporter() {
        return excelExporter;
    }

    public static StorageService getStorageService() {
        return storageService;
    }
}
