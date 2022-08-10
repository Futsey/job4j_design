package ru.job4j.cinema;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    @Override
    public Session find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public List<Session> findAllFreePlaces(Predicate<Session> filter) {
        return null;
    }

    @Override
    public List<Session> findAllBoughtPlaces(Predicate<Session> filter) {
        return null;
    }

    @Override
    public List<Session> findAllReservedPlaces(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public Ticket returnTicket(Account account, int row, int column) {
        return null;
    }

    @Override
    public void add(Session session) {

    }
}
