
package service;

import model.EmployeeProject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DateParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CSVReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVReader.class);

    private static final CSVFormat CSV_FORMAT = CSVFormat.Builder.create()
            .setAllowMissingColumnNames(true)
            .setDelimiter(',')
            .setHeader()
            .setSkipHeaderRecord(true)
            .setIgnoreEmptyLines(true)
            .get();

    public static List<EmployeeProject> load(String path) throws IOException {
        List<EmployeeProject> list = new ArrayList<>();

        try (
                BufferedReader reader = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
                CSVParser csvParser = CSVParser.parse(reader, CSV_FORMAT)
        ) {
            validateHeaders(csvParser.getHeaderNames());

            for (CSVRecord record : csvParser) {
                try {
                    EmployeeProject project = parseRecord(record);
                    if (project != null) {
                        list.add(project);
                    }
                } catch (Exception e) {
                    LOGGER.warn("Failed to parse record: {}", record, e);
                }
              }
        }

        return list;
    }

    private static void validateHeaders(List<String> headers) throws IOException {
        List<String> required = Arrays.asList("EmpID", "ProjectID", "DateFrom", "DateTo");
        if (!new HashSet<>(headers).containsAll(required)) {
            throw new IOException("CSV file is missing required headers: " + required);
        }
    }

    private static EmployeeProject parseRecord(CSVRecord record) {
        String empStr = record.get("EmpID").trim();
        if (StringUtils.isBlank(empStr) || empStr.equalsIgnoreCase("NULL"))
            return null;

        String projectStr = record.get("ProjectID").trim();
        if (StringUtils.isBlank(projectStr) || projectStr.equalsIgnoreCase("NULL"))
            return null;

        LocalDate from = DateParser.parseFreeFormat(record.get("DateFrom"));
        LocalDate to = DateParser.parseFreeFormat(record.get("DateTo"));
        if (from.isAfter(to))
            return null;

        return new EmployeeProject(Integer.parseInt(empStr), Integer.parseInt(projectStr), from, to);
    }

}