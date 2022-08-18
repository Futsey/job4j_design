package ru.job4j.srp;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Andrew", now, null, 120);
        store.add(worker);
        Report engine = new ReportJSON(store);
        StringBuilder expect = new StringBuilder();
        expect.append("[")
                .append("{")
                .append("\"name\":")
                .append("\"")
                .append(worker.getName())
                .append("\",")
                .append("\"hired\":")
                .append("{")
                .append("\"year\":")
                .append(worker.getHired().get(Calendar.YEAR))
                .append(",")
                .append("\"month\":")
                .append(worker.getHired().get(Calendar.MONTH))
                .append(",")
                .append("\"dayOfMonth\":")
                .append(worker.getHired().get(Calendar.DAY_OF_MONTH))
                .append(",")
                .append("\"hourOfDay\":")
                .append(worker.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",")
                .append("\"minute\":")
                .append(worker.getHired().get(Calendar.MINUTE))
                .append(",")
                .append("\"second\":")
                .append(worker.getHired().get(Calendar.SECOND))
                .append("},")
                .append("\"salary\":")
                .append(worker.getSalary())
                .append("}")
                .append("]");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenReportFormatXML() {
        MemStore store = new MemStore();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String date = dateFormat.format(calendar.getTime());
        Employee worker = new Employee("Andrew", calendar, calendar, 120);
        store.add(worker);
        Report engine = new ReportXML(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n<employees>\n")
                .append("    <employee>\n")
                .append("        <fired>")
                .append(date)
                .append("</fired>\n")
                .append("        <hired>")
                .append(date)
                .append("</hired>\n")
                .append("        <name>")
                .append(worker.getName())
                .append("</name>\n")
                .append("        <salary>")
                .append(worker.getSalary())
                .append("</salary>\n")
                .append("    </employee>")
                .append("\n</employees>\n");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}