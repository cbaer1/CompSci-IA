import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Sandbox {
    private String apiKey = "9ed8c36e7c52444cbab190727250503";  // Replace with your actual API key
    private String location = "Boulder";  // Change as needed

    public String fetchWeatherData() {
        try {
            // Construct API URL
            String apiUrl = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + location;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.connect();

            // Read API response
            Scanner sc = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (sc.hasNext()) response.append(sc.nextLine());
            sc.close();

            return response.toString(); // Return raw JSON string
        } catch (Exception e) {
            return "Error fetching weather data: " + e.getMessage();
        }
    }

    public boolean isGoodWeather() {
        try {
            String json = fetchWeatherData();

            // Extract "condition" value manually
            int conditionIndex = json.indexOf("\"text\":\"") + 8;
            int conditionEnd = json.indexOf("\"", conditionIndex);
            String condition = json.substring(conditionIndex, conditionEnd);

            int tempIndex = json.indexOf("\"temp_c\":") + 9;
            int tempEnd = json.indexOf(",", tempIndex);
            double temperature = Double.parseDouble(json.substring(tempIndex, tempEnd));

              // Extract cloud cover (%)
            int cloudIndex = json.indexOf("\"cloud\":") + 8;
            int cloudEnd = json.indexOf(",", cloudIndex);
            int cloudCover = Integer.parseInt(json.substring(cloudIndex, cloudEnd));

            boolean isRightTemp = (temperature > 0);
            boolean isRightCloud = (cloudCover < 80);
            boolean isRightCondition = !((condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("storm")));

            if ((isRightTemp) && (isRightCloud) && (isRightCondition))
            {
                return true;
            }
            else
            {
                return false;
            }

        
        


            // Check if weather condition contains "rain" or "storm"
        //     return !(condition.toLowerCase().contains("rain") || condition.toLowerCase().contains("storm"));
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Sandbox weather = new Sandbox();
        System.out.println("Weather Data: " + weather.fetchWeatherData());
        System.out.println("Good to Charge? " + weather.isGoodWeather());
    }
}
