public class Pianta {
    public String Common;
    public String Botanical;
    public int Zone;
    public String Light;
    public double Price;
    public String Currency;

    public Pianta(String common, String botanical, int zone, String light, double price, String currency) {
        Common = common;
        Botanical = botanical;
        Zone = zone;
        Light = light;
        Price = price;
        Currency = currency;
    }

    public static void printPianta(Pianta pianta) {
        System.out.println("Common: " + pianta.Common);
        System.out.println("Botanical: " + pianta.Botanical);
        System.out.println("Zone: " + pianta.Zone);
        System.out.println("Light: " + pianta.Light);
        System.out.println("Price: " + pianta.Price);
        System.out.println("Currency: " + pianta.Currency);
    }
}
