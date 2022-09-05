package ru.job4j.solid.srpocp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportForProg implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();

    private Store store;

    public ReportForProg(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<html".concat(SEPARATOR)
                        .concat("<head>")
                        .concat(SEPARATOR)
                        .concat("<meta charset=\"UTF-8\">")
                        .concat(SEPARATOR)
                        .concat("<meta http-equiv=\"X-RU-Compatible\" content=\"IE=edge\">")
                        .concat(SEPARATOR)
                        .concat("<meta name=\"reportHTML\" content=\"width=device-width, initial-scale=1.0\">")
                        .concat(SEPARATOR)
                        .concat("<link rel=\"icon\" href=\"past here your logo path\" type=\"image/x-icon\">")
                        .concat(SEPARATOR)
                        .concat("<body>")
                        .concat(SEPARATOR))
                .append("Name; Hired; Fired; Salary;");
        for (Employee worker : store.findBy(filter)) {
            text.append(SEPARATOR)
                    .append(worker.getName()).append(";")
                    .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                    .append(worker.getSalary()).append(";");
        }
            text.append(SEPARATOR.concat("</body>").concat(SEPARATOR).concat("</head>").concat(SEPARATOR))
                .append(SEPARATOR);
        return text.toString();
    }
}
