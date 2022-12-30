package com.it.stopwatch.service.converter;

import java.time.Duration;

public class DurationConverter {

    public Duration convert(String duration) {
        String[] minutesSeconds = duration.split(":");

        String minutesString = minutesSeconds[0];
        String secondsString = minutesSeconds[1];
        minutesString = minutesString.replaceAll("^0*", "");
        secondsString = secondsString.replaceAll("^0*", "");
        long minutes = minutesString.isBlank() ? 0 : Long.parseLong(minutesString);
        long seconds = secondsString.isBlank() ? 0 : Long.parseLong(secondsString);

        return Duration.ofMinutes(minutes).plusSeconds(seconds);
    }

    public String convert(Duration duration) {
        long minutes = duration.toMinutes();
        long seconds = duration.toSeconds() % 60;
        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);
        return String.format("%s:%s", minutesString, secondsString);
    }
}
