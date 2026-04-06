package com.ekovs;

import com.ekovs.model.BookingService;
import com.ekovs.model.Ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final BookingService manager = new BookingService();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        new Main().start();
    }

    public void start(){
        boolean isRunning = true;
        System.out.println("--- Система управления заявками ---");

        while (isRunning){
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Добавить | 2. Удалить | 3. Поиск | 4. Все | 0. Выход");
            int choice = readIntInput("");

            switch (choice){
                case 1 -> handleAdd();
                case 2 -> handleDelete();
                case 3 -> handleSearch();
                case 4 -> manager.showAllTickets();
                case 0 -> isRunning = false;
                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    private LocalDate readDateInput(){
        while (true){
            System.out.println("Введите дату (дд.мм.гггг): ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e){
                System.out.println("Неверный формат даты!");
            }
        }
    }

    private int readIntInput(String prompt){
        while (true){
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Ошибка! Введите корректное число.");
            }
        }
    }

    private void handleAdd() {
        System.out.println("Пункт назначения: ");
        String dest = scanner.nextLine();
        int num = readIntInput("Номер рейса: ");
        System.out.println("Фамилия и инициалы: ");
        String surname = scanner.nextLine();
        LocalDate date = readDateInput();

        manager.add(new Ticket(dest, num, surname, date));
        System.out.println("Заявка добавлена.");
    }

    private void handleDelete() {
        System.out.println("Введите фамилию и инициалы пассажира для удаления:");
        String name = scanner.nextLine();
        Ticket deleteTicket = manager.delete(name);
        if (deleteTicket != null){
            System.out.println("Успешно удалено.");
            System.out.println(deleteTicket);
        }
        else {
            System.out.println("Не найдено.");
        }
    }

    private void handleSearch() {
        int num = readIntInput("Номер рейса: ");
        LocalDate date = readDateInput();

        ArrayList<Ticket> results = manager.search(num, date);
        if (results.isEmpty()){
            System.out.println("Ничего не найдено.");
        }
        else {
            results.forEach(System.out::println);
        }
    }
}