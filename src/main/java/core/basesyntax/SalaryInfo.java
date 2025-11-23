package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] tokens = entry.split(" ");
            LocalDate workDate = LocalDate.parse(tokens[0], FORMATTER);
            String employee = tokens[1];
            int hours = Integer.parseInt(tokens[2]);
            int rate = Integer.parseInt(tokens[3]);

            if (workDate.isBefore(from) || workDate.isAfter(to)) {
                continue;
            }

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(employee)) {
                    salaries[i] += hours * rate;
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return report.toString();
    }
}

