package service;

import model.EmployeeProject;
import model.WorkPair;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PairCalculatorTest {

    @Test
    public void testCalculateOverlap() {
        List<EmployeeProject> list = List.of(
                new EmployeeProject(143, 10, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1)),
                new EmployeeProject(218, 10, LocalDate.of(2023, 1, 15), LocalDate.of(2023, 2, 15))
        );

        List<WorkPair> result = PairCalculator.calculatePairs(list);
        assertEquals(1, result.size());
        assertEquals(143, result.getFirst().getEmp1());
        assertEquals(218, result.getFirst().getEmp2());
    }

    @Test
    public void testNoOverlap() {
        List<EmployeeProject> list = List.of(
                new EmployeeProject(143, 10, LocalDate.of(2022, 1, 1), LocalDate.of(2022, 1, 10)),
                new EmployeeProject(218, 10, LocalDate.of(2022, 2, 1), LocalDate.of(2022, 2, 10))
        );

        List<WorkPair> result = PairCalculator.calculatePairs(list);
        assertTrue(result.isEmpty());
    }
}
