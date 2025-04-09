package ru.netology.javaqa.javaqamvn.comparator.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.javaqamvn.comparator.AviaSouls;
import ru.netology.javaqa.javaqamvn.comparator.Ticket;
import ru.netology.javaqa.javaqamvn.comparator.TicketTimeComparator;

import java.util.Arrays;

public class AviaSoulsTest {

    @Test
    public void shouldCompareMoreExpensiveTicketWithLessExpensive() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Spb", "Msk", 4000, 20, 22);

        int expected = 1;
        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsWithSamePrice() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Spb", "Msk", 5000, 20, 22);

        int expected = 0;
        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCompareLessExpensiveTicketWithMoreExpensive() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 4000, 18, 20);
        Ticket ticket2 = new Ticket("Spb", "Msk", 5000, 20, 22);

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchWithNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("London", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Glazgo", "Saint-Petersburg", 4000, 20, 22);
        Ticket ticket3 = new Ticket("Jupiter", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket4 = new Ticket("Mars", "Saint-Petersburg", 4000, 20, 22);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Moscow", "Saint-Petersburg");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWithOneTicketFound() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Glazgo", "Saint-Petersburg", 18000, 20, 24);
        Ticket ticket3 = new Ticket("Jupiter", "Saint-Petersburg", 500_000, 1, 23);
        Ticket ticket4 = new Ticket("Mars", "Saint-Petersburg", 4_000_000, 2, 22);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        Ticket[] expected = {ticket1};
        Ticket[] actual = manager.search("Moscow", "Saint-Petersburg");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWithTwoTicketsFound() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Glazgo", "Saint-Petersburg", 18000, 20, 24);
        Ticket ticket3 = new Ticket("Jupiter", "Saint-Petersburg", 500_000, 1, 23);
        Ticket ticket4 = new Ticket("Mars", "Saint-Petersburg", 4_000_000, 2, 22);
        Ticket ticket5 = new Ticket("Glazgo", "Saint-Petersburg", 23000, 18, 21);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        Ticket[] expected = {ticket2, ticket5};
        Ticket[] actual = manager.search("Glazgo", "Saint-Petersburg");
        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void shouldSearchAndSortByPrice() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Moscow", "Saint-Petersburg", 4000, 20, 22);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        Ticket[] expected = {ticket2, ticket1};
        Ticket[] actual = manager.search("Moscow", "Saint-Petersburg");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndSortByTimeComparator() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Moscow", "Saint-Petersburg", 4000, 20, 23);

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
        Ticket[] tickets = {ticket1, ticket2};
        Arrays.sort(tickets, ticketTimeComparator);

        Ticket[] expected = {ticket1, ticket2};
        Assertions.assertArrayEquals(expected, tickets);
    }

    @Test
    public void shouldSearchAndSortByManager() {
        Ticket ticket1 = new Ticket("Moscow", "Saint-Petersburg", 5000, 18, 20);
        Ticket ticket2 = new Ticket("Moscow", "Saint-Petersburg", 4000, 20, 23);

        TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = manager.searchAndSortBy("Moscow", "Saint-Petersburg", ticketTimeComparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
