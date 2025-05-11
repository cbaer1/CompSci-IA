import java.time.LocalTime;

public class Electricity {

    
    public double getRate(LocalTime time) {
        if (time.isAfter(LocalTime.of(18, 59)) || time.isBefore(LocalTime.of(13, 0))) {
            return 0.07749; // Off-peak (7pm – 1pm)
        } else if (time.isAfter(LocalTime.of(13, 0)) && time.isBefore(LocalTime.of(15, 0))) {
            return 0.10460; // Mid-peak (1pm – 3pm)
        } else {
            return 0.13171; // On-peak (3pm – 7pm)
        }
    }

    public static void main(String[] args) {
        Electricity electricity = new Electricity();
        LocalTime now = LocalTime.now();
        System.out.println(electricity.getRate(now));
    }
}
