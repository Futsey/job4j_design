package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportForProg implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private static final String SEPARATOR = System.lineSeparator();

    private Store store;

    public ReportForProg(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<html" + SEPARATOR
                        + "<head>" + SEPARATOR
                        + "<meta charset=\"UTF-8\">" + SEPARATOR
                        + "<meta http-equiv=\"X-RU-Compatible\" content=\"IE=edge\">" + SEPARATOR
                        + "<meta name=\"reportHTML\" content=\"width=device-width, initial-scale=1.0\">" + SEPARATOR
                        + "<link rel=\"icon\" href=\"past here your logo path\" type=\"image/x-icon\">" + SEPARATOR
                        + "<body>" + SEPARATOR)
                .append("Name; Hired; Fired; Salary;");
        for (Employee worker : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(worker.getName()).append(";")
                    .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                    .append(worker.getSalary()).append(";");
        }
            text.append(SEPARATOR + "</body>" + SEPARATOR + "</head>" + SEPARATOR)
                .append(System.lineSeparator());
        return text.toString();
    }
}
