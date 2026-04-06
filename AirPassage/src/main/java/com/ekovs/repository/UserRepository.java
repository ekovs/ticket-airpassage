package com.ekovs.repository;

import com.ekovs.model.Ticket;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserRepository {
    /**
     * Константа для централизованного управления путем к данным
     */
    private static final String FILE_NAME = "tickets.txt";

    /**
     * Специализация объектов (перевод данных в строковый формат)
     */
    public static void saveTickets(ArrayList<Ticket> tickets) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            for (Ticket t : tickets){
                String line = t.getDestination() + "|" + t.getNumberFlight() + "|" +
                        t.getSurname() + "|" + t.getDate();
                writer.write(line);
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    /**
     * Десериализация объектов (чтение данных из файла, реализует логику парсинга и обработки исключений)
     */
    public static ArrayList<Ticket> loadTickets(){
        ArrayList<Ticket> tickets = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return tickets;
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("\\|");
                if (parts.length == 4){
                    Ticket ticket = new Ticket(parts[0],
                            Integer.parseInt(parts[1]), parts[2], LocalDate.parse(parts[3]));
                    tickets.add(ticket);
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (NumberFormatException e){
            System.out.println("Номер рейса указан некорректно: " + e.getMessage());
        }
        return tickets;
    }
}
