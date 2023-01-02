package com.it.stopwatch.service.exporter;

import com.it.stopwatch.app.model.Run;
import com.it.stopwatch.context.ApplicationContext;
import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelExporter {

    public void export(TableView<Run> tableView, File file) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Лист1");
        HSSFRow firstRow = hssfSheet.createRow(0);

        for (int i = 0; i < tableView.getColumns().size(); i++) {
            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
        }

        for (int row = 0; row < tableView.getItems().size(); row++) {
            HSSFRow hssfRow = hssfSheet.createRow(row + 1);

            for (int col = 0; col < tableView.getColumns().size(); col++) {
                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            hssfWorkbook.write(fileOutputStream);

            fileOutputStream.close();
            hssfWorkbook.close();
        } catch (IOException e) {
            ApplicationContext.getErrorLabel().setText("Ошибка при сохранении файлав Excel");
        }
    }
}
