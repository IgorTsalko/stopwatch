package com.it.stopwatch.app.handler;

import com.it.stopwatch.app.model.Run;
import com.it.stopwatch.context.ApplicationContext;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.function.BiConsumer;

public class CellEditHandler implements EventHandler<TableColumn.CellEditEvent<Run, String>> {

    final Label errorLabel;
    final BiConsumer<Run, String> newValueSetter;

    public CellEditHandler(BiConsumer<Run, String> newValueSetter) {
        this.errorLabel = ApplicationContext.getErrorLabel();
        this.newValueSetter = newValueSetter;
    }

    @Override
    public void handle(TableColumn.CellEditEvent<Run, String> event) {
        Run updatedRun = setNewValue(event);
        addEditedRun(updatedRun, event);
        updateTableView(event);
    }

    Run setNewValue(TableColumn.CellEditEvent<Run, String> event) {
        TableView<Run> tableView = event.getTableView();
        ObservableList<Run> runs = tableView.getItems();
        Run run = runs.get(event.getTablePosition().getRow());
        Run copyRun = new Run(run);
        String newValue = event.getNewValue();
        newValueSetter.accept(copyRun, newValue);
        return copyRun;
    }

    void addEditedRun(Run run, TableColumn.CellEditEvent<Run, String> event) {
        ObservableList<Run> runs = event.getTableView().getItems();
        int eventRowNumber = event.getTablePosition().getRow();
        runs.remove(eventRowNumber);
        runs.add(run);
    }

    void updateTableView(TableColumn.CellEditEvent<Run, String> event) {
        event.getTableView().refresh();
    }
}
