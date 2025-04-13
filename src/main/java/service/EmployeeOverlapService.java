package service;

import model.EmployeeProject;
import model.WorkPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class EmployeeOverlapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeOverlapService.class);

    public static void findPairsThatWorkedTogether(String filePath) throws IOException {

        List<EmployeeProject> data = CSVReader.load(filePath);
        List<WorkPair> pairs = PairCalculator.calculatePairs(data);

        if (pairs.isEmpty()) {
            System.out.println("No pairs worked together on common projects.");
            return;
        }

        WorkPair bestPair = null;
        int maxDays = 0;
        for (WorkPair pair : pairs) {
            if (pair.getDays() > maxDays) {
                maxDays = pair.getDays();
                bestPair = pair;
            }
        }

        if (Objects.nonNull(bestPair)) {
            System.out.printf("Longest working pair first employee id: %d, second employee id: %d, worked days %d%n", bestPair.getEmp1(), bestPair.getEmp2(), maxDays);
            System.out.printf("Project they worked together on: %d%n", bestPair.getProjectId());
        } else {
            System.out.println("No overlapping project work found.");
        }
    }
}