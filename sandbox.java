import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class sandbox {
    private String apiKey = "9ed8c36e7c52444cbab190727250503";  // Replace with your actual API key
    private String location = "Boulder";  // Change as needed

    public String fetchWeatherData() {
        try {
            // Construct API URL
            String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // HTTP GET request
            conn.setRequestProperty("User-Agent", "Mozilla/5.0"); // Avoid API blocks
            conn.connect();

            // Check for successful response (HTTP 200)
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Error: API response code " + responseCode;
            }

            // Read API response
            Scanner sc = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (sc.hasNext()) response.append(sc.nextLine());
            sc.close();

            return response.toString();  // Return raw JSON response
        } catch (Exception e) {
            return "Error fetching weather data: " + e.getMessage();
        }
    }

    public boolean isGoodWeather() {
        try {
            String json = fetchWeatherData();
            JSONObject obj = new JSONObject(json);

            // Extract weather condition (e.g., "Partly cloudy", "Rain", etc.)
            String condition = obj.getJSONObject("current").getJSONObject("condition").getString("text");
            return !(condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("storm"));
        } catch (Exception e) {
            return false;  // Default to false if API call fails
        }
    }

    public static void main(String[] args) {
        sandbox weather = new sandbox();
        System.out.println("Weather Data: " + weather.fetchWeatherData());
        System.out.println("Good to Charge? " + weather.isGoodWeather());
    }
}
