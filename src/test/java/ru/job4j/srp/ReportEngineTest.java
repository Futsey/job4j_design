package ru.job4j.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.srp.ReportEngine.DATE_FORMAT;
import static ru.job4j.srp.ReportFinance.EURO;
import static ru.job4j.srp.ReportForProg.SEPARATOR;

class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenProgGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportForProg(store);
        StringBuilder expect = new StringBuilder()
                .append("<html"
                        .concat(SEPARATOR)
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
                .append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(SEPARATOR.concat("</body>").concat(SEPARATOR).concat("</head>").concat(SEPARATOR))
                .append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenFinGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportFinance(store);
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() / EURO).append(";")
                .append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Ira", now, now, 200);
        Employee worker2 = new Employee("Oleg", now, now, 130);
        Employee worker3 = new Employee("Andrew", now, now, 150);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder();
        expect.append("Name; Salary;")
                .append(SEPARATOR)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(SEPARATOR)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(SEPARATOR)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(SEPARATOR)
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(SEPARATOR);
            assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}