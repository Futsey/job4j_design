package ru.job4j.cinema;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Disabled
class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenReserve() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Reserve reserve = new Reserve3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = reserve.reserve(cinema, account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenUnReserve() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Reserve reserve = new Reserve3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = reserve.unReserve(cinema, account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenTicketReturned() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.returnTicket(account, 1, 1);
        assertThat(ticket).isNull();
    }

    @Test
    public void whenFindFreePlace() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Session session = cinema.find(singleSession -> true);
        assertThat(session).isNull();
    }

    @Test
    public void whenFindAllFreePlaces() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.findAllFreePlaces(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenFindAllBoughtPlaces() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.findAllBoughtPlaces(session -> true);
        assertThat(sessions).isNull();
    }

    @Test
    public void whenFindAllReservedPlaces() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.findAllBoughtPlaces(session -> true);
        assertThat(sessions).isNull();
    }

    @Test()
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Test()
    public void whenTryToBuyOccupiedPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
        });
    }

    @Test()
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, 1, 1, date);
        });
    }
}