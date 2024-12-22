package zadaniePiate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj trzy pierwsze cyfry numeru konta: ");
        String bankCode;
        try {
            bankCode = scanner.nextLine();
            if (!bankCode.matches("\\d{3}")) {
                System.out.println("Nie podano 3 cyfr");
                return;
            }
        } catch (Exception e) {
            System.err.println("Wystąpił błąd: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String fileUrl = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    boolean found = false;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\\s+", 2);
                        if (parts.length == 2 && parts[0].startsWith(bankCode)) {
                            System.out.println("Numer banku: " + parts[0]);
                            System.out.println("Nazwa banku: " + parts[1]);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Nie znaleziono informacji.");
                    }
                }
            } else {
                System.out.println("Nie udało się pobrać pliku. Kod błędu HTTP: " + connection.getResponseCode());
            }
        } catch (Exception e) {
            System.err.println("Wystąpił błąd: " + e.getMessage());
            e.printStackTrace();
        }
    }
}