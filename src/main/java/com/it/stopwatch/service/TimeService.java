package com.it.stopwatch.service;

import com.it.stopwatch.app.exception.ValidationException;
import com.it.stopwatch.service.converter.DurationConverter;
import com.it.stopwatch.app.model.Run;

import java.time.Duration;

public class TimeService {

    private static final String NEGATIVE_RESULT_ERROR_MESSAGE =
            "Финальное время не может быть меньше стартового времени!";

    private final DurationConverter durationConverter;

    public TimeService(DurationConverter durationConverter) {
        this.durationConverter = durationConverter;
    }

    public Run populateResult(Run run) {
        String startString = run.getStart();
        String penaltyString = run.getPenalty();
        String finishString = run.getFinish();

        Duration start = durationConverter.convert(startString);
        Duration finish = durationConverter.convert(finishString);

        Duration result = finish.minus(start);

        if (result.isNegative()) {
            throw new ValidationException(NEGATIVE_RESULT_ERROR_MESSAGE);
        }

        if (penaltyString != null && !penaltyString.isBlank()) {
            Duration penalty = durationConverter.convert(penaltyString);
            result = result.plus(penalty);
        }

        String resultString = durationConverter.convert(result);
        run.setResult(resultString);
        return run;
    }
}
