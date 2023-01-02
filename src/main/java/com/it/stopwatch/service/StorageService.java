package com.it.stopwatch.service;

import com.it.stopwatch.app.exception.StopwatchException;
import com.it.stopwatch.app.model.Run;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StorageService {

    public void saveRuns(Collection<Run> runs, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            Object[] objects = runs.toArray();
            objectOutputStream.writeObject(objects);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new StopwatchException("Не удалось сохранить файл!", e);
        }
    }

    public List<Run> loadRuns(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Object[] objects = (Object[]) objectInputStream.readObject();
            return Arrays.stream(objects)
                    .map(Run.class::cast)
                    .collect(Collectors.toList());
        } catch (ClassNotFoundException | IOException e) {
            throw new StopwatchException("Не удалось открыть файл!", e);
        }
    }
}
