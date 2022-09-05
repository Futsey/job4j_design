package ru.job4j.solid.srpocp;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);
}
