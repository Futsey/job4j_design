package ru.job4j.solid.srpocp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR  implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        text.append("Name; Salary;")
                .append(ReportForProg.SEPARATOR);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(ReportForProg.SEPARATOR);
        }
        return text.toString();
    }
}
