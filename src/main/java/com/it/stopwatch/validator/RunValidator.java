package com.it.stopwatch.validator;

import com.it.stopwatch.app.exception.ValidationException;
import com.it.stopwatch.app.model.Run;

import java.util.regex.Pattern;

public class RunValidator {

    private static final String MM_SS_REGEX = "^[0-9]?\\d:[0-5]\\d$";
    private static final Pattern mmSSPattern = Pattern.compile(MM_SS_REGEX);

    private static final String EMPTY_FIELD_ERROR_MESSAGE = "Заполните '%s'. Поле не может быть пустым!";
    private static final String DURATION_FORMAT_OR_EMPTY_ERROR_MESSAGE =
            "Поле '%s' пустое или заполнено неверно. Правильный формат 02:33";
    private static final String DURATION_FORMAT_ERROR_MESSAGE =
            "Поле '%s' пустое или заполнено неверно. Правильный формат 02:33";
    private static final String NAME_LABEL = "ФИО";
    private static final String TEAM_LABEL = "Команда";
    private static final String START_LABEL = "Старт время";
    private static final String FINISH_LABEL = "Финиш время";
    private static final String PENALTY_LABEL = "Штрафное время";

    public void validate(Run run) {
        if (run.getName() == null || run.getName().isBlank()) {
            throw new ValidationException(NAME_LABEL, EMPTY_FIELD_ERROR_MESSAGE);
        }
        if (run.getTeam() == null || run.getTeam().isBlank()) {
            throw new ValidationException(TEAM_LABEL, EMPTY_FIELD_ERROR_MESSAGE);
        }


        if (run.getStart() == null || !mmSSPattern.matcher(run.getStart()).matches()) {
            throw new ValidationException(START_LABEL, DURATION_FORMAT_OR_EMPTY_ERROR_MESSAGE);
        }
        if (run.getFinish() == null || !mmSSPattern.matcher(run.getFinish()).matches()) {
            throw new ValidationException(FINISH_LABEL, DURATION_FORMAT_OR_EMPTY_ERROR_MESSAGE);
        }
        if (run.getPenalty() != null && !run.getPenalty().isBlank()) {
            if (!mmSSPattern.matcher(run.getPenalty()).matches()) {
                throw new ValidationException(PENALTY_LABEL, DURATION_FORMAT_ERROR_MESSAGE);
            }
        }
    }
}
