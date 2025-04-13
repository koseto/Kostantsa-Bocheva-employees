package service;

import model.EmployeeProject;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Test
    public void testLoadValidCSV() throws IOException {
        String testPath = "test_data.csv";
        FileWriter writer = new FileWriter(testPath);
        writer.write("ProjectID,EmpID,DateFrom,DateTo\n");
        writer.write("10,143,2023-01-01,2023-02-01\n");
        writer.write("10,218,2023-01-15,2023-03-01\n");
        writer.close();

        List<EmployeeProject> list = CSVReader.load(testPath);
        assertEquals(2, list.size());
        assertEquals(143, list.getFirst().empId);
        assertEquals(10, list.getFirst().projectId);
        assertEquals(LocalDate.of(2023, 1, 1), list.getFirst().dateFrom);
    }

    @Test
    public void testLoadInValidCSV() throws IOException {
        String testPath = "test_data.csv";
        FileWriter writer = new FileWriter(testPath);
        writer.write("EmpID,ProjectID,DateTo\n");
        writer.write("143,10,2023-02-01\n");
        writer.write("218,10,2023-03-01\n");
        writer.close();

        assertThrows(IOException.class, () -> CSVReader.load(testPath));
    }

    @Test
    void testInvalidDates() throws IOException {
        String testPath = "test_data.csv";
        FileWriter writer = new FileWriter(testPath);
        writer.write("EmpID,ProjectID,DateFrom,DateTo\n");
        writer.write("143,10,invalid-date,2023-02-01\n");
        writer.write("218,10,2023-01-15,2023-03-01\n");
        writer.close();

        List<EmployeeProject> list = CSVReader.load(testPath);
        assertEquals(1, list.size());
    }

    @Test
    void testInvalidProjectId() throws IOException {
        String testPath = "test_data.csv";
        FileWriter writer = new FileWriter(testPath);
        writer.write("EmpID,ProjectID,DateFrom,DateTo\n");
        writer.write("143,,2023-01-16,2023-02-01\n");
        writer.write(",10,2023-01-15,2023-03-01\n");
        writer.write("5,10,2023-01-15,2023-03-01\n");
        writer.write("8,10,2023-01-20,2023-03-01\n");
        writer.close();

        List<EmployeeProject> list = CSVReader.load(testPath);
        assertEquals(2, list.size());
    }

}
