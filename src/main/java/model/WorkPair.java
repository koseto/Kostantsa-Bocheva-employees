package model;

public class WorkPair {
    public int emp1;
    public int emp2;
    public int projectId;
    public int days;

    public WorkPair(int emp1, int emp2, int projectId, int days) {
        this.emp1 = emp1;
        this.emp2 = emp2;
        this.projectId = projectId;
        this.days = days;
    }

    public int getEmp1() {
        return emp1;
    }

    public void setEmp1(int emp1) {
        this.emp1 = emp1;
    }

    public int getEmp2() {
        return emp2;
    }

    public void setEmp2(int emp2) {
        this.emp2 = emp2;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return emp1 + ", " + emp2 + ", " + projectId + ", " + days;
    }
}