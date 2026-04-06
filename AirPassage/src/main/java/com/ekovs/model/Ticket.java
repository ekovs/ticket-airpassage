package com.ekovs.model;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
    /**
     * Поля для взаимодействия с объектами
     */
    private String destination;
    private int numberFlight;
    private String surname;
    private LocalDate date;

    /**
     * Конструктор для инициализации объекта с обязательным набором данных
     */
    public Ticket(String destination, int numberFlight, String surname, LocalDate date) {
        this.destination = destination;
        this.numberFlight = numberFlight;
        this.surname = surname;
        this.date = date;
    }

    /**
     * Геттеры и сеттеры для валидации
     */
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getNumberFlight() {
        return numberFlight;
    }

    public void setNumberFlight(int numberFlight) {
        this.numberFlight = numberFlight;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Equals для сравнения объектов по их содержимому, а не по ссылкам
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return numberFlight == ticket.numberFlight && Objects.equals(destination, ticket.destination)
                && Objects.equals(surname, ticket.surname) && Objects.equals(date, ticket.date);
    }

    /**
     * HashCod для корректной работы объекта в коллекции
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, numberFlight, surname, date);
    }

    /**
     * Для удобного вывода информации в консоли
     */
    @Override
    public String toString() {
        return "Заявка {" +
                "Пункт назначения: '" + destination + '\'' +
                ", Номер рейса: " + numberFlight +
                ", Фамилия и инициалы: '" + surname + '\'' +
                ", Дата: " + date +
                '}';
    }
}
