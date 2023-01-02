package com.it.stopwatch.app.model;

import java.io.Serializable;
import java.util.Objects;

public class Run implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer number;
    private String name;
    private String team;
    private String start;
    private String finish;
    private String penalty;
    private String result;
    private Integer place;

    public Run() {
    }

    public Run(Run run) {
        this.number = run.getNumber();
        this.name = run.getName();
        this.team = run.getTeam();
        this.start = run.getStart();
        this.finish = run.getFinish();
        this.penalty = run.getPenalty();
        this.result = run.getResult();
        this.place = run.getPlace();
    }

    public Run(Integer number,
               String name,
               String team,
               String start,
               String finish,
               String penalty,
               String result,
               Integer place) {
        this.number = number;
        this.name = name;
        this.team = team;
        this.start = start;
        this.finish = finish;
        this.penalty = penalty;
        this.result = result;
        this.place = place;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return Objects.equals(number, run.number)
                && Objects.equals(name, run.name)
                && Objects.equals(team, run.team)
                && Objects.equals(start, run.start)
                && Objects.equals(finish, run.finish)
                && Objects.equals(penalty, run.penalty)
                && Objects.equals(result, run.result)
                && Objects.equals(place, run.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, team, start, finish, penalty, result, place);
    }

    @Override
    public String toString() {
        return "Run{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                ", penalty='" + penalty + '\'' +
                ", result='" + result + '\'' +
                ", place=" + place +
                '}';
    }
}
