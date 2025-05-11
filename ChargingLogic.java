import java.time.LocalTime;

public class ChargingLogic {
    

    public static void main(String[] args) {
        Weather weather1 = new Weather();
        Electricity electricity1 = new Electricity();
        LocalTime now = LocalTime.now();
        double rate = electricity1.getRate(now);
        boolean isGood = weather1.isGoodWeather();


        if (isGood && rate <= 0.08) {
            System.out.println("Perfect time to charge: Sunny and cheap electricity.");
        } else if (isGood) {
            System.out.println("Pretty good time to charge: It's sunny, but electricity is more expensive right now.");
        } else if (rate <= 0.08) {
            System.out.println("Decent time to charge: Cheap electricity, but no sun.");
        } else {
            System.out.println("Bad time to charge: It's cloudy and expensive.");
        }


       
    
    }
    
    

}
