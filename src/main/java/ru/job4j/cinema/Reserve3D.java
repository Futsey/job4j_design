package ru.job4j.cinema;

import java.util.Calendar;

public class Reserve3D implements Reserve {

    @Override
    public Ticket reserve(Cinema cinema, Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public Ticket unReserve(Cinema cinema, Account account, int row, int column, Calendar date) {
        return null;
    }
}
