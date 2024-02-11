
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол), разделенные пробелом:");

        try {
            String input = scanner.nextLine();
            String[] data = input.split("\\s+");

            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];

            if (!lastName.matches("[a-zA-Zа-яА-Я]+") || !firstName.matches("[a-zA-Zа-яА-Я]+") || !middleName.matches("[a-zA-Zа-яА-Я]+")) {
                throw new IllegalArgumentException("Имя, фамилия и отчество должны содержать только буквы");
            }

            String birthDateString = data[3];
            if (!isValidDateFormat(birthDateString)) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Используйте формат dd MM yyyy");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = dateFormat.parse(data[3]);

            long phoneNumber;

            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Номер телефона должен содержать только цифры");
            }

            char gender = data[5].charAt(0);
            if (gender != 'm' && gender != 'f') {
                throw new IllegalArgumentException("Неверный формат пола. Введите m или f");
            }

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);

            // Создание или открытие файла
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastName + ".txt", true))) {
                // Запись данных в файл
                writer.write(userData.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Данные успешно записаны в файл.");

        } catch (IllegalArgumentException | ParseException e) {
            System.err.println("Ошибка ввода данных: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static boolean isValidDateFormat(String dateString) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

