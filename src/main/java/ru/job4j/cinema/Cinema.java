package ru.job4j.cinema;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    Session find(Predicate<Session> filter);

    List<Session> findAllFreePlaces(Predicate<Session> filter);

    List<Session> findAllBoughtPlaces(Predicate<Session> filter);

    List<Session> findAllReservedPlaces(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    Ticket returnTicket(Account account, int row, int column);

    void add(Session session);
}
