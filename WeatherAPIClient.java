package com.codtech.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

import org.json.JSONObject;

public class WeatherAPIClient {

    public static void main(String[] args) {

        try {
            // 1. API details
            String apiKey = "71fcd98c3ddba965216eeb3e4357542f";
            String city = "Bangalore";

            // 2. Build API URL
            String urlString =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=" + apiKey + "&units=metric";

            URL url = new URL(urlString);

            // 3. Create connection
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 4. Read response
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 5. Parse JSON
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject main = jsonObject.getJSONObject("main");

            double temperature = main.getDouble("temp");
            int humidity = main.getInt("humidity");

            // 6. Get Date and Day
            LocalDate currentDate = LocalDate.now();
            DayOfWeek day = currentDate.getDayOfWeek();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

          
            System.out.println("WEATHER REPORT");
            System.out.println("--------------------------------");
            System.out.println("City: " + city);
            System.out.println("Date: " + currentDate.format(formatter));
            System.out.println("Day: " + day);
            System.out.println("Temperature: " + temperature + " °C");
            System.out.println("Humidity: " + humidity + "%");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
