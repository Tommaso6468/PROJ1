import java.util.Scanner;

public class Util {
    /**
     * Leest een int van een scanner en vraagt opnieuw als het ongeldig is.
     * @param scanner De scanner om de invoer uit te lezen.
     * @param min Het laagst toegestane getal.
     * @param max Het hoogst toegestane getal.
     * @return Het getal dat is uitgelezen.
     */
    public static int leesInt(Scanner scanner, int min, int max) {
        while (true) {
            String invoer = scanner.nextLine();
            int getal;
            try {
                getal = Integer.parseInt(invoer);
            }
            catch (NumberFormatException exception) {
                System.out.println("Ongeldig getal; probeer opnieuw.");
                continue;
            }
            if (getal < min || getal > max) {
                System.out.printf("Het getal moet minstens %d en hoogstens %d zijn; probeer opnieuw.", min, max);
                continue;
            }
            return getal;
        }
    }
}
