package com.it.stopwatch.app.handler;

import com.it.stopwatch.app.exception.ValidationException;
import com.it.stopwatch.app.model.Run;
import com.it.stopwatch.context.ApplicationContext;
import com.it.stopwatch.service.RunService;
import com.it.stopwatch.service.util.LabelUtil;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.function.BiConsumer;

public class TimeCellEditHandler extends CellEditHandler {

    private final RunService runService;

    public TimeCellEditHandler(BiConsumer<Run, String> newValueSetter) {
        super(newValueSetter);
        this.runService = ApplicationContext.getRunService();
    }

    @Override
    public void handle(TableColumn.CellEditEvent<Run, String> event) {
        try {
            Run run = setNewValue(event);
            runService.calculateRun(run);

            ObservableList<Run> runs = event.getTableView().getItems();
            runService.populatePlaces(runs);

            addEditedRun(run, event);
            updateTableView(event);
            LabelUtil.clearLabel(errorLabel);
        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
            event.getTableView().refresh();
        }
    }
}
