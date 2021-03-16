package hu.nive.ujratervezes.zarovizsga.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorkHours {
    public String minWork(String file) {
        String result ="";
        List<Employee> employees = new ArrayList<>();
       Path path = Path.of(file);
       try(BufferedReader br = Files.newBufferedReader(path)) {
           String line;
           while((line=br.readLine()) != null) {
               Employee employee = createEmployee(line);
               employees.add(employee);
           }
           employees.sort(Comparator.comparing(Employee::getWorkHours));
           result = nameAndDate(employees.get(0).getName(),employees.get(0).getDate().toString());
       } catch (IOException ioe) {
           throw new IllegalStateException("Can't read file!");
       }
       return result;
    }

  
  
    private Employee createEmployee(String line) {
        String[] parts = line.split(",");
        String name = parts[0];
        int workHours = Integer.parseInt(parts[1]);
        LocalDate date = LocalDate.parse(parts[2]);
        return new Employee(name,workHours,date);
    }

    private String nameAndDate(String name,String date) {
        return name +": " + date;
    }
}


