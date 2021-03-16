package hu.nive.ujratervezes.zarovizsga.workhours;

import java.time.LocalDate;

public class Employee {
    private String name;
    private int workHours;
    private LocalDate date;

    public Employee(String name, int workHours, LocalDate date) {
        this.name = name;
        this.workHours = workHours;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getWorkHours() {
        return workHours;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", workHours=" + workHours +
                ", date=" + date +
                '}';
    }
}
