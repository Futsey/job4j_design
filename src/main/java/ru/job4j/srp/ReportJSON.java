package ru.job4j.srp;

import com.google.gson.Gson;

import java.util.function.Predicate;


public class ReportJSON implements Report {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new Gson().toJson(store.findBy(filter));
    }
}
