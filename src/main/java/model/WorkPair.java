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

    @Override
    public String toString() {
        return emp1 + ", " + emp2 + ", " + projectId + ", " + days;
    }
}