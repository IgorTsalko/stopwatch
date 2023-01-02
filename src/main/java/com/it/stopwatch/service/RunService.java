package com.it.stopwatch.service;

import com.it.stopwatch.app.model.Run;
import com.it.stopwatch.service.validator.RunValidator;
import javafx.collections.ObservableList;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RunService {

    private final TimeService timeService;
    private final RunValidator runValidator;

    public RunService(TimeService timeService, RunValidator runValidator) {
        this.timeService = timeService;
        this.runValidator = runValidator;
    }

    public Run calculateRun(Run run) {
        runValidator.validate(run);
        return timeService.populateResult(run);
    }

    public void populatePlaces(ObservableList<Run> runs) {
        List<Run> sortedByResult = runs.stream()
                .sorted(Comparator.comparing(Run::getResult))
                .collect(Collectors.toList());

        int place = 1;
        String previousResult = null;
        for (Run run : sortedByResult) {
            String result = run.getResult();
            if (previousResult != null && !Objects.equals(result, previousResult)) {
                run.setPlace(++place);
            } else {
                run.setPlace(place);
            }
            previousResult = result;
        }
    }
}
