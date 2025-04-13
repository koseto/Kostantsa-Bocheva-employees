package service;

import model.EmployeeProject;
import model.WorkPair;

import java.time.LocalDate;
import java.util.*;

public class PairCalculator {
    public static List<WorkPair> calculatePairs(List<EmployeeProject> data) {
        List<WorkPair> pairDurations = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            for (int j = i + 1; j < data.size(); j++) {
                EmployeeProject e1 = data.get(i);
                EmployeeProject e2 = data.get(j);

                if (e1.projectId != e2.projectId) continue;

                LocalDate start = Collections.max(List.of(e1.dateFrom, e2.dateFrom));
                LocalDate end = Collections.min(List.of(e1.dateTo, e2.dateTo));

                if (start.isAfter(end)) continue;

                int days = (int) (end.toEpochDay() - start.toEpochDay());
                if (days <= 0) continue;

                pairDurations.add(new WorkPair(e1.empId, e2.empId, e1.projectId, days));
            }
        }

        return pairDurations;
    }
}
