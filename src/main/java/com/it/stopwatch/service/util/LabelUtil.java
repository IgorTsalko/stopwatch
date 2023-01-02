package com.it.stopwatch.service.util;

import javafx.scene.control.Label;

public class LabelUtil {

    public static void clearLabel(Label label) {
        String errorMessage = label.getText();
        if (errorMessage != null && !errorMessage.isBlank()) {
            label.setText(null);
        }
    }
}
