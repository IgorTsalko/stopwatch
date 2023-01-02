package com.it.stopwatch.app.controller;

import com.it.stopwatch.app.exception.ValidationException;
import com.it.stopwatch.app.handler.CellEditHandler;
import com.it.stopwatch.app.handler.TimeCellEditHandler;
import com.it.stopwatch.app.model.Run;
import com.it.stopwatch.context.ApplicationContext;
import com.it.stopwatch.service.RunService;
import com.it.stopwatch.service.exporter.ExcelExporter;
import com.it.stopwatch.service.util.LabelUtil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    private final Label errorLabel;

    @FXML
    private StackPane stackPane;

    @FXML
    private TableView<Run> tableView;

    @FXML
    private TableColumn<Run, String> numberColumn;
    @FXML
    private TableColumn<Run, String> nameColumn;
    @FXML
    private TableColumn<Run, String> teamColumn;
    @FXML
    private TableColumn<Run, String> startColumn;
    @FXML
    private TableColumn<Run, String> finishColumn;
    @FXML
    private TableColumn<Run, String> penaltyColumn;
    @FXML
    private TableColumn<Run, String> resultColumn;
    @FXML
    private TableColumn<Run, String> placeColumn;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField teamInput;
    @FXML
    private TextField startInput;
    @FXML
    private TextField finishInput;
    @FXML
    private TextField penaltyInput;

    private final RunService runService;
    private final ExcelExporter excelExporter;

    public TableController() {
        errorLabel = ApplicationContext.getErrorLabel();

        this.runService = ApplicationContext.getRunService();
        this.excelExporter = ApplicationContext.getExcelExporter();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackPane.getChildren().add(ApplicationContext.getErrorLabel());

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setOnEditCommit(new CellEditHandler(Run::setName));

        teamColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        teamColumn.setOnEditCommit(new CellEditHandler(Run::setTeam));

        startColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        startColumn.setOnEditCommit(new TimeCellEditHandler(Run::setStart));

        finishColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        finishColumn.setCellValueFactory(new PropertyValueFactory<>("finish"));
        finishColumn.setOnEditCommit(new TimeCellEditHandler(Run::setFinish));

        penaltyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        penaltyColumn.setCellValueFactory(new PropertyValueFactory<>("penalty"));
        penaltyColumn.setOnEditCommit(new TimeCellEditHandler(Run::setPenalty));

        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
    }

    @FXML
    void addRun(ActionEvent event) {
        ObservableList<Run> runs = tableView.getItems();
        int size = runs.size();

        String name = nameInput.getText();
        String team = teamInput.getText();
        String start = startInput.getText();
        String finish = finishInput.getText();
        String penalty = penaltyInput.getText();

        try {
            Run newRun = new Run();
            newRun.setNumber(++size);
            newRun.setName(name);
            newRun.setTeam(team);
            newRun.setStart(start);
            newRun.setFinish(finish);
            newRun.setPenalty(penalty);

            newRun = runService.calculateRun(newRun);

            runs.add(newRun);
            runService.populatePlaces(runs);
            tableView.setItems(runs);
            tableView.refresh();

            nameInput.clear();
            teamInput.clear();
            startInput.clear();
            finishInput.clear();
            penaltyInput.clear();

            LabelUtil.clearLabel(errorLabel);
        } catch (ValidationException e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    void removeRun(ActionEvent event) {
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        ObservableList<Run> runs = tableView.getItems();
        runs.remove(selectedIndex);
        runService.populatePlaces(runs);
        tableView.refresh();
    }

    @FXML
    void exportTable(ActionEvent event) {
        if (!tableView.getItems().isEmpty()) {
            FileChooser fileChooser = new FileChooser();

            String desktopPath = System.getProperty("user.home") + "/Desktop";
            fileChooser.setInitialDirectory(new File(desktopPath));
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter("Excel file", "*.xls", "*.xlsx"));

            Window stage = ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            File selectedDirectory = fileChooser.showSaveDialog(stage);
            excelExporter.export(tableView, selectedDirectory);

            LabelUtil.clearLabel(errorLabel);
        } else {
            errorLabel.setText("Таблица пустая. Добавьте записи чтобы сохранить!");
        }
    }
}
