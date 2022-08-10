package ru.job4j.cinema;

import java.util.Calendar;

public interface Reserve {

    Ticket reserve(Cinema cinema, Account account, int row, int column, Calendar date);

    Ticket unReserve(Cinema cinema, Account account, int row, int column, Calendar date);
}
