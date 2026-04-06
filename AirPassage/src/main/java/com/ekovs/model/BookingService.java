package com.ekovs.model;

import com.ekovs.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookingService {
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public BookingService() {
        this.tickets = new ArrayList<>(UserRepository.loadTickets());
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Метод добавления заявки в список
     */
    public void add(Ticket ticket){
        this.tickets.add(ticket);
        UserRepository.saveTickets(tickets);
    }

    /**
     * Метод поиска по фамилии (возвращает объект)
     */
    public Ticket searchSurname (String surname){
        for (Ticket ticket : this.tickets){
            if (surname.equalsIgnoreCase(ticket.getSurname())){
                return ticket;
            }
        }
        return null;
    }

    /**
     * Метод удаления заявки из списка по фамилии (возвращает объект)
     */
    public Ticket delete(String surname){
        Ticket ticketDelete = this.searchSurname(surname);
        if (ticketDelete != null){
            this.tickets.remove(ticketDelete);
            UserRepository.saveTickets(tickets);
        }
        return ticketDelete;
    }

    /**
     * Метод поиска по номеру рейса и дате (возвращает список)
     */
    public ArrayList<Ticket> search(int number, LocalDate date){
        ArrayList<Ticket> ticketAll = new ArrayList<>();
        for (Ticket ticket : this.tickets){
            if (ticket.getNumberFlight() == number && ticket.getDate().equals(date)){
                ticketAll.add(ticket);
            }
        }
        return ticketAll;
    }

    /**
     * Метод вывода всех заявок
     */
    public void showAllTickets(){
        if (this.tickets.isEmpty()){
            System.out.println("Заявок пока что нет!");
            return;
        }

        for (Ticket ticket : this.tickets){
            System.out.println(ticket);
        }
    }
}
