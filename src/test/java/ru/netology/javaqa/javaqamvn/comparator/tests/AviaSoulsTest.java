package ru.netology.javaqa.javaqamvn.comparator.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.javaqamvn.comparator.AviaSouls;
import ru.netology.javaqa.javaqamvn.comparator.Ticket;
import ru.netology.javaqa.javaqamvn.comparator.TicketTimeComparator;

import java.util.Arrays;
import java.util.Comparator;

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
