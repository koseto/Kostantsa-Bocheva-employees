package model;

import java.time.LocalDate;

public class EmployeeProject {
    public int empId;
    public int projectId;
    public LocalDate dateFrom;
    public LocalDate dateTo;

    public EmployeeProject(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
